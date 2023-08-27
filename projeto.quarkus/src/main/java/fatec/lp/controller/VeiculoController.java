package fatec.lp.controller;

import java.util.List;

import fatec.lp.entity.Veiculo;
import fatec.lp.service.VeiculoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/veiculo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VeiculoController {
	
	@Inject
    private VeiculoService veiculoService;

	@GET
    public List<Veiculo> listarVeiculos() {
        return veiculoService.listarVeiculos();
    }
}

