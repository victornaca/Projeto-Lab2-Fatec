package br.gov.sp.fatec.service;

import java.util.List;

import br.gov.sp.fatec.entity.Cliente;
import br.gov.sp.fatec.entity.DispositivoInformatica;
import br.gov.sp.fatec.entity.Leilao;
import br.gov.sp.fatec.entity.Veiculo;
import br.gov.sp.fatec.exporter.EntityExporter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ExportService {

	@Inject
	LeilaoService leilaoService;
	
	@Inject
	VeiculoService veiculoService;
	
	@Inject
	DispositivoInformaticaService dispositivoInformaticaService;
	
	@Inject
	ClienteService clienteService;
	
    public void exportarDetalhesLeiloesParaDET() {
        String nomeArquivo = "leiloes.DET";

        List<Leilao> leiloes = leilaoService.listarLeiloesByDataOcorrencia();

        EntityExporter.exportarDetalhesParaDET(leiloes, nomeArquivo);
    }
    
    public void exportarDetalhesVeiculosParaDET() {
        String nomeArquivo = "veiculos.DET";

        List<Veiculo> veiculos = veiculoService.listarVeiculos();

        EntityExporter.exportarDetalhesParaDET(veiculos, nomeArquivo);
    }
    
    public void exportarDetalhesDispositivosParaDET() {
        String nomeArquivo = "dispositivos.DET";

        List<DispositivoInformatica> dispositivos = dispositivoInformaticaService.ListarDispositivoInformatica();

        EntityExporter.exportarDetalhesParaDET(dispositivos, nomeArquivo);
    }
    
    public void exportarDetalhesClienteParaDET() {
        String nomeArquivo = "clientes.DET";

        List<Cliente> clientes = clienteService.listarclientes();

        EntityExporter.exportarDetalhesParaDET(clientes, nomeArquivo);
    }
	
}
