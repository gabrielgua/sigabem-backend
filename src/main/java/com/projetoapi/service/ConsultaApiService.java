package com.projetoapi.service;

import com.projetoapi.model.ConsultaApi;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConsultaApiService {

    public ConsultaApi buscarCep(String cep) {

        RestTemplate restTemplate = new RestTemplate();
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        restTemplate = restTemplateBuilder.build();

        ConsultaApi consultaApi = restTemplate.getForObject("https://viacep.com.br/ws/"+cep+"/json", ConsultaApi.class);
        return consultaApi;
    }
}
