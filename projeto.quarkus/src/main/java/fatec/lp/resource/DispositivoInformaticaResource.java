package fatec.lp.resource;

import java.util.List;

import fatec.lp.DTO.CelularDTO;
import fatec.lp.DTO.MonitorDTO;
import fatec.lp.DTO.NotebookDTO;
import fatec.lp.DTO.TabletDTO;
import fatec.lp.entity.Celular;
import fatec.lp.entity.DispositivoInformatica;
import fatec.lp.entity.Monitor;
import fatec.lp.entity.Notebook;
import fatec.lp.entity.Tablet;
import fatec.lp.entity.Veiculo;
import fatec.lp.resource.Request.VincularLeilaoRequest;
import fatec.lp.service.DispositivoInformaticaService;
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

@Path("/api/dispositivos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DispositivoInformaticaResource {

	@Inject
	DispositivoInformaticaService dispositivoInformaticaService;

	@POST
	@Path("/cadastrar-celular")
	public Response cadastrarCelular(CelularDTO celularDTO) {
		CelularDTO createdCelular = dispositivoInformaticaService.cadastrarCelular(celularDTO);
		return Response.status(Response.Status.CREATED).entity(createdCelular).build();
	}

	@POST
	@Path("/cadastrar-monitor")
	public Response cadastrarMonitor(MonitorDTO monitorDTO) {
		MonitorDTO createdMonitor = dispositivoInformaticaService.cadastrarMonitor(monitorDTO);
		return Response.status(Response.Status.CREATED).entity(createdMonitor).build();
	}

	@POST
	@Path("/cadastrar-notebook")
	public Response cadastrarNotebook(NotebookDTO notebookDTO) {
		NotebookDTO createdNotebook = dispositivoInformaticaService.cadastrarNotebook(notebookDTO);
		return Response.status(Response.Status.CREATED).entity(createdNotebook).build();
	}

	@POST
	@Path("/cadastrar-tablet")
	public Response cadastrarTablet(TabletDTO tabletDTO) {
		TabletDTO createdTablet = dispositivoInformaticaService.cadastrarTablet(tabletDTO);
		return Response.status(Response.Status.CREATED).entity(createdTablet).build();
	}

	@PUT
	@Path("/atualizar-celular/{id}")
	public Response atualizarCelular(@PathParam("id") Long id, CelularDTO celularAtualizado) {
		Response celular = dispositivoInformaticaService.atualizarCelular(id,celularAtualizado);
    	return celular;
	}

	@PUT
	@Path("/atualizar-monitor/{id}")
	public Response atualizarMonitor(@PathParam("id") Long id, MonitorDTO monitorAtualizado) {
		Response monitor = dispositivoInformaticaService.atualizarMonitor(id,monitorAtualizado);
    	return monitor;
	}

	@PUT
	@Path("/atualizar-notebook/{id}")
	public Response atualizarNotebook(@PathParam("id") Long id, NotebookDTO notebookAtualizado) {
		Response notebook = dispositivoInformaticaService.atualizarNotebook(id,notebookAtualizado);
    	return notebook;
	}

	@PUT
	@Path("/atualizar-tablet/{id}")
	public Response atualizarTablet(@PathParam("id") Long id, TabletDTO tabletAtualizado) {
		Response tablet = dispositivoInformaticaService.atualizarTablet(id,tabletAtualizado);
    	return tablet;
	}

	@GET
	public List<DispositivoInformatica> listarDispositivoInformatica() {
		return dispositivoInformaticaService.ListarDispositivoInformatica();
	}

	@GET
	@Path("{id}")
	public DispositivoInformatica listarDispositivoInformaticaId(@PathParam("id") Long id) {
		return dispositivoInformaticaService.listarDispositivoInformaticaId(id);
	}

	@DELETE
	@Path("{id}")
	public Response deletarDispositivoInformatica(@PathParam("id") Long id,
			DispositivoInformatica dispositivoInformatica) {
		dispositivoInformaticaService.deletarDispositivoInformatica(id, dispositivoInformatica);
		return Response.noContent().build();
	}

	@PUT
	@Path("vincular-dispositivo/{dispositivoId}/{leilaoId}")
	public Response vincularLeilao(@PathParam("dispositivoId") Long dispositivoId, @PathParam("leilaoId") Long leilaoId) {
		Response dispositivo = dispositivoInformaticaService.vincularLeilao(dispositivoId, leilaoId);
		return dispositivo;

	}
	
    @GET
    @Path("/dispositivo-por-leilao/{leilaoId}")
    public Response listarDispositivoAssociadoLeilao(@PathParam("leilaoId")Long leilaoId) {
    	Response dispositivo =  dispositivoInformaticaService.listarDispositivoAssociadoLeilao(leilaoId);
    	return dispositivo;
    }
}
