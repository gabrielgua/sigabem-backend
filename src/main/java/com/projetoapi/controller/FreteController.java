package com.projetoapi.controller;

import com.projetoapi.model.ConsultaApi;
import com.projetoapi.model.Frete;
import com.projetoapi.model.FreteDTO;
import com.projetoapi.service.ConsultaApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("frete")
public class FreteController {

    @Autowired
    private ConsultaApiService apiService;

    @PostMapping
    public ResponseEntity<FreteDTO> calcularFrete(@RequestBody Frete frete) {
        List<ConsultaApi> ceps = getDadosCep(frete.getCepOrigem(), frete.getCepDestino());
        if (ceps.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        FreteDTO freteDTO = new FreteDTO();
        ConsultaApi cepOrigemObj = ceps.get(0);
        ConsultaApi cepDestinoObj = ceps.get(1);


        List<BigDecimal> descontoEDias = getDescontoEDias(cepOrigemObj, cepDestinoObj);
        BigDecimal desconto = descontoEDias.get(0);
        BigDecimal dias = descontoEDias.get(1);

        freteDTO.setVlTotalFrete(frete.getPeso().subtract(frete.getPeso().multiply(desconto)));
        freteDTO.setDataPrevistaEntrega(frete.getDataConsulta().plusDays(Integer.parseInt(String.valueOf(dias))));

        freteDTO.setCepOrigem(cepOrigemObj.getCep());
        freteDTO.setCepDestino(cepDestinoObj.getCep());




        return ResponseEntity.ok().body(freteDTO);
    }

    public List<ConsultaApi> getDadosCep(String cepOrigem, String cepDestino) {
        ConsultaApi apiOrigem = apiService.buscarCep(cepOrigem);
        ConsultaApi apiDestino = apiService.buscarCep(cepDestino);
        List<ConsultaApi> ceps = new ArrayList<>();
        ceps.add(apiOrigem);
        ceps.add(apiDestino);
        return ceps;
    }

    public List<BigDecimal> getDescontoEDias(ConsultaApi cepO, ConsultaApi cepD) {
        List<BigDecimal> descontoEDias = new ArrayList<>();
        BigDecimal desconto = BigDecimal.valueOf(0);
        BigDecimal dias = BigDecimal.valueOf(10);

        if (cepO.getUf().equals(cepD.getUf())) {
            desconto = BigDecimal.valueOf(.75);
            dias = BigDecimal.valueOf(1);

            if (cepO.getDdd().equals(cepD.getDdd())) {
                desconto = BigDecimal.valueOf(.5);
                dias = BigDecimal.valueOf(3);
            }
        }

        descontoEDias.add(desconto);
        descontoEDias.add(dias);

        return descontoEDias;
    }


}
