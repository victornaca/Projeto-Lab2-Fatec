package br.gov.sp.fatec.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import br.gov.sp.fatec.dto.DetalhesLeilaoDTO;
import br.gov.sp.fatec.dto.DispositivoInformaticaDTO;
import br.gov.sp.fatec.dto.LeilaoDTO;
import br.gov.sp.fatec.dto.VeiculoDTO;
import br.gov.sp.fatec.entity.InstituicaoFinanceira;
import br.gov.sp.fatec.entity.Leilao;
import br.gov.sp.fatec.entity.LeilaoInstituicaoFinanceira;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class LeilaoService {
	
	private ModelMapper modelMapper;

    public LeilaoService() {
        this.modelMapper = new ModelMapper();
    }

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
	
	@Inject
	VeiculoService veiculoService;
	
	@Inject
	DispositivoInformaticaService dispositivoInformaticaService;
	
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

        Response responseVeiculos = veiculoService.listarVeiculoAssociadoLeilao(leilaoId);
        if (responseVeiculos.getStatus() == Response.Status.OK.getStatusCode()) {
            List<VeiculoDTO> veiculos = (List<VeiculoDTO>) responseVeiculos.getEntity();
            detalhesLeilaoDTO.setVeiculos(veiculos);
        }
        
        Response responseDispositivo = dispositivoInformaticaService.listarDispositivoAssociadoLeilao(leilaoId);
        if (responseDispositivo.getStatus() == Response.Status.OK.getStatusCode()) {
            List<DispositivoInformaticaDTO> dispositivo = (List<DispositivoInformaticaDTO>) responseDispositivo.getEntity();
            detalhesLeilaoDTO.setDispositivosInformatica(dispositivo);
        }
        
        return detalhesLeilaoDTO;
    }
	
}
