package br.gov.sp.fatec.service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;

import br.gov.sp.fatec.dto.LanceDTO;
import br.gov.sp.fatec.entity.Cliente;
import br.gov.sp.fatec.entity.DispositivoInformatica;
import br.gov.sp.fatec.entity.Lance;
import br.gov.sp.fatec.entity.Veiculo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class LanceService {

	@Inject
	EntityManager entityManager;

	private ModelMapper modelMapper;

    public LanceService() {
        this.modelMapper = new ModelMapper();
    }
	

	public List<Lance> listarLances() {
		return Lance.listAll();
	}
	
	@Transactional
	public LanceDTO vincularLanceAoVeiculo(LanceDTO lanceDTO) {
	    Veiculo veiculo = Veiculo.findById(lanceDTO.getVeiculoId());
	    Cliente cliente = Cliente.findById(lanceDTO.getClienteId());

	    if (veiculo == null || cliente == null) {
	        throw new EntityNotFoundException("Veículo ou cliente não encontrado");
	    }

	    Lance lance = new Lance();
	    lance.setDataHora(new Date()); // Usando Instant para representar data e hora atual
	    lance.setValorInicial(lanceDTO.getValorInicial());
	    lance.setValorAdicional(lanceDTO.getValorAdicional());
	    lance.setVeiculo(veiculo);
	    lance.setCliente(cliente);

	    lance.persist();

	    return modelMapper.map(lance, LanceDTO.class);
	}

	@Transactional
	public LanceDTO vincularLanceAoDispositivo(LanceDTO lanceDTO) {
	    DispositivoInformatica dispositivo = DispositivoInformatica.findById(lanceDTO.getDispositivoId());
	    Cliente cliente = Cliente.findById(lanceDTO.getClienteId());

	    if (dispositivo == null || cliente == null) {
	        throw new EntityNotFoundException("Dispositivo ou cliente não encontrado");
	    }

	    Lance lance = new Lance();
	    lance.setDataHora(new Date());
	    lance.setValorInicial(lanceDTO.getValorInicial());
	    lance.setValorAdicional(lanceDTO.getValorAdicional());
	    lance.setDispositivo(dispositivo);
	    lance.setCliente(cliente);

	    lance.persist();

	    return modelMapper.map(lance, LanceDTO.class);
	}
	
	@Transactional
	public List<Lance> buscarLancesPorProduto(Long produto) {
		String jpql = "SELECT l FROM Lance l " + "WHERE (l.dispositivo.id = :produto OR l.veiculo.id = :produto)";

		return entityManager.createQuery(jpql, Lance.class).setParameter("produto", produto).getResultList();
	}
}
