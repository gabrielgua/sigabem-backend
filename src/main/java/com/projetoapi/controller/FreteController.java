package com.projetoapi.controller;

import com.projetoapi.model.Frete;
import com.projetoapi.model.FreteDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("frete")
public class FreteController {

    @PostMapping
    public ResponseEntity<FreteDTO> consultarFrete(@RequestBody Frete frete) {

    }


}
