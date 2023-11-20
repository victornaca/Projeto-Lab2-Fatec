package br.gov.sp.fatec.resource;

import java.util.List;

import br.gov.sp.fatec.dto.CelularDTO;
import br.gov.sp.fatec.dto.DispositivoInformaticaDTO;
import br.gov.sp.fatec.dto.MonitorDTO;
import br.gov.sp.fatec.dto.NotebookDTO;
import br.gov.sp.fatec.dto.TabletDTO;
import br.gov.sp.fatec.dto.VeiculoDTO;
import br.gov.sp.fatec.entity.Carro;
import br.gov.sp.fatec.entity.Celular;
import br.gov.sp.fatec.entity.DispositivoInformatica;
import br.gov.sp.fatec.entity.Monitor;
import br.gov.sp.fatec.entity.Notebook;
import br.gov.sp.fatec.entity.Tablet;
import br.gov.sp.fatec.service.DispositivoInformaticaService;
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
	public Response deletarDispositivoInformatica(@PathParam("id") Long id, DispositivoInformatica dispositivoInformatica) {
		dispositivoInformaticaService.deletarDispositivoInformatica(id, dispositivoInformatica);
		return Response.noContent().build();
	}
	
	@POST
	@Path("/cadastrar-celular")
	public Response cadastrarCelular(CelularDTO celularDTO) {
		return cadastrarDispositivo(celularDTO, Celular.class);
	}

	@POST
	@Path("/cadastrar-monitor")
	public Response cadastrarMonitor(MonitorDTO monitorDTO) {
		return cadastrarDispositivo(monitorDTO, Monitor.class);
	}

	@POST
	@Path("/cadastrar-notebook")
	public Response cadastrarNotebook(NotebookDTO notebookDTO) {
		return cadastrarDispositivo(notebookDTO, Notebook.class);
	}

	@POST
	@Path("/cadastrar-tablet")
	public Response cadastrarTablet(TabletDTO tabletDTO) {
		return cadastrarDispositivo(tabletDTO, Tablet.class);
	}
	
	private Response cadastrarDispositivo(DispositivoInformaticaDTO dispositivoInformaticaDTO, Class<? extends PanacheEntityBase> entityClass) {
		DispositivoInformaticaDTO dispostivoCriadoDTO = dispositivoInformaticaService.cadastrarDispositivo(dispositivoInformaticaDTO, entityClass);
        if (dispostivoCriadoDTO != null) {
            return Response.status(Response.Status.CREATED).entity(dispostivoCriadoDTO).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

	@PUT
	@Path("/atualizar-celular/{id}")
	public Response atualizarCelular(@PathParam("id") Long id, CelularDTO celularDTO) {
		return atualizarDispositivo(id, celularDTO, Celular.class);
    }

	@PUT
	@Path("/atualizar-monitor/{id}")
	public Response atualizarMonitor(@PathParam("id") Long id, MonitorDTO monitorDTO) {
		return atualizarDispositivo(id, monitorDTO, Monitor.class);
    }

	@PUT
	@Path("/atualizar-notebook/{id}")
	public Response atualizarNotebook(@PathParam("id") Long id, NotebookDTO notebookDTO) {
		return atualizarDispositivo(id, notebookDTO, Notebook.class);
    }

	@PUT
	@Path("/atualizar-tablet/{id}")
	public Response atualizarTablet(@PathParam("id") Long id, TabletDTO tabletDTO) {
		return atualizarDispositivo(id, tabletDTO, Tablet.class);
    }

	private Response atualizarDispositivo(Long id, DispositivoInformaticaDTO dispositivoInformaticaDTO, Class<? extends PanacheEntityBase> entityClass) {
		DispositivoInformaticaDTO dispositvoAtualizadoDTO = dispositivoInformaticaService.atualizarDispositivo(id, dispositivoInformaticaDTO, entityClass);
        if (dispositvoAtualizadoDTO != null) {
            return Response.ok(dispositvoAtualizadoDTO).build();
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
