package br.gov.sp.fatec.resource;

import java.util.List;

import br.gov.sp.fatec.dto.LanceDTO;
import br.gov.sp.fatec.entity.Lance;
import br.gov.sp.fatec.service.LanceService;
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
	public Response vincularLanceAoVeiculo(LanceDTO lanceDTO) {
	    LanceDTO lance = lanceService.vincularLanceAoVeiculo(lanceDTO);
	    if (lance == null) {
	        return Response.status(Response.Status.BAD_REQUEST).build();
	    }
	    return Response.status(Response.Status.OK).entity(lance).build();
	}

	@PUT
	@Path("vincular-dispositivo/{dispositivoId}/{clienteId}")
	public Response vincularLanceAoDispositivo(LanceDTO lanceDTO) {
	    LanceDTO lance = lanceService.vincularLanceAoDispositivo(lanceDTO);
	    if (lance == null) {
	        return Response.status(Response.Status.BAD_REQUEST).build();
	    }
	    return Response.status(Response.Status.OK).entity(lance).build();
	}
}
