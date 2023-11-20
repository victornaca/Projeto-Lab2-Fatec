package br.gov.sp.fatec.entity;

import org.modelmapper.ModelMapper;

import br.gov.sp.fatec.dto.MotocicletaDTO;
import br.gov.sp.fatec.dto.VeiculoDTO;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("MOTOCICLETA")
public class Motocicleta extends Veiculo {
    private String tipoMotocicleta;
    private int cilindrada;
    private boolean partidaEletrica;
    private boolean freioAbs;
    
    public Motocicleta() {
    	
    }
    
	@Override
	public VeiculoDTO toDTO() {
		ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, MotocicletaDTO.class);
	}
}
