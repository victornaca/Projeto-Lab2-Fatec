package fatec.lp.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("CARRO")
public class Carro extends Veiculo {
    private int numeroPortas;
    private int capacidadePassageiros;
    private String combustivel;
    private String tipoTransmissao;
    private boolean arCondicionado;
    private boolean travaEletrica;
}
