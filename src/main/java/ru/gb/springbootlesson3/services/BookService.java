package ru.gb.springbootlesson3.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.springbootlesson3.entity.Book;
import ru.gb.springbootlesson3.repository.BookRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository repository;

    public Book findById(long id){
       return repository.findById(id);
    }
    public void addNewBook(String name){
        repository.save(new Book(name));
    }
    public void deleteById(long id){
        repository.deleteById(id);
    }
    public List<Book> getAll(){
        return  repository.getAll();
    }
    public Book findByName(String name){
        return repository.findByName(name);
    }
}
