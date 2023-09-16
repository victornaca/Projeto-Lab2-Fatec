package fatec.lp.resource;

import java.util.List;

import fatec.lp.entity.Veiculo;
import fatec.lp.resource.Request.VincularLeilaoRequest;
import fatec.lp.service.VeiculoService;
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

@Path("/veiculos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VeiculoResource {

    @Inject
    VeiculoService veiculoService;

    @POST
    public Response cadastrarVeiculo(Veiculo veiculo) {
        veiculoService.cadastrarVeiculo(veiculo);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    public List<Veiculo> listarVeiculos() {
        return veiculoService.listarVeiculos();
    }
    
    @GET
    @Path("{id}")
    public Veiculo listarVeiculoId(@PathParam("id") Long id) {
        return veiculoService.listarVeiculoId(id);
    }
    
    @PUT
    @Path("{id}")
    public Response atualizarVeiculo(@PathParam("id") Long id, Veiculo veiculoAtualizado) {
    	veiculoService.atualizarVeiculo(id,veiculoAtualizado);
    	return Response.status(Response.Status.OK).build();
    }
    
    @DELETE
    @Path("{id}")
    public Response deletarVeiculo(@PathParam("id") Long id, Veiculo veiculo) {
    	veiculoService.deletarVeiculo(id, veiculo);
    	return Response.noContent().build();
    }
    
    @PUT
    @Path("vincular-veiculo/{id}")
    public Response vincularLeilao(@PathParam("id") Long veiculoId, VincularLeilaoRequest request) {
		Veiculo veiculo = veiculoService.vincularLeilao(veiculoId, request);
		if (veiculo == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(veiculo).build();
        }
    	return Response.status(Response.Status.OK).entity(veiculo).build();
    	
    }
}
