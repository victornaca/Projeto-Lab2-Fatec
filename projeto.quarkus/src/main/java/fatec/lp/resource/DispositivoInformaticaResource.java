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
		Celular celularExistente = Celular.findById(id);
		if (celularExistente == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("Celular não encontrado").build();
		}
		dispositivoInformaticaService.atualizarCelular(id, celularAtualizado);
		return Response.status(Response.Status.OK).entity("Celular atualizado com sucesso").build();
	}

	@PUT
	@Path("/atualizar-monitor/{id}")
	public Response atualizarMonitor(@PathParam("id") Long id, MonitorDTO monitorAtualizado) {
		Monitor monitorExistente = Monitor.findById(id);
		if (monitorExistente == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("Monitor não encontrado").build();
		}
		dispositivoInformaticaService.atualizarMonitor(id, monitorAtualizado);
		return Response.status(Response.Status.OK).entity("Monitor atualizado com sucesso").build();
	}

	@PUT
	@Path("/atualizar-notebook/{id}")
	public Response atualizarNotebook(@PathParam("id") Long id, NotebookDTO notebookAtualizado) {
		Notebook notebookExistente = Notebook.findById(id);
		if (notebookExistente == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("Notebook não encontrado").build();
		}
		dispositivoInformaticaService.atualizarNotebook(id, notebookAtualizado);
		return Response.status(Response.Status.OK).entity("Notebook atualizado com sucesso").build();
	}

	@PUT
	@Path("/atualizar-tablet/{id}")
	public Response atualizarTablet(@PathParam("id") Long id, TabletDTO tabletAtualizado) {
		Tablet tabletExistente = Tablet.findById(id);
		if (tabletExistente == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("Tablet não encontrado").build();
		}

		dispositivoInformaticaService.atualizarTablet(id, tabletAtualizado);
		return Response.status(Response.Status.OK).entity("Tablet atualizado com sucesso").build();
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
	@Path("vincular-dispositivo/{id}")
	public Response vincularLeilao(@PathParam("id") Long dispositivoId, VincularLeilaoRequest request) {
		DispositivoInformatica dispositivo = dispositivoInformaticaService.vincularLeilao(dispositivoId, request);
		if (dispositivo == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity(dispositivo).build();
		}
		return Response.status(Response.Status.OK).entity(dispositivo).build();

	}
}
