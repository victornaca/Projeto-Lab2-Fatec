package br.gov.sp.fatec.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("TABLET")
public class Tablet extends DispositivoInformatica {
	private String fabricante;
	private Double tamanhoTela;
	private String características; 
	private String memoria;
	private String memoriaRAM;
}
