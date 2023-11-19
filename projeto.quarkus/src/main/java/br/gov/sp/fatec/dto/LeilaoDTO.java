package br.gov.sp.fatec.dto;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import br.gov.sp.fatec.entity.DispositivoInformatica;
import lombok.Data;

@Data
public class LeilaoDTO {

	private Long id;
	private LocalDateTime dataOcorrencia;
	private LocalDateTime dataVisita;
	private String status;
	private String endereco;
	private String cidade;
	private String estado;
	private List<Long> leilaoInstituicaoid;
	private List<InstituicaoFinanceiraDTO> leilaoInstituicao;
	private List<DispositivoInformaticaDTO> dispositivos;
	private List<VeiculoDTO> veiculos;

}
