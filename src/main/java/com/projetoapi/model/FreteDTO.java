package com.projetoapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class FreteDTO {

    protected Long id;

    private BigDecimal vlTotalFrete;
    private String cepOrigem;
    private String cepDestino;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataPrevistaEntrega;

}
