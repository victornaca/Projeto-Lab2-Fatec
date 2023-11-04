package br.gov.sp.fatec.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("MOTOCICLETA")
public class Motocicleta extends Veiculo {
    private String tipoMotocicleta;
    private int cilindrada;
    private boolean partidaEletrica;
    private boolean freioAbs;
}
