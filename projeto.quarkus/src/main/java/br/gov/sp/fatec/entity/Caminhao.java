package br.gov.sp.fatec.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("CAMINHAO")
public class Caminhao extends Veiculo {
    private int capacidadeCarga;
    private String tipoCarroceria;
    private int numeroEixos;
    private boolean temCarreta;
}
