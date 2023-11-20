package br.gov.sp.fatec.resource;

import java.util.List;

import br.gov.sp.fatec.dto.ClienteDTO;
import br.gov.sp.fatec.dto.LeilaoDTO;
import br.gov.sp.fatec.entity.Cliente;
import br.gov.sp.fatec.service.ClienteService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteResource {
	
	@Inject
	ClienteService clienteService;
	
	@POST
	public Response cadastrarCliente(ClienteDTO clienteDTO) {
		clienteService.cadastrarcliente(clienteDTO);
		return Response.status(Response.Status.CREATED).build();
	}
	
	@GET
	public List<Cliente> listarCliente(){
		return clienteService.listarclientes();
	}
	
	@GET
	@Path("{id}")
	public Cliente listarClienteId(@PathParam("id") Long id) {
		return 	clienteService.listarClienteId(id);
	}
	
	@PUT
	@Path("{id}")
	public Response atualizarCliente(@PathParam("id") Long id, ClienteDTO clienteDTO) {
		ClienteDTO cliente = clienteService.atualizarCliente(id,clienteDTO);
    	if (cliente != null) {
            return Response.ok(cliente).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
	}
	
	@DELETE
	@Path("{id}")
	public Response deletarCliente(@PathParam("id") Long id, Cliente cliente) {
		clienteService.deletarCliente(id, cliente);
		return Response.noContent().build();
	}

}
