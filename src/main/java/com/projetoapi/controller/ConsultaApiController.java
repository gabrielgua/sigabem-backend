package com.projetoapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConsultaApiController {

    @GetMapping("/")
    public String home() {
        return "index";
    }
}
