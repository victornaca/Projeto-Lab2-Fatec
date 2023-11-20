package br.gov.sp.fatec.entity;

import org.modelmapper.ModelMapper;

import br.gov.sp.fatec.dto.CarroDTO;
import br.gov.sp.fatec.dto.VeiculoDTO;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("CARRO")
public class Carro extends Veiculo {
    private int numeroPortas;
    private int capacidadePassageiros;
    private String combustivel;
    private String tipoTransmissao;
    private boolean arCondicionado;
    private boolean travaEletrica;
    
    public Carro() {

    }
    
	@Override
	public VeiculoDTO toDTO() {
		ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, CarroDTO.class);
	}
}
