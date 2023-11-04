package br.gov.sp.fatec.resource;

import java.util.List;

import br.gov.sp.fatec.dto.CelularDTO;
import br.gov.sp.fatec.dto.DispositivoInformaticaDTO;
import br.gov.sp.fatec.dto.MonitorDTO;
import br.gov.sp.fatec.dto.NotebookDTO;
import br.gov.sp.fatec.dto.TabletDTO;
import br.gov.sp.fatec.entity.DispositivoInformatica;
import br.gov.sp.fatec.service.DispositivoInformaticaService;
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
	
	@POST
	@Path("/cadastrar-celular")
	public Response cadastrarCelular(CelularDTO celularDTO) {
		DispositivoInformaticaDTO celularCriadoDTO = dispositivoInformaticaService.cadastrarCelular(celularDTO);
		if (celularCriadoDTO != null) {
            return Response.status(Response.Status.CREATED).entity(celularCriadoDTO).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }	
	}

	@POST
	@Path("/cadastrar-monitor")
	public Response cadastrarMonitor(MonitorDTO monitorDTO) {
		DispositivoInformaticaDTO monitorCriadoDTO = dispositivoInformaticaService.cadastrarMonitor(monitorDTO);
		if (monitorCriadoDTO != null) {
            return Response.status(Response.Status.CREATED).entity(monitorCriadoDTO).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }	
	}

	@POST
	@Path("/cadastrar-notebook")
	public Response cadastrarNotebook(NotebookDTO notebookDTO) {
		DispositivoInformaticaDTO notebookCriadoDTO = dispositivoInformaticaService.cadastrarNotebook(notebookDTO);
		if (notebookCriadoDTO != null) {
            return Response.status(Response.Status.CREATED).entity(notebookCriadoDTO).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }	
	}

	@POST
	@Path("/cadastrar-tablet")
	public Response cadastrarTablet(TabletDTO tabletDTO) {
		DispositivoInformaticaDTO tabletCriadoDTO = dispositivoInformaticaService.cadastrarTablet(tabletDTO);
		if (tabletCriadoDTO != null) {
            return Response.status(Response.Status.CREATED).entity(tabletCriadoDTO).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }	
	}

	@PUT
	@Path("/atualizar-celular/{id}")
	public Response atualizarCelular(@PathParam("id") Long id, CelularDTO celularDTO) {
		DispositivoInformaticaDTO celular = dispositivoInformaticaService.atualizarCelular(id,celularDTO);
    	if (celular != null) {
            return Response.ok(celular).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

	@PUT
	@Path("/atualizar-monitor/{id}")
	public Response atualizarMonitor(@PathParam("id") Long id, MonitorDTO monitorDTO) {
		DispositivoInformaticaDTO monitor = dispositivoInformaticaService.atualizarMonitor(id,monitorDTO);
    	if (monitor != null) {
            return Response.ok(monitor).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

	@PUT
	@Path("/atualizar-notebook/{id}")
	public Response atualizarNotebook(@PathParam("id") Long id, NotebookDTO notebookDTO) {
		DispositivoInformaticaDTO notebook = dispositivoInformaticaService.atualizarNotebook(id,notebookDTO);
    	if (notebook != null) {
            return Response.ok(notebook).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

	@PUT
	@Path("/atualizar-tablet/{id}")
	public Response atualizarTablet(@PathParam("id") Long id, TabletDTO tabletDTO) {
		DispositivoInformaticaDTO tablet = dispositivoInformaticaService.atualizarTablet(id,tabletDTO);
    	if (tablet != null) {
            return Response.ok(tablet).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


	@PUT
	@Path("vincular-dispositivo/{dispositivoId}/{leilaoId}")
	public Response vincularLeilao(@PathParam("dispositivoId") Long dispositivoId, @PathParam("leilaoId") Long leilaoId) {
		DispositivoInformaticaDTO dispositivoInformaticaDTO = dispositivoInformaticaService.vincularLeilao(dispositivoId, leilaoId);
        return Response.status(Response.Status.OK).entity(dispositivoInformaticaDTO).build();

	}
	
    @GET
    @Path("/dispositivo-por-leilao/{leilaoId}")
    public Response listarDispositivoAssociadoLeilao(@PathParam("leilaoId")Long leilaoId) {
    	Response dispositivo =  dispositivoInformaticaService.listarDispositivoAssociadoLeilao(leilaoId);
    	return dispositivo;
    }
}
