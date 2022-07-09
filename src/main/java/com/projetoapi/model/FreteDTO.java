package com.projetoapi.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FreteDTO {

    protected Long id;

    private double peso;
    private String cepOrigem;
    private String cepDestino;
    private String nomeDestinatario;

}
