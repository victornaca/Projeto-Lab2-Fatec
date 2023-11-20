package br.gov.sp.fatec.dto;

import lombok.Data;

@Data
public class VeiculoDTO {
	private Long id;
	private String marca;
	private String modelo;
	private Integer ano;
	private String estadoConservacao;
	private String status;
}
