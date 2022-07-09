package com.projetoapi.controller;

import com.projetoapi.model.ConsultaApi;
import com.projetoapi.service.ConsultaApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ConsultaApiController {

    @Autowired
    private ConsultaApiService service;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/consultar/{cep}")
    public ResponseEntity<?> buscarCepPorCodigo(@PathVariable("cep") String cep) {
        ConsultaApi consultaApi = service.buscarCep(cep);
        return ResponseEntity.ok().body(consultaApi);
    }
}
