package ru.gb.springbootlesson3.repository;

import org.springframework.stereotype.Repository;
import ru.gb.springbootlesson3.entity.Book;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {
    private final List<Book> list = new ArrayList<>();

    public BookRepository() {
        list.add(new Book("Война и мир"));
        list.add(new Book("Мастер и Маргарита"));
        list.add(new Book("Приключения Буратино"));
    }

    public Book findById(long id){
        return list.stream().filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }
    public Book findByName(String name){
        return list.stream().filter(e -> e.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
    public void save(Book book){
        list.add(book);
    }
    public void deleteById(long id){
        list.removeIf(e -> e.getId() == id);
    }

    public List<Book> getAll(){
        return List.copyOf(list);
    }
}
