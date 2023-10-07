package fatec.lp.DTO;

import lombok.Data;

@Data
public class CelularDTO extends DispositivoInformaticaDTO {
	private String fabricante;
	private String sistemaOperacional;
	private Double tamanhoTela;
	private String memoria;
	private String camera;
}
