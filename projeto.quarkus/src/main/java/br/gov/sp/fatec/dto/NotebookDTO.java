package br.gov.sp.fatec.dto;

import lombok.Data;

@Data
public class NotebookDTO extends DispositivoInformaticaDTO{
	private String fabricante;
	private String processador;
	private Double tamanhoTela;
	private String memoria;
	private String memoriaRAM;
	private String peso; 
}
