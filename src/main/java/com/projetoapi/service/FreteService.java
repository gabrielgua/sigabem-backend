package com.projetoapi.service;

import com.projetoapi.exception.ObjectNotFoundException;
import com.projetoapi.model.Cep;
import com.projetoapi.model.Frete;
import com.projetoapi.model.FreteDTO;
import com.projetoapi.repository.FreteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class FreteService {

    @Autowired
    private CepService apiService;

    @Autowired
    private FreteRepository repository;

    @Transactional(readOnly = true)
    public Frete buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Frete não encontrado, id: "+id));
    }

    @Transactional(readOnly = true)
    public List<Frete> buscarTodos() {
        return repository.findAll();
    }

    @Transactional(readOnly = false)
    public FreteDTO salvar(Frete frete) {

        FreteDTO freteDTO = new FreteDTO();
        Cep cepOrigemObj = getDadosCep(frete.getCepOrigem());
        Cep cepDestinoObj = getDadosCep(frete.getCepDestino());

        BigDecimal desconto = getDesconto(cepOrigemObj, cepDestinoObj);
        int dias = getDias(cepOrigemObj, cepDestinoObj);

        freteDTO.setVlTotalFrete(frete.getPeso().subtract(frete.getPeso().multiply(desconto))); //peso - (peso * desconto);
        freteDTO.setDataPrevistaEntrega(frete.getDataConsulta().plusDays(dias)); //dataConsulta + dias;

        freteDTO.setCepOrigem(cepOrigemObj.getCep());
        freteDTO.setCepDestino(cepDestinoObj.getCep());

        frete.setVlTotalFrete(freteDTO.getVlTotalFrete());
        frete.setDataPrevistaEntrega(freteDTO.getDataPrevistaEntrega());
        repository.save(frete);

        return freteDTO;
    }

    @Transactional(readOnly = true)
    public Cep getDadosCep(String cep) {
        return apiService.buscarCep(cep);
    }


    public BigDecimal getDesconto(Cep cepO, Cep cepD) {
        BigDecimal desconto = BigDecimal.valueOf(0);
        if (cepO.getUf().equals(cepD.getUf())) {
            desconto = BigDecimal.valueOf(.75);
            if (cepO.getDdd().equals(cepD.getDdd())) {
                desconto = BigDecimal.valueOf(.5);
            }
        }
        return desconto;
    }

    public Integer getDias(Cep cepO, Cep cepD) {
        int dias = 10;
        if (cepO.getUf().equals(cepD.getUf())) {
            dias = 1;
            if (cepO.getDdd().equals(cepD.getDdd())) {
                dias = 3;
            }
        }
        return dias;
    }



}
