package ru.gb.springbootlesson3.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.springbootlesson3.services.IssueService;

@Controller
@RequestMapping("ui")
@RequiredArgsConstructor
public class IssueControllerUi {
    private final IssueService service;

    @GetMapping("issues")
    public String table(Model model) {
        model.addAttribute("table", service.getAllIssueSrt());
        return "issuesStr";
    }
}
