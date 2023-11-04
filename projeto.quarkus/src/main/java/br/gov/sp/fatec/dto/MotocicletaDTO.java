package br.gov.sp.fatec.dto;

import lombok.Data;

@Data
public class MotocicletaDTO extends VeiculoDTO{
	private String tipoMotocicleta;
    private int cilindrada;
    private boolean partidaEletrica;
    private boolean freioAbs;
}
