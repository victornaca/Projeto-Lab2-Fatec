package br.gov.sp.fatec.service;

import java.util.List;

import org.modelmapper.ModelMapper;

import br.gov.sp.fatec.dto.InstituicaoFinanceiraDTO;
import br.gov.sp.fatec.entity.InstituicaoFinanceira;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class InstituicaoFinanceiraService {
	
	private ModelMapper modelMapper;

    public InstituicaoFinanceiraService() {
        this.modelMapper = new ModelMapper();
    }
	
    @Transactional
    public void cadastrarInstituicaoFinanceira(InstituicaoFinanceiraDTO instituicaoFinanceiraDTO) {
        InstituicaoFinanceira instituicaoFinanceira = modelMapper.map(instituicaoFinanceiraDTO, InstituicaoFinanceira.class);
        instituicaoFinanceira.persist();
    }
	
	public List<InstituicaoFinanceira> ListarinstituicaoFinanceira(){
		return InstituicaoFinanceira.listAll();
	}
	
	public InstituicaoFinanceira listarInstituicaoFinanceiraId(Long id) {
		return InstituicaoFinanceira.findById(id);
	}
	
	@Transactional
	public InstituicaoFinanceiraDTO atualizarInstituicaoFinanceira(Long id, InstituicaoFinanceiraDTO instituicaoFinanceiraDTO) {
		InstituicaoFinanceira financeira = InstituicaoFinanceira.findById(id);
    	if (financeira != null) {
    		modelMapper.map(instituicaoFinanceiraDTO, financeira);
    		financeira.persist();
    		return modelMapper.map(financeira, InstituicaoFinanceiraDTO.class);
    	} else {
            return null;
        }
	}
	
	@Transactional
	public void deletarInstituicaoFinanceira(Long id, InstituicaoFinanceira instituicaoFinanceira) {
		InstituicaoFinanceira.deleteById(id);
	}
}
