package br.gov.sp.fatec.dto;

import br.gov.sp.fatec.entity.DispositivoInformatica;
import br.gov.sp.fatec.entity.Veiculo;
import lombok.Data;

@Data
public class ProdutoDTO {
	
	private Veiculo veiculo;
	private DispositivoInformatica dispositivoInformatica; 

}
