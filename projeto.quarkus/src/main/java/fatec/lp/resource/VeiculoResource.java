package fatec.lp.resource;

import java.util.List;

import fatec.lp.entity.Veiculo;
import fatec.lp.service.VeiculoService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
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
    @Transactional
    public Response cadastrarVeiculo(Veiculo veiculo) {
        veiculoService.cadastrarVeiculo(veiculo);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    public List<Veiculo> listarVeiculos() {
        return veiculoService.listarVeiculos();
    }
	
}
