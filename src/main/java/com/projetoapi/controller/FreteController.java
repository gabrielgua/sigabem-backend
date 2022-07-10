package com.projetoapi.controller;


import com.projetoapi.model.Frete;
import com.projetoapi.model.FreteDTO;
import com.projetoapi.service.FreteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("frete")
@Tag(name = "API Frete")
@CrossOrigin("*")
public class FreteController {

    @Autowired
    private FreteService service;

    @PostMapping
    @Operation(description = "Persiste no banco de dados o Frete calculado.")
    public ResponseEntity<FreteDTO> salvarFrete(@Valid @RequestBody Frete frete) {
        FreteDTO freteDTO = service.salvar(frete);
        return ResponseEntity.ok().body(freteDTO);
    }

    @GetMapping
    @Operation(description = "Retorna todos os Fretes salvos no banco de dados.")
    public ResponseEntity<List<Frete>> buscarTodos() {
        return ResponseEntity.ok().body(service.buscarTodos());
    }

    @GetMapping("/{id}")
    @Operation(description = "Retorna apenas um Frete especificado pelo id.")
    public ResponseEntity<Frete> buscarFretePorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(service.buscarPorId(id));
    }
}
