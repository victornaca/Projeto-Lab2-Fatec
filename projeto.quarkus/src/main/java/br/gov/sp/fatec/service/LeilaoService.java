package br.gov.sp.fatec.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;

import br.gov.sp.fatec.dto.DetalhesLeilaoDTO;
import br.gov.sp.fatec.dto.DispositivoInformaticaDTO;
import br.gov.sp.fatec.dto.LanceDTO;
import br.gov.sp.fatec.dto.LeilaoDTO;
import br.gov.sp.fatec.dto.VeiculoDTO;
import br.gov.sp.fatec.entity.InstituicaoFinanceira;
import br.gov.sp.fatec.entity.Lance;
import br.gov.sp.fatec.entity.Leilao;
import br.gov.sp.fatec.entity.LeilaoInstituicaoFinanceira;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class LeilaoService implements PanacheRepositoryBase<Leilao, Long> {

	@Inject
	VeiculoService veiculoService;
	
	@Inject
	DispositivoInformaticaService dispositivoInformaticaService;
	
	@Inject
	LanceService lanceService;
	
	private ModelMapper modelMapper;

	public LeilaoService() {
		this.modelMapper = new ModelMapper();
	}

	@Inject
	private EntityManager em;

	@Transactional
	public LeilaoDTO cadastrarLeilao(LeilaoDTO leilaoDTO) {
		Leilao leilao = modelMapper.map(leilaoDTO, Leilao.class);
		
		leilaoDTO.setStatus(null);
		
        if (leilaoDTO.getLeilaoInstituicaoIds() == null || leilaoDTO.getLeilaoInstituicaoIds().isEmpty()) {
            throw new IllegalArgumentException("Pelo menos uma instituição financeira deve ser informada.");
        }

        List<InstituicaoFinanceira> instituicoes = new ArrayList<>();
        for (Long instituicaoId : leilaoDTO.getLeilaoInstituicaoIds()) {
            InstituicaoFinanceira instituicao = InstituicaoFinanceira.findById(instituicaoId);
            if (instituicao != null) {
                instituicoes.add(instituicao);
            } else {
                throw new IllegalArgumentException("Instituição financeira com ID " + instituicaoId + " não encontrada.");
            }
        }

        leilao.setLeilaoInstituicoes(new ArrayList<>());
        for (InstituicaoFinanceira instituicao : instituicoes) {
            LeilaoInstituicaoFinanceira relacao = new LeilaoInstituicaoFinanceira();
            relacao.setInstituicaoFinanceira(instituicao);
            relacao.setLeilao(leilao);
            leilao.getLeilaoInstituicoes().add(relacao);
        }
        
        leilao.definirStatusComBaseNoHorario();

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
	
	public List<Leilao> listarLeiloesByDataInicio() {
		return Leilao.listAll(Sort.by("dataInicio"));
	}

	@Transactional
	public List<Object> filtrarProdutosPorFaixaDeValores(Long leilaoId, Double valorMin, Double valorMax) {
		Leilao leilao = Leilao.findById(leilaoId);

		if (leilao != null) {
			List<Lance> lances = Lance.find("valorInicial >= ?1 and valorInicial <= ?2", valorMin, valorMax).list();

			List<Object> produtos = lances.stream().filter(lance -> lance.getVeiculo() != null)
					.flatMap(lance -> Stream.of(lance.getVeiculo(), lance.getDispositivo())).filter(Objects::nonNull)
					.distinct().collect(Collectors.toList());

			return produtos;
		}

		return Collections.emptyList();
	}

	@Transactional
	public List<Object> filtrarProdutosPorFaixaDeValoresAdicionais(Long leilaoId, Double valorMin, Double valorMax) {
		Leilao leilao = Leilao.findById(leilaoId);

		if (leilao != null) {
			List<Lance> lances = Lance.find("valorInicial >= ?1 and valorAdicional <= ?2", valorMin, valorMax).list();

			List<Object> produtos = lances.stream().filter(lance -> lance.getVeiculo() != null)
					.flatMap(lance -> Stream.of(lance.getVeiculo(), lance.getDispositivo())).filter(Objects::nonNull)
					.distinct().collect(Collectors.toList());

			return produtos;
		}

		return Collections.emptyList();
	}
	
	@Transactional
    public DetalhesLeilaoDTO ProdutosDeLeilaoByNome(Long leilaoId, String buscaNome) {
        Leilao leilao = Leilao.findById(leilaoId);

        if (leilao == null) {
            return null;
        }

        DetalhesLeilaoDTO detalhesLeilaoDTO = new DetalhesLeilaoDTO();
        detalhesLeilaoDTO.setId(leilaoId);
        detalhesLeilaoDTO.setDataInicio(leilao.getDataInicio());
        detalhesLeilaoDTO.setDataFim(leilao.getDataFim());
        detalhesLeilaoDTO.setStatus(leilao.getStatus());
        detalhesLeilaoDTO.setEndereco(leilao.getEndereco());
        detalhesLeilaoDTO.setCidade(leilao.getCidade());
        detalhesLeilaoDTO.setEstado(leilao.getEstado());

        Response responseVeiculos = veiculoService.listarVeiculosAssociadosLeilaoByNome(leilaoId, buscaNome);
        if (responseVeiculos.getStatus() == Response.Status.OK.getStatusCode()) {
            List<VeiculoDTO> veiculos = (List<VeiculoDTO>) responseVeiculos.getEntity();
            detalhesLeilaoDTO.setVeiculos(veiculos);
        }
        
        Response responseDispositivo = dispositivoInformaticaService.listarDispositivosAssociadosLeilaoByNome(leilaoId, buscaNome);
        if (responseDispositivo.getStatus() == Response.Status.OK.getStatusCode()) {
            List<DispositivoInformaticaDTO> dispositivo = (List<DispositivoInformaticaDTO>) responseDispositivo.getEntity();
            detalhesLeilaoDTO.setDispositivosInformatica(dispositivo);
        }
        
        return detalhesLeilaoDTO;
    }
	
	@Transactional
	public DetalhesLeilaoDTO obterDetalhesLeilao(Long leilaoId) {
	    Leilao leilao = Leilao.findById(leilaoId);

	    if (leilao == null) {
	        return null;
	    }

	    DetalhesLeilaoDTO detalhesLeilaoDTO = new DetalhesLeilaoDTO();
	    detalhesLeilaoDTO.setId(leilaoId);
	    detalhesLeilaoDTO.setDataInicio(leilao.getDataInicio());
	    detalhesLeilaoDTO.setDataFim(leilao.getDataFim());
	    detalhesLeilaoDTO.setStatus(leilao.getStatus());
	    detalhesLeilaoDTO.setEndereco(leilao.getEndereco());
	    detalhesLeilaoDTO.setCidade(leilao.getCidade());
	    detalhesLeilaoDTO.setEstado(leilao.getEstado());

	    Response responseVeiculos = veiculoService.listarVeiculoLeilao(leilaoId);
	    if (responseVeiculos.getStatus() == Response.Status.OK.getStatusCode()) {
	        List<VeiculoDTO> veiculos = (List<VeiculoDTO>) responseVeiculos.getEntity();
	        detalhesLeilaoDTO.setVeiculos(veiculos);

	        if ("FINALIZADO".equals(leilao.getStatus()) && !veiculos.isEmpty()) {
	            List<LanceDTO> todosLances = new ArrayList<>();

	            for (VeiculoDTO veiculo : veiculos) {
	                Long veiculoId = veiculo.getId();

	                Response responseLances = lanceService.listarLancesPorProduto(veiculoId);
	                if (responseLances.getStatus() == Response.Status.OK.getStatusCode()) {
	                    List<LanceDTO> lances = (List<LanceDTO>) responseLances.getEntity();
	                    todosLances.addAll(lances);
	                }
	            }

	            detalhesLeilaoDTO.setLances(todosLances);
	        }
	    }

	    Response responseDispositivo = dispositivoInformaticaService.listarDispositivoLeilao(leilaoId);
	    if (responseDispositivo.getStatus() == Response.Status.OK.getStatusCode()) {
	        List<DispositivoInformaticaDTO> dispositivos = (List<DispositivoInformaticaDTO>) responseDispositivo.getEntity();
	        detalhesLeilaoDTO.setDispositivosInformatica(dispositivos);

	        if ("FINALIZADO".equals(leilao.getStatus()) && !dispositivos.isEmpty()) {
	            List<LanceDTO> todosLances = new ArrayList<>();

	            for (DispositivoInformaticaDTO dispositivo : dispositivos) {
	                Long dispositivoId = dispositivo.getId();

	                Response responseLances = lanceService.listarLancesPorProduto(dispositivoId);
	                if (responseLances.getStatus() == Response.Status.OK.getStatusCode()) {
	                    List<LanceDTO> lances = (List<LanceDTO>) responseLances.getEntity();
	                    todosLances.addAll(lances);
	                }
	            }

	            detalhesLeilaoDTO.setLances(todosLances);
	        }
	    }

	    return detalhesLeilaoDTO;
	}

	
}

