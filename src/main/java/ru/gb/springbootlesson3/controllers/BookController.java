package ru.gb.springbootlesson3.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.springbootlesson3.entity.Book;
import ru.gb.springbootlesson3.services.BookService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookService service;

    @RequestMapping(value = "book/{id}", method = RequestMethod.GET)
    public ResponseEntity<Book> findById(@PathVariable long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "book", method = RequestMethod.GET)
    public ResponseEntity<List<Book>> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "book/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Book> delete(@PathVariable long id) {
        service.deleteById(id);
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(service.findById(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "book", method = RequestMethod.POST)
    public ResponseEntity<Book> createNewBook(@RequestBody String name) {
        service.addNewBook(name);
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.findByName(name));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/ui/books")
    public String list(Model model){
        List<Book> books = service.getAll();
        List<String> names = new ArrayList<>();
        for (Book book: books){
            names.add(book.getName());
        }
        model.addAttribute("list", names);
        return "books";
    }
}
