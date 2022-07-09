package com.projetoapi.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

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

    private double peso;
    private String cepOrigem;
    private String cepDestino;
    private String nomeDestinatario;
    private double vlTotalFrete;
    private LocalDateTime dataConsulta;
    private LocalDateTime dataPrevistaEntrega;
}
