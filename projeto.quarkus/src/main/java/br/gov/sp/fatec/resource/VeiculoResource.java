package br.gov.sp.fatec.resource;

import java.util.List;

import br.gov.sp.fatec.dto.CaminhaoDTO;
import br.gov.sp.fatec.dto.CarroDTO;
import br.gov.sp.fatec.dto.MotocicletaDTO;
import br.gov.sp.fatec.dto.VeiculoDTO;
import br.gov.sp.fatec.entity.Veiculo;
import br.gov.sp.fatec.service.VeiculoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/veiculos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VeiculoResource {

    @Inject
    VeiculoService veiculoService;
    
    
    @GET
    public List<Veiculo
    > listarVeiculos() {
        return veiculoService.listarVeiculos();
    }
    
    @GET
    @Path("{id}")
    public Response listarVeiculoId(@PathParam("id") Long id) {
    	Veiculo veiculo = Veiculo.findById(id);
    	if (veiculo != null) {
    		VeiculoDTO veiculoDTO = veiculo.toDTO();
    		return Response.ok(veiculoDTO).build();
    	} else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @DELETE
    @Path("{id}")
    public Response deletarVeiculo(@PathParam("id") Long id, Veiculo veiculo) {
    	veiculoService.deletarVeiculo(id, veiculo);
    	return Response.noContent().build();
    }
    
    @POST
    @Path("/cadastrar-carro")
    public Response cadastrarCarro(CarroDTO carroDTO) {
    	VeiculoDTO carroCriadoDTO = veiculoService.cadastrarCarro(carroDTO);
        if (carroCriadoDTO != null) {
            return Response.status(Response.Status.CREATED).entity(carroCriadoDTO).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
    
    @POST
    @Path("/cadastrar-caminhao")
    public Response cadastrarCaminhao(CaminhaoDTO caminhaoDTO) {
    	VeiculoDTO caminhaoCriadoDTO = veiculoService.cadastrarCaminhao(caminhaoDTO);
        if (caminhaoCriadoDTO != null) {
            return Response.status(Response.Status.CREATED).entity(caminhaoCriadoDTO).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path("/cadastrar-moto")
    public Response cadastrarMotocicleta(MotocicletaDTO motocicletaDTO) {
    	VeiculoDTO motocicletaCriadoDTO = veiculoService.cadastrarMotocicleta(motocicletaDTO);
        if (motocicletaCriadoDTO != null) {
            return Response.status(Response.Status.CREATED).entity(motocicletaCriadoDTO).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
    
    @PUT
    @Path("/atualizar-carro/{id}")
    public Response atualizarCarro(@PathParam("id") Long id, CarroDTO carroDTO) {
    	VeiculoDTO carro = veiculoService.atualizarCarro(id,carroDTO);
    	if (carro != null) {
            return Response.ok(carro).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @PUT
    @Path("/atualizar-caminhao/{id}")
    public Response atualizarCaminhao(@PathParam("id") Long id, CaminhaoDTO caminhaoDTO) {
    	VeiculoDTO caminhao = veiculoService.atualizarCaminhao(id,caminhaoDTO);
    	if (caminhao != null) {
            return Response.ok(caminhao).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @PUT
    @Path("/atualizar-moto/{id}")
    public Response atualizarMotocicleta(@PathParam("id") Long id, MotocicletaDTO motocicletaDTO) {
    	VeiculoDTO motocicleta = veiculoService.atualizarMotocicleta(id,motocicletaDTO);
    	if (motocicleta != null) {
            return Response.ok(motocicleta).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @PUT
    @Path("vincular-veiculo/{veiculoId}/{leilaoId}")
    public Response vincularLeilao(@PathParam("veiculoId") Long veiculoId, @PathParam("leilaoId") Long leilaoId) {
    	VeiculoDTO veiculoDTO = veiculoService.vincularLeilao(veiculoId, leilaoId);
        return Response.status(Response.Status.OK).entity(veiculoDTO).build();
    }
    
    @GET
    @Path("/veiculo-por-leilao/{leilaoId}")
    public Response listarVeiculoAssociadoLeilao(@PathParam("leilaoId")Long leilaoId) {
    	Response veiculo = veiculoService.listarVeiculoAssociadoLeilao(leilaoId);
    	return veiculo;
    }
    
    @GET
    @Path("/{leilaoId}/veiculos")
    public Response listarVeiculoAssociadoLeilao(@PathParam("leilaoId") Long leilaoId, @QueryParam("buscaNome") String buscaNome) {

        if (leilaoId == null) {
            throw new WebApplicationException("Leilão Nulo", Response.Status.NOT_FOUND);
        }

        List<VeiculoDTO> veiculoDTOs = veiculoService.listarVeiculosAssociadosLeilaoByNome(leilaoId, buscaNome);

        if (veiculoDTOs.isEmpty()) {
            throw new WebApplicationException("Veículos não encontrados para o Leilão informado", Response.Status.NOT_FOUND);
        }

        return Response.status(Response.Status.OK).entity(veiculoDTOs).build();
    }
}
