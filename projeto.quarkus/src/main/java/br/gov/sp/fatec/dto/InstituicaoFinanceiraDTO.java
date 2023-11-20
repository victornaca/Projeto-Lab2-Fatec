package br.gov.sp.fatec.dto;

import br.gov.sp.fatec.entity.InstituicaoFinanceira;
import lombok.Data;

@Data
public class InstituicaoFinanceiraDTO {
	
	private Long id;
	private String nome;
	private String cnpj;
	
	
	public static InstituicaoFinanceiraDTO fromEntity(InstituicaoFinanceira instituicao) {
			InstituicaoFinanceiraDTO dto = new InstituicaoFinanceiraDTO();
	        dto.setId(instituicao.getId());
	        dto.setNome(instituicao.getNome());
	        dto.setCnpj(instituicao.getCnpj());
	        return dto;
	    }

	
}
