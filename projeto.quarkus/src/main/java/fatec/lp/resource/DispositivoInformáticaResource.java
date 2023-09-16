package fatec.lp.resource;

import java.util.List;

import fatec.lp.entity.DispositivoInformática;
import fatec.lp.service.DispositivoInformáticaService;
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
public class DispositivoInformáticaResource {

	@Inject
	DispositivoInformáticaService dispositivoInformáticaservice;
	
	@POST
	public Response cadastrarDispositivoInformática(DispositivoInformática dispositivoInformáticaliente) {
		dispositivoInformáticaservice.cadastrarDispositivoInformática(dispositivoInformáticaliente);
		return Response.status(Response.Status.CREATED).build();
	}
	
	@GET
	public List<DispositivoInformática> listarDispositivoInformática(){
		return dispositivoInformáticaservice.ListarDispositivoInformática();
	}
	
	@GET
	@Path("{id}")
	public DispositivoInformática listarDispositivoInformáticaId(@PathParam("id") Long id) {
		return 	dispositivoInformáticaservice.listarDispositivoInformáticaId(id);
	}
	
	@PUT
	@Path("{id}")
	public Response atualizarDispositivoInformática(@PathParam("id") Long id, DispositivoInformática dispositivoInformáticaAtualizado) {
		dispositivoInformáticaservice.atualizarDispositivoInformática(id, dispositivoInformáticaAtualizado);
		return Response.status(Response.Status.OK).build();
	}
	
	@DELETE
	@Path("{id}")
	public Response deletarDispositivoInformática(@PathParam("id") Long id, DispositivoInformática dispositivoInformática) {
		dispositivoInformáticaservice.deletarDispositivoInformática(id, dispositivoInformática);
		return Response.noContent().build();
	}
}
