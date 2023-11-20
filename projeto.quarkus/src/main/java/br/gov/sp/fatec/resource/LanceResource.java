
package br.gov.sp.fatec.resource;

import java.util.List;

import br.gov.sp.fatec.dto.LanceDTO;
import br.gov.sp.fatec.entity.Lance;
import br.gov.sp.fatec.service.LanceService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/lance")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LanceResource {

	@Inject
	private LanceService lanceService;

	@GET
	public List<Lance> listarLances() {
		return lanceService.listarLances();
	}

	@POST
	@Path("vincular-veiculo")
	public Response vincularLanceAoVeiculo(@QueryParam("veiculoId") Long veiculoId, @QueryParam("clienteId") Long clienteId, LanceDTO lanceDTO) {
	    lanceDTO.setVeiculoId(veiculoId);
	    lanceDTO.setClienteId(clienteId);

	    LanceDTO lance = lanceService.vincularLanceAoVeiculo(lanceDTO);
	    if (lance == null) {
	        return Response.status(Response.Status.BAD_REQUEST).build();
	    }
	    return Response.status(Response.Status.OK).entity(lance).build();
	}

	@POST
	@Path("vincular-dispositivo")
	public Response vincularLanceAoDispositivo(@QueryParam("dispositivoId") Long dispositivoId, @QueryParam("clienteId") Long clienteId, LanceDTO lanceDTO) {
	    lanceDTO.setDispositivoId(dispositivoId);
	    lanceDTO.setClienteId(clienteId);

	    LanceDTO lance = lanceService.vincularLanceAoDispositivo(lanceDTO);
	    if (lance == null) {
	        return Response.status(Response.Status.BAD_REQUEST).build();
	    }
	    return Response.status(Response.Status.OK).entity(lance).build();
	}
	@GET
	@Path("/lances-por-produto")
	public List<Lance> buscarLancesPorTipo(@QueryParam("produto") Long produto) {
		return lanceService.buscarLancesPorProduto(produto);
	}
}
