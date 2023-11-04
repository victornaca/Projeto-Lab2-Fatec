package br.gov.sp.fatec.service;

import java.util.List;

import br.gov.sp.fatec.entity.Cliente;
import br.gov.sp.fatec.entity.Leilao;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ClienteService {
	
	@Transactional
	public void cadastrarcliente (Cliente cliente) {
		cliente.persist();
	}
	
	public List<Cliente> listarclientes(){
		return Cliente.listAll();
	}
	
	public Cliente listarClienteId(Long id) {
		return Cliente.findById(id);
	}
	
	@Transactional
	public Cliente atualizarCliente(Long id, Cliente clienteAtualizado) {
		Cliente cliente = Cliente.findById(id);
		cliente.setEmail(clienteAtualizado.getEmail());
		cliente.setNome(clienteAtualizado.getNome());
		return cliente;
	}
	
	@Transactional
	public void deletarCliente(Long id, Cliente cliente) {
		Cliente.deleteById(id);
	}
	
}
