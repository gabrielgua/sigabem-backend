package com.projetoapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.projetoapi.model.Frete;
import com.projetoapi.model.FreteDTO;
import com.projetoapi.service.FreteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("frete")
public class FreteController {

    @Autowired
    private FreteService service;

    @PostMapping
    public ResponseEntity<FreteDTO> salvarFrete(@Valid @RequestBody Frete frete) {
        FreteDTO freteDTO = service.salvar(frete);
        return ResponseEntity.ok().body(freteDTO);
    }

    @GetMapping
    public ResponseEntity<List<Frete>> buscarTodos() {
        return ResponseEntity.ok().body(service.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Frete> buscarFretePorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(service.buscarPorId(id));
    }
}
