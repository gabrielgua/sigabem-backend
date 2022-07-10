package com.projetoapi.service;

import com.projetoapi.model.Cep;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CepService {

    public Cep buscarCep(String cep) {

        RestTemplate restTemplate = new RestTemplate();
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        restTemplate = restTemplateBuilder.build();

        Cep consultaApi = restTemplate.getForObject("https://viacep.com.br/ws/"+cep+"/json", Cep.class);
        return consultaApi;
    }
}
