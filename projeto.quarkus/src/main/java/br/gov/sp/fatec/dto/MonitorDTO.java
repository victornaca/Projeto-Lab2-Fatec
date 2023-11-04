package br.gov.sp.fatec.dto;

import lombok.Data;

@Data
public class MonitorDTO extends DispositivoInformaticaDTO{
	private String fabricante;
	private Double tamanhoTela;
	private String recursos;
	private String entradas;
	private String peso; 
}
