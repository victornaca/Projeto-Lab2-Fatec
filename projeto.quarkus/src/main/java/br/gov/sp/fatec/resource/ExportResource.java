package br.gov.sp.fatec.resource;

import java.io.File;
import java.net.URI;

import br.gov.sp.fatec.service.ExportService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/exportar")
@Produces(MediaType.APPLICATION_JSON)
public class ExportResource {

    @Inject
    ExportService exportService;

    @GET
    @Path("/leiloes")
    @Produces("application/octet-stream")
    public Response exportarDetalhesLeiloesParaDET() {
        exportService.exportarDetalhesLeiloesParaDET();
        File file = new File("leiloes.DET");

        URI uri = file.toURI();

        Response.ResponseBuilder response = Response.ok(file, "application/octet-stream");
        response.header("Content-Disposition", "attachment;filename=leiloes.DET");

        response.header("Link", "<" + uri.toString() + ">; rel=download");

        return response.build();
    }
    
    @GET
    @Path("/veiculos")
    @Produces("application/octet-stream")
    public Response exportarDetalhesVeiculosParaDET() {
        exportService.exportarDetalhesVeiculosParaDET();
        File file = new File("veiculos.DET");

        URI uri = file.toURI();

        Response.ResponseBuilder response = Response.ok(file, "application/octet-stream");
        response.header("Content-Disposition", "attachment;filename=veiculos.DET");

        response.header("Link", "<" + uri.toString() + ">; rel=download");

        return response.build();
    }
    
    @GET
    @Path("/dispositivos")
    @Produces("application/octet-stream")
    public Response exportarDetalhesDispositivosParaDET() {
        exportService.exportarDetalhesDispositivosParaDET();
        File file = new File("dispositivos.DET");

        URI uri = file.toURI();

        Response.ResponseBuilder response = Response.ok(file, "application/octet-stream");
        response.header("Content-Disposition", "attachment;filename=dispositivos.DET");

        response.header("Link", "<" + uri.toString() + ">; rel=download");

        return response.build();
    }
    
    @GET
    @Path("/clientes")
    @Produces("application/octet-stream")
    public Response exportarDetalhesClienteParaDET() {
        exportService.exportarDetalhesClienteParaDET();
        File file = new File("clientes.DET");

        URI uri = file.toURI();

        Response.ResponseBuilder response = Response.ok(file, "application/octet-stream");
        response.header("Content-Disposition", "attachment;filename=clientes.DET");

        response.header("Link", "<" + uri.toString() + ">; rel=download");

        return response.build();
    }
}
