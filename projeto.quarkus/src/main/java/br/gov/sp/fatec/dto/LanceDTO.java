package br.gov.sp.fatec.dto;

import java.util.Date;
import java.util.List;

import br.gov.sp.fatec.entity.Cliente;
import lombok.Data;

@Data
public class LanceDTO {

	private Long veiculoId;
	private Long dispositivoId;
	private Long clienteId;
	private Date dataHora;
	private Double valorInicial;
	private Double valorAdicional;

	private ClienteDTO cliente;
}
