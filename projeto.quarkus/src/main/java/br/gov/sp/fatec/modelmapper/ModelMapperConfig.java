package br.gov.sp.fatec.modelmapper;

import org.modelmapper.ModelMapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Produces;

@ApplicationScoped
public class ModelMapperConfig {
	
	@Produces
	@ApplicationScoped
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
