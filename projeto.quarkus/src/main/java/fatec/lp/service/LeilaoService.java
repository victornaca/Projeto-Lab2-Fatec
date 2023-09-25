package fatec.lp.service;

import java.util.List;

import fatec.lp.DTO.LeilaoDTO;
import fatec.lp.entity.InstituicaoFinanceira;
import fatec.lp.entity.Leilao;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class LeilaoService {

	@Transactional
	public Leilao cadastrarLeilao(LeilaoDTO leilaoDTO) {
		if (leilaoDTO.getInstituicaoIds() == null || leilaoDTO.getInstituicaoIds().isEmpty()) {
			throw new IllegalArgumentException("Pelo menos uma instituição financeira deve ser informada.");
		}

		Leilao leilao = new Leilao();
		leilao.setDataOcorrencia(leilaoDTO.getDataOcorrencia());
		leilao.setDataVisita(leilaoDTO.getDataVisita());
		leilao.setStatus(leilaoDTO.getStatus());
		leilao.setEndereco(leilaoDTO.getEndereco());
		leilao.setCidade(leilaoDTO.getCidade());
		leilao.setEstado(leilaoDTO.getEstado());

		List<InstituicaoFinanceira> instituicoes = InstituicaoFinanceira.list("id in (?1)",
				leilaoDTO.getInstituicaoIds());
		leilao.setInstituicoes(instituicoes);

		leilao.persist();
		return leilao;
	}

	public List<Leilao> listarLeiloes() {
		return Leilao.listAll();
	}

	public Leilao listarLeilaoId(Long id) {
		return Leilao.findById(id);
	}

	@Transactional
	public Leilao atualizarLeilao(Long id, Leilao leilaoAtualizado) {
		Leilao leilao = Leilao.findById(id);
		leilao.setDataOcorrencia(leilaoAtualizado.getDataOcorrencia());
		leilao.setDataVisita(leilaoAtualizado.getDataVisita());
		leilao.setStatus(leilaoAtualizado.getStatus());
		leilao.setEndereco(leilaoAtualizado.getEndereco());
		leilao.setCidade(leilaoAtualizado.getCidade());
		leilao.setEstado(leilaoAtualizado.getEstado());
		return leilao;
	}

	@Transactional
	public void deletarLeilao(Long id, Leilao leilao) {
		Leilao.deleteById(id);
	}
}
