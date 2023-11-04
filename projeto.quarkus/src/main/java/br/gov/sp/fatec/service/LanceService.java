package br.gov.sp.fatec.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import br.gov.sp.fatec.entity.Cliente;
import br.gov.sp.fatec.entity.DispositivoInformatica;
import br.gov.sp.fatec.entity.Lance;
import br.gov.sp.fatec.entity.Veiculo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class LanceService {

	public List<Lance> listarLances() {
		return Lance.listAll();
	}

	@Transactional
	public Lance vincularLanceAoVeiculo(Long veiculoId, Long clienteId, Double valor) {
		Veiculo veiculo = Veiculo.findById(veiculoId);
		Cliente cliente = Cliente.findById(clienteId);

		if (veiculo == null || cliente == null) {
			return null;
		}

		Lance lance = new Lance();
		lance.setDataHora(new Date());
		lance.setValor(valor);
		lance.setVeiculo(veiculo);
		lance.setCliente(cliente);

		lance.persist();

		return lance;
	}

	@Transactional
	public Lance vincularLanceAoDispositivo(Long dispositivoId, Long clienteId, Double valor) {
		DispositivoInformatica dispositivo = DispositivoInformatica.findById(dispositivoId);
		Cliente cliente = Cliente.findById(clienteId);

		if (dispositivo == null || cliente == null) {
			return null;
		}

		Lance lance = new Lance();
		lance.setDataHora(new Date());
		lance.setValor(valor);
		lance.setDispositivo(dispositivo);
		lance.setCliente(cliente);

		lance.persist();
		dispositivo.persist();

		return lance;
	}
}
