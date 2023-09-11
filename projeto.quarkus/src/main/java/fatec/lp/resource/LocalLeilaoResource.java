package fatec.lp.resource;

import java.util.List;

import fatec.lp.entity.Leilao;
import fatec.lp.entity.LocalLeilao;
import fatec.lp.service.LocalLeilaoService;
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

@Path("/leilao/local")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LocalLeilaoResource {
	
	@Inject
	LocalLeilaoService localLeilaoService;
	
	@POST
	public Response cadastrarLocalLeilao(LocalLeilao localLeilao) {
		localLeilaoService.cadastrarLocalLeilao(localLeilao);
		return Response.status(Response.Status.CREATED).build();
	}
	
	@GET
	public List<LocalLeilao> listarLocalLeiloes(){
		return localLeilaoService.listarLocalLeiloes();
	}
	
	@GET
	@Path("{id}")
	public LocalLeilao listarLocalLeilaoId(@PathParam("id") Long id) {
		return localLeilaoService.listarLocalLeilaoId(id);
	}
	
	@PUT
	@Path("{id}")
	public Response atualizarLocalLeilao(@PathParam("id") Long id, LocalLeilao localLeilaoAtualizado) {
		localLeilaoService.atualizarLocalLeilao(id, localLeilaoAtualizado);
		return Response.status(Response.Status.OK).build();
	}
	
	@DELETE
	@Path("{id}")
	public Response deletarLocalLeilao(@PathParam("id") Long id, LocalLeilao localLeilao) {
		localLeilaoService.deletarLocalLeilao(id, localLeilao);
		return Response.noContent().build();
	}
}
