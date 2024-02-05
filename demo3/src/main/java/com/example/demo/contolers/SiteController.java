package com.example.demo.contolers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SiteController {

    @GetMapping("/")
    public String getWplata(Model model) {
        return "start";
    }
}
