package br.gov.sp.fatec.resource;

import java.util.List;

import br.gov.sp.fatec.dto.CaminhaoDTO;
import br.gov.sp.fatec.dto.CarroDTO;
import br.gov.sp.fatec.dto.MotocicletaDTO;
import br.gov.sp.fatec.dto.VeiculoDTO;
import br.gov.sp.fatec.entity.Caminhao;
import br.gov.sp.fatec.entity.Carro;
import br.gov.sp.fatec.entity.Motocicleta;
import br.gov.sp.fatec.entity.Veiculo;
import br.gov.sp.fatec.service.VeiculoService;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
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
        return cadastrarVeiculo(carroDTO, Carro.class);
    }

    @POST
    @Path("/cadastrar-caminhao")
    public Response cadastrarCaminhao(CaminhaoDTO caminhaoDTO) {
        return cadastrarVeiculo(caminhaoDTO, Caminhao.class);
    }

    @POST
    @Path("/cadastrar-moto")
    public Response cadastrarMotocicleta(MotocicletaDTO motocicletaDTO) {
        return cadastrarVeiculo(motocicletaDTO, Motocicleta.class);
    }

    private Response cadastrarVeiculo(VeiculoDTO veiculoDTO, Class<? extends PanacheEntityBase> entityClass) {
        VeiculoDTO veiculoCriadoDTO = veiculoService.cadastrarVeiculo(veiculoDTO, entityClass);
        if (veiculoCriadoDTO != null) {
            return Response.status(Response.Status.CREATED).entity(veiculoCriadoDTO).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
    
    @PUT
    @Path("/atualizar-carro/{id}")
    public Response atualizarCarro(@PathParam("id") Long id, CarroDTO carroDTO) {
        return atualizarVeiculo(id, carroDTO, Carro.class);
    }

    @PUT
    @Path("/atualizar-caminhao/{id}")
    public Response atualizarCaminhao(@PathParam("id") Long id, CaminhaoDTO caminhaoDTO) {
        return atualizarVeiculo(id, caminhaoDTO, Caminhao.class);
    }

    @PUT
    @Path("/atualizar-moto/{id}")
    public Response atualizarMotocicleta(@PathParam("id") Long id, MotocicletaDTO motocicletaDTO) {
        return atualizarVeiculo(id, motocicletaDTO, Motocicleta.class);
    }

    private Response atualizarVeiculo(Long id, VeiculoDTO veiculoDTO, Class<? extends PanacheEntityBase> entityClass) {
        VeiculoDTO veiculoAtualizadoDTO = veiculoService.atualizarVeiculo(id, veiculoDTO, entityClass);
        if (veiculoAtualizadoDTO != null) {
            return Response.ok(veiculoAtualizadoDTO).build();
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
}
