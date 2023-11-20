package br.gov.sp.fatec.service;

import java.util.List;

import org.modelmapper.ModelMapper;

import br.gov.sp.fatec.dto.ClienteDTO;
import br.gov.sp.fatec.entity.Cliente;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ClienteService {
	
	private ModelMapper modelMapper;

    public ClienteService() {
        this.modelMapper = new ModelMapper();
    }
	
	@Transactional
	public void cadastrarcliente (ClienteDTO clienteDTO) {
		Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);
        cliente.persist();
	}
	
	public List<Cliente> listarclientes(){
		return Cliente.listAll();
	}
	
	public Cliente listarClienteId(Long id) {
		return Cliente.findById(id);
	}
	
	@Transactional
    public ClienteDTO atualizarCliente(Long id, ClienteDTO clienteDTO) {
    	Cliente cliente = Cliente.findById(id);
    	if (cliente != null) {
    		modelMapper.map(clienteDTO, cliente);
    		cliente.persist();
    		return modelMapper.map(cliente, ClienteDTO.class);
    	} else {
            return null;
        }
    }
	
	@Transactional
	public void deletarCliente(Long id, Cliente cliente) {
		Cliente.deleteById(id);
	}
	
}
