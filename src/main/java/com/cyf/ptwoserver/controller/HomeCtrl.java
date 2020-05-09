package com.cyf.ptwoserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeCtrl {
    @RequestMapping("/index")
    public String index() {
        return "index";
    }
}
