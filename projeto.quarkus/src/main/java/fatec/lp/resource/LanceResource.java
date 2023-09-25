package fatec.lp.resource;

import java.util.List;

import fatec.lp.entity.Lance;
import fatec.lp.service.LanceService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/lance")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LanceResource {

	@Inject
	private LanceService lanceService;
	
	@GET
	public List<Lance> listarLances(){
		return lanceService.listarLances();
	}

	@PUT
	@Path("vincular-veiculo/{veiculoId}/{clienteId}")
	public Response vincularLanceAoVeiculo(@PathParam("veiculoId") Long veiculoId,
			@PathParam("clienteId") Long clienteId, Double valor) {
		Lance lance = lanceService.vincularLanceAoVeiculo(veiculoId, clienteId, valor);
		if (lance == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		return Response.status(Response.Status.OK).entity(lance).build();
	}

	@PUT
	@Path("vincular-dispositivo/{dispositivoId}/{clienteId}")
	public Response vincularLanceAoDispositivo(@PathParam("dispositivoId") Long dispositivoId,
			@PathParam("clienteId") Long clienteId, Double valor) {
		Lance lance = lanceService.vincularLanceAoDispositivo(dispositivoId, clienteId, valor);
		if (lance == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		return Response.status(Response.Status.OK).entity(lance).build();
	}
}
