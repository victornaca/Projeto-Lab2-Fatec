package fatec.lp.DTO;

import lombok.Data;

@Data
public class CarroDTO extends VeiculoDTO{
	private int numeroPortas;
    private int capacidadePassageiros;
    private String combustivel;
    private String tipoTransmissao;
    private boolean arCondicionado;
    private boolean travaEletrica;
}
