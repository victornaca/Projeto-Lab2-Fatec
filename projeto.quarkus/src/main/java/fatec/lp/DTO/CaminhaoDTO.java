package fatec.lp.DTO;

import lombok.Data;

@Data
public class CaminhaoDTO extends VeiculoDTO {
	private int capacidadeCarga;
    private String tipoCarroceria;
    private int numeroEixos;
    private boolean temCarreta;
}
