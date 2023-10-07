package fatec.lp.DTO;

import lombok.Data;

@Data
public class TabletDTO extends DispositivoInformaticaDTO{
	private String fabricante;
	private Double tamanhoTela;
	private String características; 
	private String memoria;
	private String memoriaRAM;
}
