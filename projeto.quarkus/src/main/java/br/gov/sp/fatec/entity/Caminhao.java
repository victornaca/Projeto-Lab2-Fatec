package br.gov.sp.fatec.entity;

import org.modelmapper.ModelMapper;

import br.gov.sp.fatec.dto.CaminhaoDTO;
import br.gov.sp.fatec.dto.VeiculoDTO;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("CAMINHAO")
public class Caminhao extends Veiculo {
    private int capacidadeCarga;
    private String tipoCarroceria;
    private int numeroEixos;
    private boolean temCarreta;
    
    public Caminhao() {

    }
    
	@Override
	public VeiculoDTO toDTO() {
		ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, CaminhaoDTO.class);
	}
}
