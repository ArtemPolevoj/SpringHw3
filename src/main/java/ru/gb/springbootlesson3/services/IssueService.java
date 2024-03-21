package ru.gb.springbootlesson3.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.gb.springbootlesson3.controllers.IssueRequest;
import ru.gb.springbootlesson3.entity.Book;
import ru.gb.springbootlesson3.entity.Issue;
import ru.gb.springbootlesson3.entity.IssueStr;
import ru.gb.springbootlesson3.repository.BookRepository;
import ru.gb.springbootlesson3.repository.IssueRepository;
import ru.gb.springbootlesson3.repository.ReaderRepository;

import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class IssueService {
    @Value("${application.issue.max-allowed-books:1}")
    private int maxBook;
    private final BookRepository bookRepository;
    private final IssueRepository issueRepository;
    private final ReaderRepository readerRepository;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Issue createIssue(IssueRequest request) {
        if (bookRepository.findById(request.getBookId()) == null) {
            log.info("Не удалось найти книгу с id " + request.getBookId());
            throw new NoSuchElementException("Не удалось найти книгу с id " + request.getBookId());
        }
        if (readerRepository.findById(request.getReaderId()) == null) {
            log.info("Не удалось найти читателя с id " + request.getReaderId());
            throw new NoSuchElementException("Не удалось найти читателя с id " + request.getReaderId());
        }

        if (issueRepository.getCountBookReader(request.getReaderId()) >= maxBook) {
            log.info("У читателя с id " + request.getReaderId() + " максимальное количество книг на руках.");
            return new Issue(-1, -1);
        }

        Issue issue = new Issue(request.getReaderId(), request.getBookId());
        issueRepository.createIssue(issue);
        return issue;
    }

    public TreeMap<String, String> findById(long id) {
        TreeMap<String, String> map = new TreeMap<>();
        Issue issue = issueRepository.findById(id);
        long idReader = issue.getIdReader();
        long idBook = issue.getIdBook();
        String nameReader = readerRepository.findById(idReader).getName();
        String nameBook = bookRepository.findById(idBook).getName();
        String issuedAt = issue.getIssuedAt().format(formatter);
        String returnedAt = "-";
        if (issue.getReturnedAt() != null) {
            returnedAt = issue.getReturnedAt().format(formatter);
        }
        map.put("Reader", nameReader);
        map.put("Book", nameBook);
        map.put("issued_at", issuedAt);
        map.put("returned_at", returnedAt);
        return map;
    }

    public void setReturnedAt(long id) {
        issueRepository.setReturnedAt(id);
    }

    public List<IssueStr> getAllIssueSrt() {
        List<IssueStr> issueStrs = new ArrayList<>();
        List<Issue> temp = issueRepository.getAll();
        for (Issue issue : temp) {
            String nameReader = readerRepository.findById(issue.getIdReader()).getName();
            String nameBook = bookRepository.findById(issue.getIdBook()).getName();
            String issuedAt = issue.getIssuedAt().format(formatter);
            String returnedAt = "";
            if (issue.getReturnedAt() != null) {
                returnedAt = issue.getReturnedAt().format(formatter);
            }
            issueStrs.add(new IssueStr(nameReader, nameBook, issuedAt, returnedAt));
        }
        return issueStrs;
    }

    public Map<String, List<String>> getAllBooksIdIssue(long id) {
        Map<String, List<String>> map = new HashMap<>();
        List<String> books = new ArrayList<>();
        List<Issue> temp = issueRepository.getAll();
        String nameReader = "";
        for (Issue issue : temp) {
            if (issue.getIdReader() == id) {
                nameReader = readerRepository.findById(issue.getIdReader()).getName();
                String nameBook = bookRepository.findById(issue.getIdBook()).getName();
                books.add(nameBook);
            }
        }
        String key = nameReader + ", Id: " + id;
        map.put(key, books);
        return map;
    }

}