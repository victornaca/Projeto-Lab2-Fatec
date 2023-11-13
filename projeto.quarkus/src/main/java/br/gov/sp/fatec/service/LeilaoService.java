package br.gov.sp.fatec.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import br.gov.sp.fatec.dto.LeilaoDTO;
import br.gov.sp.fatec.entity.Carro;
import br.gov.sp.fatec.entity.InstituicaoFinanceira;
import br.gov.sp.fatec.entity.Leilao;
import br.gov.sp.fatec.entity.LeilaoInstituicaoFinanceira;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class LeilaoService {
	
	private ModelMapper modelMapper;

    public LeilaoService() {
        this.modelMapper = new ModelMapper();
    }

	@Transactional
	public LeilaoDTO cadastrarLeilao(LeilaoDTO leilaoDTO) {
		Leilao leilao = modelMapper.map(leilaoDTO, Leilao.class);

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
	
	public String StatusLeilao(LocalDateTime dataOcorrencia, LocalDateTime dataVisita) {
	    LocalDateTime now = LocalDateTime.now();

	    if (now.isBefore(dataOcorrencia)) {
	        return "EM ABERTO";
	    } else if (now.isAfter(dataOcorrencia) && now.isBefore(dataVisita)) {
	        return "EM ANDAMENTO";
	    } else {
	        return "FINALIZADO";
	    }
	}
}
