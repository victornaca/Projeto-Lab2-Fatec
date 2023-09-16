package fatec.lp.resource;

import java.util.List;

import fatec.lp.entity.DispositivoInformatica;
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
	public Response cadastrarDispositivoInformatica(DispositivoInformatica dispositivoInformatica) {
		dispositivoInformaticaService.cadastrarDispositivoInformatica(dispositivoInformatica);
		return Response.status(Response.Status.CREATED).build();
	}
	
	@GET
	public List<DispositivoInformatica> listarDispositivoInformatica(){
		return dispositivoInformaticaService.ListarDispositivoInformatica();
	}
	
	@GET
	@Path("{id}")
	public DispositivoInformatica listarDispositivoInformaticaId(@PathParam("id") Long id) {
		return 	dispositivoInformaticaService.listarDispositivoInformaticaId(id);
	}
	
	@PUT
	@Path("{id}")
	public Response atualizarDispositivoInformatica(@PathParam("id") Long id, DispositivoInformatica dispositivoInformaticaAtualizado) {
		dispositivoInformaticaService.atualizarDispositivoInformatica(id, dispositivoInformaticaAtualizado);
		return Response.status(Response.Status.OK).build();
	}
	
	@DELETE
	@Path("{id}")
	public Response deletarDispositivoInformatica(@PathParam("id") Long id, DispositivoInformatica dispositivoInformatica) {
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
