package fatec.lp.service;

import java.util.List;

import fatec.lp.entity.Leilao;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class LeilaoService {
	
	@Transactional
	public void cadastrarLeilao (Leilao leilao) {
		leilao.persist();
	}
	
	public List<Leilao> listarLeiloes(){
		return Leilao.listAll();
	}
	
	public Leilao listarLeilaoId(Long id) {
		return Leilao.findById(id);
	}
	
	@Transactional
	public Leilao atualizarLeilao(Long id, Leilao leilaoAtualizado) {
		Leilao leilao = Leilao.findById(id);
		leilao.setLocal(leilaoAtualizado.getLocal());
		leilao.setDataOcorrencia(leilaoAtualizado.getDataOcorrencia());
		leilao.setDataVisita(leilaoAtualizado.getDataVisita());
		return leilao;
	}
	
	@Transactional
	public void deletarLeilao(Long id, Leilao leilao) {
		Leilao.deleteById(id);
	}
}
