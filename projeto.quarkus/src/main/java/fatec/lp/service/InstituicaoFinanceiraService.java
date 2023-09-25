package fatec.lp.service;

import java.util.List;

import fatec.lp.entity.InstituicaoFinanceira;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class InstituicaoFinanceiraService {
	
	@Transactional
	public void cadastrarInstituicaoFinanceira (InstituicaoFinanceira instituicaoFinanceira) {
		instituicaoFinanceira.persist();
	}
	
	public List<InstituicaoFinanceira> ListarinstituicaoFinanceira(){
		return InstituicaoFinanceira.listAll();
	}
	
	public InstituicaoFinanceira listarInstituicaoFinanceiraId(Long id) {
		return InstituicaoFinanceira.findById(id);
	}
	
	@Transactional
	public InstituicaoFinanceira atualizarInstituicaoFinanceira(Long id, InstituicaoFinanceira instituicaoFinanceiraAtualizado) {
		InstituicaoFinanceira instituicaoFinanceira = InstituicaoFinanceira.findById(id);
		instituicaoFinanceira.setNome(instituicaoFinanceiraAtualizado.getNome());
		instituicaoFinanceira.setCnpj(instituicaoFinanceiraAtualizado.getCnpj());
		return instituicaoFinanceira;
	}
	
	@Transactional
	public void deletarInstituicaoFinanceira(Long id, InstituicaoFinanceira instituicaoFinanceira) {
		InstituicaoFinanceira.deleteById(id);
	}
}
