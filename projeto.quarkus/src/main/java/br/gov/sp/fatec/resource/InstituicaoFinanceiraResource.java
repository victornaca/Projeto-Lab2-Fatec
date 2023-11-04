package br.gov.sp.fatec.resource;

import java.util.List;

import br.gov.sp.fatec.entity.Cliente;
import br.gov.sp.fatec.entity.InstituicaoFinanceira;
import br.gov.sp.fatec.service.ClienteService;
import br.gov.sp.fatec.service.InstituicaoFinanceiraService;
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

@Path("/api/instituicoes-financeiras")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InstituicaoFinanceiraResource {


	@Inject
	InstituicaoFinanceiraService instituicaoFinanceiraservice;
	
	@POST
	public Response cadastrarInstituicaoFinanceira(InstituicaoFinanceira instituicaoFinanceira) {
		instituicaoFinanceiraservice.cadastrarInstituicaoFinanceira(instituicaoFinanceira);
		return Response.status(Response.Status.CREATED).build();
	}
	
	@GET
	public List<InstituicaoFinanceira> listarInstituicaoFinanceira(){
		return instituicaoFinanceiraservice.ListarinstituicaoFinanceira();
	}
	
	@GET
	@Path("{id}")
	public InstituicaoFinanceira listarInstituicaoFinanceiraId(@PathParam("id") Long id) {
		return instituicaoFinanceiraservice.listarInstituicaoFinanceiraId(id);
	}
	
	@PUT
	@Path("{id}")
	public Response atualizarInstituicaoFinanceira(@PathParam("id") Long id, InstituicaoFinanceira instituicaoFinanceiraAtualizado) {
		instituicaoFinanceiraservice.atualizarInstituicaoFinanceira(id, instituicaoFinanceiraAtualizado);
		return Response.status(Response.Status.OK).build();
	}
	
	@DELETE
	@Path("{id}")
	public Response deletarInstituicaoFinanceira(@PathParam("id") Long id, InstituicaoFinanceira instituicaoFinanceira) {
		instituicaoFinanceiraservice.deletarInstituicaoFinanceira(id, instituicaoFinanceira);
		return Response.noContent().build();
	}
}
