package br.gov.sp.fatec.service;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;

import br.gov.sp.fatec.dto.LanceDTO;
import br.gov.sp.fatec.entity.Cliente;
import br.gov.sp.fatec.entity.DispositivoInformatica;
import br.gov.sp.fatec.entity.Lance;
import br.gov.sp.fatec.entity.Veiculo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class LanceService {

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
	        return null;
	    }

	    Lance lance = new Lance();
	    lance.setDataHora(new Date());
	    lance.setValor(lanceDTO.getValor());
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
	        return null;
	    }

	    Lance lance = new Lance();
	    lance.setDataHora(new Date());
	    lance.setValor(lanceDTO.getValor());
	    lance.setDispositivo(dispositivo);
	    lance.setCliente(cliente);

	    lance.persist();
	    dispositivo.persist();

	    return modelMapper.map(lance, LanceDTO.class);
	}
}
