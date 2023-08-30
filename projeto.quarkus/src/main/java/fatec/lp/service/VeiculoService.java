package fatec.lp.service;

import java.util.List;

import fatec.lp.entity.Veiculo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class VeiculoService {

    @Transactional
    public void cadastrarVeiculo(Veiculo veiculo) {
        veiculo.persist();
    }

    public List<Veiculo> listarVeiculos() {
        return Veiculo.listAll();
    }
    
	public Veiculo listarVeiculoId(Long id) {
        return Veiculo.findById(id);
    }
	
	@Transactional
	public Veiculo atualizarVeiculo(Long id, Veiculo veiculoAtualizado) {
		Veiculo veiculo = Veiculo.findById(id);
		veiculo.setMarca(veiculoAtualizado.getMarca());
	    veiculo.setModelo(veiculoAtualizado.getModelo());
	    return veiculo;
	}
    
}
