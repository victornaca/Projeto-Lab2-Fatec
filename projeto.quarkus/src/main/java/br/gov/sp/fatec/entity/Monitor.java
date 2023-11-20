package br.gov.sp.fatec.entity;

import org.modelmapper.ModelMapper;

import br.gov.sp.fatec.dto.DispositivoInformaticaDTO;
import br.gov.sp.fatec.dto.MonitorDTO;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("MONITOR")
public class Monitor extends DispositivoInformatica {
	private String fabricante;
	private Double tamanhoTela;
	private String recursos;
	private String entradas;
	private String peso;
	
	public Monitor() {

    }
	
	@Override
	public DispositivoInformaticaDTO toDTO() {
		ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, MonitorDTO.class);
	} 
}
