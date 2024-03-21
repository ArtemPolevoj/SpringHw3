package ru.gb.springbootlesson3.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.springbootlesson3.services.IssueService;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("ui")
@RequiredArgsConstructor
public class ReaderControllerUi {
    private final IssueService service;
    @GetMapping("reader/{id}")
    public String table(Model model, @PathVariable long id){
    Map<String, List<String>> map = service.getAllBooksIdIssue(id);
    Set<String> set = map.keySet();
    String reader = set.iterator().next();
    List<String> books = map.get(reader);
        model.addAttribute("reader", reader);
        model.addAttribute("list", books);
        return "readerId";
    }
}
