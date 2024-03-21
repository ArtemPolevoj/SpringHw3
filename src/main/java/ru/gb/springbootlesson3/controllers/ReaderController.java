package ru.gb.springbootlesson3.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.springbootlesson3.entity.Issue;
import ru.gb.springbootlesson3.entity.Reader;
import ru.gb.springbootlesson3.services.ReaderService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
public class ReaderController {
    private final ReaderService service;

    @RequestMapping(value = "reader/{id}", method = RequestMethod.GET)
    public ResponseEntity<Reader> findById(@PathVariable long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "reader/{id}/issue", method = RequestMethod.GET)
    public ResponseEntity<List<Issue>> findIssue(@PathVariable long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findIssue(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "reader", method = RequestMethod.GET)
    public ResponseEntity<List<Reader>> booksResponseEntity() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "reader/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Reader> delete(@PathVariable long id) {
        service.deleteById(id);
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(service.findById(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "reader", method = RequestMethod.POST)
    public ResponseEntity<Reader> createNewBook(@RequestBody String name) {
        service.addNewBook(name);
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.findByName(name));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/ui/reader")
    public String list(Model model){
        List<Reader> books = service.getAll();
        List<String> names = new ArrayList<>();
        for (Reader r: books){
            names.add(r.getName());
        }
        model.addAttribute("list", names);
        return "readers";
    }
}
