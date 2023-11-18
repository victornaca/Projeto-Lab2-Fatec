package br.gov.sp.fatec.service;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import br.gov.sp.fatec.dto.InstituicaoFinanceiraDTO;
import br.gov.sp.fatec.dto.LeilaoDTO;
import br.gov.sp.fatec.entity.DispositivoInformatica;
import br.gov.sp.fatec.entity.InstituicaoFinanceira;
import br.gov.sp.fatec.entity.Leilao;
import br.gov.sp.fatec.entity.LeilaoInstituicaoFinanceira;
import br.gov.sp.fatec.entity.Veiculo;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class LeilaoService implements PanacheRepositoryBase<Leilao, Long> {

	private ModelMapper modelMapper;

	public LeilaoService() {
		this.modelMapper = new ModelMapper();
	}

	@Transactional
	public LeilaoDTO cadastrarLeilao(LeilaoDTO leilaoDTO) {
		Leilao leilao = modelMapper.map(leilaoDTO, Leilao.class);

		if (leilaoDTO.getLeilaoInstituicao() == null || leilaoDTO.getLeilaoInstituicao().isEmpty()) {
			throw new IllegalArgumentException("Pelo menos uma instituição financeira deve ser informada.");
		}

		List<InstituicaoFinanceira> instituicoes = new ArrayList<>();
		for (Long instituicaoId : leilaoDTO.getLeilaoInstituicaoid()) {
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
		return modelMapper.map(leilao, LeilaoDTO.class);
	}

	public List<Leilao> listarLeiloes() {
		return Leilao.listAll();
	}

	public Leilao listarLeilaoId(Long id) {
		return Leilao.findById(id);
	}

	@Transactional
	public LeilaoDTO atualizarLeilao(Long id, LeilaoDTO leilaoDTO) {
		Leilao leilao = Leilao.findById(id);
		if (leilao != null) {
			modelMapper.map(leilaoDTO, leilao);
			leilao.persist();
			return modelMapper.map(leilao, LeilaoDTO.class);
		} else {
			return null;
		}
	}

	@Transactional
	public void deletarLeilao(Long id, Leilao leilao) {
		Leilao.deleteById(id);
	}

	public List<Leilao> listarLeiloesByDataOcorrencia() {
		return Leilao.listAll(Sort.by("dataOcorrencia"));
	}

	@Transactional
	public void atualizarStatusLeiloes() {
		List<Leilao> leiloes = listAll();

		LocalDateTime now = LocalDateTime.now();

		for (Leilao leilao : leiloes) {
			LocalDateTime dataOcorrencia = leilao.getDataOcorrencia();
			LocalDateTime dataVisita = leilao.getDataVisita();

			String novoStatus;
			if (now.isBefore(dataOcorrencia)) {
				novoStatus = "EM ABERTO";
			} else if (now.isAfter(dataOcorrencia) && now.isBefore(dataVisita)) {
				novoStatus = "EM ANDAMENTO";
			} else {
				novoStatus = "FINALIZADO";
			}

			leilao.setStatus(novoStatus);

			persist(leilao);
		}
	}

	@Transactional
	public LeilaoDTO detalharLeilao(Long id) {
		Leilao leilao = Leilao.findById(id);
		if (leilao == null) {
			return null;
		}

		LeilaoDTO leilaoDTO = new LeilaoDTO();

		leilaoDTO.setId(leilao.getId());
		leilaoDTO.setDataOcorrencia(leilao.getDataOcorrencia());
		leilaoDTO.setDataVisita(leilao.getDataVisita());
		leilaoDTO.setStatus(leilao.getStatus());
		leilaoDTO.setEndereco(leilao.getEndereco());
		leilaoDTO.setCidade(leilao.getCidade());
		leilaoDTO.setEstado(leilao.getEstado());

		List<LeilaoInstituicaoFinanceira> leilaoInstituicoes = leilao.getLeilaoInstituicoes();
		List<InstituicaoFinanceiraDTO> instituicoesDTO = leilaoInstituicoes.stream()
				.map(relacao -> InstituicaoFinanceiraDTO.fromEntity(relacao.getInstituicaoFinanceira()))
				.collect(Collectors.toList());

		leilaoDTO.setLeilaoInstituicao(instituicoesDTO);
		
		List<DispositivoInformatica> dispositivos = DispositivoInformatica.list("leilao", leilao);
		leilaoDTO
				.setDispositivos(dispositivos.stream().map(DispositivoInformatica::toDTO).collect(Collectors.toList()));

		List<Veiculo> veiculos = Veiculo.list("leilao", leilao);
		leilaoDTO.setVeiculos(veiculos.stream().map(Veiculo::toDTO).collect(Collectors.toList()));

		return leilaoDTO;

	}
}
