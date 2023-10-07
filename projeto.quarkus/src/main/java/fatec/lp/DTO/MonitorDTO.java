package fatec.lp.DTO;

import lombok.Data;

@Data
public class MonitorDTO extends DispositivoInformaticaDTO{
	private String fabricante;
	private Double tamanhoTela;
	private String recursos;
	private String entradas;
	private String peso; 
}
