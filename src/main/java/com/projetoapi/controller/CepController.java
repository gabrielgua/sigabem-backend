package com.projetoapi.controller;

import com.projetoapi.model.Cep;
import com.projetoapi.service.CepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CepController {

    @Autowired
    private CepService service;

    @GetMapping("/consultar/{cep}")
    public ResponseEntity<?> buscarCepPorCodigo(@PathVariable("cep") String cep) {
        Cep consultaApi = service.buscarCep(cep);
        return ResponseEntity.ok().body(consultaApi);
    }
}
