package com.projetoapi.controller;

import com.projetoapi.model.Cep;
import com.projetoapi.service.CepService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Tag(name = "API CEP externa")
@RequestMapping("api")
@CrossOrigin("*")
public class CepController {

    @Autowired
    private CepService service;

    @GetMapping("/consultar/{cep}")
    @Operation(description = "Retorna a busca de um CEP consumindo a API externa.")
    public ResponseEntity<Cep> buscarCepPorCodigo(@PathVariable("cep") String cep) {
        Cep consultaApi = service.buscarCep(cep);
        return ResponseEntity.ok().body(consultaApi);
    }
}
