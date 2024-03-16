package ru.gb.springbootlesson3.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springbootlesson3.entity.Issue;
import ru.gb.springbootlesson3.entity.Reader;
import ru.gb.springbootlesson3.services.ReaderService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("reader")
public class ReaderController {
    private final ReaderService service;

    @GetMapping("{id}")
    public ResponseEntity<Reader> findById(@PathVariable long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{id}/issue")
    public ResponseEntity<List<Issue>> findIssue(@PathVariable long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findIssue(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping()
    public ResponseEntity<List<Reader>> booksResponseEntity() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Reader> delete(@PathVariable long id) {
        service.deleteById(id);
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(service.findById(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Reader> createNewBook(@RequestBody String name) {
        service.addNewBook(name);
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.findByName(name));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
