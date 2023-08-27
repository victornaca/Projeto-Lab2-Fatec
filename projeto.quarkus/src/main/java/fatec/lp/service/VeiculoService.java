package fatec.lp.service;

import java.util.List;

import fatec.lp.entity.Veiculo;
import fatec.lp.repository.VeiculoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class VeiculoService{
	
	@Inject
    private VeiculoRepository veiculoRepo;
	
	public List<Veiculo> listarVeiculos() {
		return veiculoRepo.listAll();
	}

}
