package fatec.lp.resource;

import java.util.List;

import fatec.lp.entity.Cliente;
import fatec.lp.service.ClienteService;
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
	ClienteService clienteservice;
	
	@POST
	public Response cadastrarCliente(Cliente cliente) {
		clienteservice.cadastrarcliente(cliente);
		return Response.status(Response.Status.CREATED).build();
	}
	
	@GET
	public List<Cliente> listarCliente(){
		return clienteservice.listarclientes();
	}
	
	@GET
	@Path("{id}")
	public Cliente listarClienteId(@PathParam("id") Long id) {
		return 	clienteservice.listarClienteId(id);
	}
	
	@PUT
	@Path("{id}")
	public Response atualizarCliente(@PathParam("id") Long id, Cliente clienteAtualizado) {
		clienteservice.atualizarCliente(id, clienteAtualizado);
		return Response.status(Response.Status.OK).build();
	}
	
	@DELETE
	@Path("{id}")
	public Response deletarCliente(@PathParam("id") Long id, Cliente cliente) {
		clienteservice.deletarCliente(id, cliente);
		return Response.noContent().build();
	}

}
