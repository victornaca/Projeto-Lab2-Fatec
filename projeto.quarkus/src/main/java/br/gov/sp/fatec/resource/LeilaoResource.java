package br.gov.sp.fatec.resource;

import java.util.List;

import br.gov.sp.fatec.dto.LeilaoDTO;
import br.gov.sp.fatec.entity.InstituicaoFinanceira;
import br.gov.sp.fatec.entity.Leilao;
import br.gov.sp.fatec.service.LeilaoService;
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

@Path("/api/leilao")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LeilaoResource {

	@Inject
	LeilaoService leilaoService;

	@POST
	public Response cadastrarLeilao(LeilaoDTO leilaoDTO) {
		try {
			Leilao leilao = leilaoService.cadastrarLeilao(leilaoDTO);
			return Response.status(Response.Status.CREATED).entity(leilao).build();
		} catch (IllegalArgumentException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao cadastrar o leilão: " + e.getMessage()).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao cadastrar o leilão: " + e.getMessage()).build();
		}
	}

	@GET
	public List<Leilao> listarLeiloes() {
		return leilaoService.listarLeiloes();
	}

	@GET
	@Path("{id}")
	public Leilao listarLeilaoId(@PathParam("id") Long id) {
		return leilaoService.listarLeilaoId(id);
	}

	@PUT
	@Path("{id}")
	public Response atualizarLeilao(@PathParam("id") Long id, Leilao leilaoAtualizado) {
		leilaoService.atualizarLeilao(id, leilaoAtualizado);
		return Response.status(Response.Status.OK).build();
	}

	@DELETE
	@Path("{id}")
	public Response deletarLeilao(@PathParam("id") Long id, Leilao leilao) {
		leilaoService.deletarLeilao(id, leilao);
		return Response.noContent().build();
	}
	
	@GET
	@Path("/pordataocorrencia")
	public List<Leilao> listarLeiloesByDataOcorrencia(){
		return leilaoService.listarLeiloesByDataOcorrencia();
	}
}