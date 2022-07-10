package com.projetoapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Frete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Peso é requerido!")
    private BigDecimal peso;

    @NotNull(message = "Cep Origem é requerido!")
    private String cepOrigem;

    @NotNull(message = "Cep de Destino é requerido!")
    private String cepDestino;

    @NotNull(message = "Nome do Destinatário é requerido!")
    private String nomeDestinatario;

    private BigDecimal vlTotalFrete;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataConsulta = LocalDate.now();
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataPrevistaEntrega;

}
