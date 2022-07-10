package com.projetoapi.controller;

import com.projetoapi.model.Cep;
import com.projetoapi.model.Frete;
import com.projetoapi.service.CepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CepController {

    @Autowired
    private CepService service;

    @GetMapping("/")
    public String home(ModelMap model) {
        model.addAttribute("frete", new Frete());
        return "index";
    }

    @GetMapping("/consultar/{cep}")
    public ResponseEntity<?> buscarCepPorCodigo(@PathVariable("cep") String cep) {
        Cep consultaApi = service.buscarCep(cep);
        return ResponseEntity.ok().body(consultaApi);
    }
}
