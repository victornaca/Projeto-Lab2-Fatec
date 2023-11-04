package br.gov.sp.fatec.entity;

import org.modelmapper.ModelMapper;

import br.gov.sp.fatec.dto.CelularDTO;
import br.gov.sp.fatec.dto.DispositivoInformaticaDTO;
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
	
	public Tablet() {

    }
	
	@Override
	public DispositivoInformaticaDTO toDTO() {
		ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, CelularDTO.class);
	}
}
