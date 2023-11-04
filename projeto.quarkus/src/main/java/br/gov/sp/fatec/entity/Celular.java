package br.gov.sp.fatec.entity;

import org.modelmapper.ModelMapper;

import br.gov.sp.fatec.dto.CelularDTO;
import br.gov.sp.fatec.dto.DispositivoInformaticaDTO;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("CELULAR")
public class Celular extends DispositivoInformatica {
	
	private String fabricante;
	private String sistemaOperacional;
	private Double tamanhoTela;
	private String memoria;
	private String camera;
	
	public Celular() {

    }
	
	@Override
	public DispositivoInformaticaDTO toDTO() {
		ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, CelularDTO.class);
	}
}
