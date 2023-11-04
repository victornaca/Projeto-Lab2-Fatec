package br.gov.sp.fatec.dto;

import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data
public class LeilaoDTO {

	private Date dataOcorrencia;
	private Date dataVisita;
	private String status;
	private String endereco;
	private String cidade;
	private String estado;
	private List<Long> leilaoInstituicaoIds;

}
