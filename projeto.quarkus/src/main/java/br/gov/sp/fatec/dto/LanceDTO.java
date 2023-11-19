package br.gov.sp.fatec.dto;

import java.util.Date;

import lombok.Data;

@Data
public class LanceDTO {

	private Long veiculoId;
	private Long dispositivoId;
	private Long clienteId;
	private Double valor;
	private Double valorInicial;
	private Double valorAdicional;

}
