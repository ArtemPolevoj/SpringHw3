package ru.gb.springbootlesson3.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.springbootlesson3.entity.Issue;
import ru.gb.springbootlesson3.entity.Reader;
import ru.gb.springbootlesson3.repository.IssueRepository;
import ru.gb.springbootlesson3.repository.ReaderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReaderService {
    private final ReaderRepository readerRepository;
    private final IssueRepository issueRepository;
    public Reader findById(long id){
        return readerRepository.findById(id);
    }
    public void addNewBook(String name){
        readerRepository.save(new Reader(name));
    }
    public void deleteById(long id){
        readerRepository.deleteById(id);
    }
    public List<Reader> getAll(){
        return  readerRepository.getAll();
    }
    public Reader findByName(String name){
        return readerRepository.findByName(name);
    }

    public List<Issue> findIssue(long id) {

        return issueRepository.findIssueByReaderId(id);
    }
}
