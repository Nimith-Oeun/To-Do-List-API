package com.personal.todolistapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class RootController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "To-Do List API");
        String project = "To-Do List (V1.0.0)";
        String dateTime = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss 'GMT' xxx yyyy"));
        model.addAttribute("project", project);
        model.addAttribute("dateTime", dateTime);
        return "index";
    }

}
