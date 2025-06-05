package com.personal.webscrapping.controller;


import com.personal.webscrapping.service.WebScrapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Scrapper {

    private WebScrapping webScrapping;

    public Scrapper(WebScrapping webScrapping) {
        this.webScrapping = webScrapping;
    }
    @GetMapping
    public String index(Model model) {
        return "index";
    }

    @PostMapping("/")
    public String post(@ModelAttribute("url") String url, Model model) {
        model.addAttribute("results", this.webScrapping.getBets(url));
        return "index";
    }

}
