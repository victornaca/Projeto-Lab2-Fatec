package fatec.lp.resource;

import java.util.List;

import fatec.lp.DTO.CaminhaoDTO;
import fatec.lp.DTO.CarroDTO;
import fatec.lp.DTO.MotocicletaDTO;
import fatec.lp.entity.Caminhao;
import fatec.lp.entity.Carro;
import fatec.lp.entity.Motocicleta;
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
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/veiculos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VeiculoResource {

    @Inject
    VeiculoService veiculoService;
    
    @POST
    @Path("/cadastrar-carro")
    public Response cadastrarCarro(CarroDTO carroDTO) {
        veiculoService.cadastrarCarro(carroDTO);
        return Response.status(Response.Status.CREATED).build();
    }
    
    @POST
    @Path("/cadastrar-caminhao")
    public Response cadastrarCaminhao(CaminhaoDTO caminhaoDTO) {
        veiculoService.cadastrarCaminhao(caminhaoDTO);
        return Response.status(Response.Status.CREATED).entity("Caminhão cadastrado com sucesso").build();
    }

    @POST
    @Path("/cadastrar-moto")
    public Response cadastrarMotocicleta(MotocicletaDTO motocicletaDTO) {
        veiculoService.cadastrarMoto(motocicletaDTO);
        return Response.status(Response.Status.CREATED).entity("Motocicleta cadastrada com sucesso").build();
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
    @Path("/atualizar-carro/{id}")
    public Response atualizarCarro(@PathParam("id") Long id, CarroDTO carroAtualizado) {
    	Response carro = veiculoService.atualizarCarro(id,carroAtualizado);
    	return carro;
    }
    
    @PUT
    @Path("/atualizar-caminhao/{id}")
    public Response atualizarCaminhao(@PathParam("id") Long id, CaminhaoDTO caminhaoAtualizado) {
    	Response caminhao = veiculoService.atualizarCaminhao(id,caminhaoAtualizado);
    	return caminhao;
    }
    
    @PUT
    @Path("/atualizar-moto/{id}")
    public Response atualizarMotocicleta(@PathParam("id") Long id, MotocicletaDTO motocicletaAtualizado) {
    	Response motocicleta = veiculoService.atualizarMotocicleta(id,motocicletaAtualizado);
    	return motocicleta;
    }
    
    @DELETE
    @Path("{id}")
    public Response deletarVeiculo(@PathParam("id") Long id, Veiculo veiculo) {
    	veiculoService.deletarVeiculo(id, veiculo);
    	return Response.noContent().build();
    }
    
    @PUT
    @Path("vincular-veiculo/{veiculoId}/{leilaoId}")
    public Response vincularLeilao(@PathParam("veiculoId") Long veiculoId, @PathParam("leilaoId") Long leilaoId) {
		Response veiculo = veiculoService.vincularLeilao(veiculoId, leilaoId);	
		return veiculo;
    }
    
    @GET
    @Path("/veiculo-por-leilao/{leilaoId}")
    public Response listarVeiculoAssociadoLeilao(@PathParam("leilaoId")Long leilaoId) {
    	Response veiculo = veiculoService.listarVeiculoAssociadoLeilao(leilaoId);
    	return veiculo;
    }
}
