package fatec.lp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import fatec.lp.DTO.LeilaoDTO;
import fatec.lp.entity.DispositivoInformatica;
import fatec.lp.entity.InstituicaoFinanceira;
import fatec.lp.entity.Leilao;
import fatec.lp.entity.LeilaoInstituicaoFinanceira;
import fatec.lp.entity.Veiculo;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Parameters;
import java.util.List;

@ApplicationScoped
public class LeilaoService {

	@Inject
	EntityManager entityManager;

	@Transactional
	public Leilao cadastrarLeilao(LeilaoDTO leilaoDTO) {
		if (leilaoDTO.getLeilaoInstituicaoIds() == null || leilaoDTO.getLeilaoInstituicaoIds().isEmpty()) {
			throw new IllegalArgumentException("Pelo menos uma instituição financeira deve ser informada.");
		}

		Leilao leilao = new Leilao();
		leilao.setStatus(leilaoDTO.getStatus());
		leilao.setEndereco(leilaoDTO.getEndereco());
		leilao.setCidade(leilaoDTO.getCidade());
		leilao.setEstado(leilaoDTO.getEstado());
		leilao.setDataOcorrencia(leilaoDTO.getDataOcorrencia());
		leilao.setDataVisita(leilaoDTO.getDataVisita());

		List<InstituicaoFinanceira> instituicoes = new ArrayList<>();
		for (Long instituicaoId : leilaoDTO.getLeilaoInstituicaoIds()) {
			InstituicaoFinanceira instituicao = InstituicaoFinanceira.findById(instituicaoId);
			if (instituicao != null) {
				instituicoes.add(instituicao);
			} else {
				throw new IllegalArgumentException(
						"Instituição financeira com ID " + instituicaoId + " não encontrada.");
			}
		}

		leilao.setLeilaoInstituicoes(new ArrayList<>());
		for (InstituicaoFinanceira instituicao : instituicoes) {
			LeilaoInstituicaoFinanceira relacao = new LeilaoInstituicaoFinanceira();
			relacao.setInstituicaoFinanceira(instituicao);
			relacao.setLeilao(leilao);
			leilao.getLeilaoInstituicoes().add(relacao);
		}

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

	public List<Leilao> listarLeiloesByDataOcorrencia() {
		return Leilao.listAll(Sort.by("dataOcorrencia"));
	}

	@Transactional
	public List<Leilao> buscarLeiloesPorTipo(String tipoExtensao) {
		List<Leilao> leiloes = Leilao.listAll(); // Consulta todos os leilões

		List<Leilao> leiloesFiltrados = leiloes.stream().filter(leilao -> contemExtensao(leilao, tipoExtensao))
				.collect(Collectors.toList());

		return leiloesFiltrados;
	}

	private boolean contemExtensao(Leilao leilao, String tipoExtensao) {
		if (leilao.getVeiculos() != null) {
			for (Veiculo veiculo : leilao.getVeiculos()) {
				if (veiculo.getClass().getSimpleName().equals(tipoExtensao)) {
					return true;
				}
			}
		}

		if (leilao.getDispositivoInformatica() != null) {
			for (DispositivoInformatica dispositivo : leilao.getDispositivoInformatica()) {
				if (dispositivo.getClass().getSimpleName().equals(tipoExtensao)) {
					return true;
				}
			}
		}

		return false;
	}
}
