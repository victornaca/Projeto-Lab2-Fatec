package fatec.lp.service;

import java.util.List;

import fatec.lp.entity.Leilao;
import fatec.lp.entity.LocalLeilao;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class LocalLeilaoService {
	
	@Transactional
	public void cadastrarLocalLeilao (LocalLeilao localLeilao) {
		localLeilao.persist();
	}
	
	public List<LocalLeilao> listarLocalLeiloes(){
		return LocalLeilao.listAll();
	}
	
	public LocalLeilao listarLocalLeilaoId(Long id) {
		return LocalLeilao.findById(id);
	}
	
	@Transactional
	public LocalLeilao atualizarLocalLeilao(Long id, LocalLeilao localLeilaoAtualizado) {
		LocalLeilao localLeilao = LocalLeilao.findById(id);
		localLeilao.setEndereco(localLeilaoAtualizado.getEndereco());
		localLeilao.setCidade(localLeilaoAtualizado.getCidade());
		localLeilao.setEstado(localLeilaoAtualizado.getEstado());
		return localLeilao;
	}
	
	@Transactional
	public void deletarLocalLeilao(Long id, LocalLeilao localLeilaol) {
		LocalLeilao.deleteById(id);
	}
}
