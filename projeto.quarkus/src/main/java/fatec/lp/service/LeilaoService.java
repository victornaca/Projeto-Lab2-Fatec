package fatec.lp.service;

import java.util.List;

import fatec.lp.entity.Leilao;
import fatec.lp.entity.LocalLeilao;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class LeilaoService {
	
	@Transactional
	public void cadastrarLeilao (Long localLeilaoId, Leilao leilao) {
		LocalLeilao localLeilao = LocalLeilao.findById(localLeilaoId);
		if (localLeilao == null) {
            throw new IllegalArgumentException("Local de leilão não encontrado.");
        }
		leilao.localLeilao = localLeilao;
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
		leilao.setLocalLeilao(leilaoAtualizado.getLocalLeilao());
		leilao.setDataOcorrencia(leilaoAtualizado.getDataOcorrencia());
		leilao.setDataVisita(leilaoAtualizado.getDataVisita());
		return leilao;
	}
	
	@Transactional
	public void deletarLeilao(Long id, Leilao leilao) {
		Leilao.deleteById(id);
	}
}
