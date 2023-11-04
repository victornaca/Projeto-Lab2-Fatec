package br.gov.sp.fatec.entity;

import org.modelmapper.ModelMapper;

import br.gov.sp.fatec.dto.DispositivoInformaticaDTO;
import br.gov.sp.fatec.dto.NotebookDTO;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("NOTEBOOK")
public class Notebook extends DispositivoInformatica {

	private String fabricante;
	private String processador;
	private Double tamanhoTela;
	private String memoria;
	private String memoriaRAM;
	private String peso;
	
	public Notebook() {

    }
	
	@Override
	public DispositivoInformaticaDTO toDTO() {
		ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, NotebookDTO.class);
	} 


}
