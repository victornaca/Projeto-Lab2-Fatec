package br.gov.sp.fatec.service;

import java.util.List;

import br.gov.sp.fatec.dto.CelularDTO;
import br.gov.sp.fatec.dto.MonitorDTO;
import br.gov.sp.fatec.dto.NotebookDTO;
import br.gov.sp.fatec.dto.TabletDTO;
import br.gov.sp.fatec.entity.Celular;
import br.gov.sp.fatec.entity.DispositivoInformatica;
import br.gov.sp.fatec.entity.Leilao;
import br.gov.sp.fatec.entity.Monitor;
import br.gov.sp.fatec.entity.Notebook;
import br.gov.sp.fatec.entity.Tablet;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class DispositivoInformaticaService {

	@Transactional
	public CelularDTO cadastrarCelular(CelularDTO celularDTO) {
		Celular celular = new Celular();
		celular.setModelo(celularDTO.getModelo());
		celular.setFabricante(celularDTO.getFabricante());
		celular.setSistemaOperacional(celularDTO.getSistemaOperacional());
		celular.setTamanhoTela(celularDTO.getTamanhoTela());
		celular.setMemoria(celularDTO.getMemoria());
		celular.setCamera(celularDTO.getCamera());
		celular.setModelo(celularDTO.getModelo());
		celular.setEstadoConservacao(celularDTO.getEstadoConservacao());
		celular.setStatus("NAO VINCULADO");

		celular.persist();

		return celularDTO;
	}

	@Transactional
	public MonitorDTO cadastrarMonitor(MonitorDTO monitorDTO) {
		Monitor monitor = new Monitor();
		monitor.setModelo(monitorDTO.getModelo());
		monitor.setFabricante(monitorDTO.getFabricante());
		monitor.setEntradas(monitorDTO.getEntradas());
		monitor.setTamanhoTela(monitorDTO.getTamanhoTela());
		monitor.setPeso(monitorDTO.getPeso());
		monitor.setRecursos(monitorDTO.getRecursos());
		monitor.setTipo(monitorDTO.getTipo());
		monitor.setEstadoConservacao(monitorDTO.getEstadoConservacao());
		monitor.setStatus("NAO VINCULADO");

		monitor.persist();

		return monitorDTO;
	}

	@Transactional
	public NotebookDTO cadastrarNotebook(NotebookDTO notebookDTO) {
		Notebook notebook = new Notebook();
		notebook.setModelo(notebookDTO.getModelo());
		notebook.setFabricante(notebookDTO.getFabricante());
		notebook.setProcessador(notebookDTO.getProcessador());
		notebook.setTamanhoTela(notebookDTO.getTamanhoTela());
		notebook.setMemoria(notebookDTO.getMemoria());
		notebook.setMemoriaRAM(notebookDTO.getMemoriaRAM());
		notebook.setPeso(notebookDTO.getPeso());
		notebook.setTipo(notebookDTO.getTipo());
		notebook.setEstadoConservacao(notebookDTO.getEstadoConservacao());
		notebook.setStatus("NAO VINCULADO");

		return notebookDTO;
	}

	@Transactional
	public TabletDTO cadastrarTablet(TabletDTO tabletDTO) {
		Tablet tablet = new Tablet();
		tablet.setModelo(tabletDTO.getModelo());
		tablet.setFabricante(tabletDTO.getFabricante());
		tablet.setTamanhoTela(tabletDTO.getTamanhoTela());
		tablet.setCaracterísticas(tabletDTO.getCaracterísticas());
		tablet.setMemoria(tabletDTO.getMemoria());
		tablet.setMemoriaRAM(tabletDTO.getMemoriaRAM());
		tablet.setTipo(tabletDTO.getTipo());
		tablet.setEstadoConservacao(tabletDTO.getEstadoConservacao());
		tablet.setStatus("NAO VINCULADO");

		return tabletDTO;
	}

	@Transactional
	public Response atualizarCelular(Long id, CelularDTO celularAtualizado) {
		Celular celular = Celular.findById(id);

		if (celular == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Celular não encontrado").build();
        }
		
		if (celular != null) {
			celular.setModelo(celularAtualizado.getModelo());
			celular.setFabricante(celularAtualizado.getFabricante());
			celular.setSistemaOperacional(celularAtualizado.getSistemaOperacional());
			celular.setTamanhoTela(celularAtualizado.getTamanhoTela());
			celular.setMemoria(celularAtualizado.getMemoria());
			celular.setCamera(celularAtualizado.getCamera());
			celular.setEstadoConservacao(celularAtualizado.getEstadoConservacao());

			celular.persist();
		}

		return Response.status(Response.Status.OK).entity("Celular atualizado com sucesso").build();
	}

	@Transactional
	public Response atualizarMonitor(Long id, MonitorDTO monitorAtualizado) {
		Monitor monitor = Monitor.findById(id);

		if (monitor == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Monitor não encontrado").build();
        }
		
		if (monitor != null) {
			monitor.setModelo(monitorAtualizado.getModelo());
			monitor.setFabricante(monitorAtualizado.getFabricante());
			monitor.setEntradas(monitorAtualizado.getEntradas());
			monitor.setTamanhoTela(monitorAtualizado.getTamanhoTela());
			monitor.setPeso(monitorAtualizado.getPeso());
			monitor.setRecursos(monitorAtualizado.getRecursos());
			monitor.setEstadoConservacao(monitorAtualizado.getEstadoConservacao());

			monitor.persist();
		}

		return Response.status(Response.Status.OK).entity("Monitor atualizado com sucesso").build();
	}

	@Transactional
	public Response atualizarNotebook(Long id, NotebookDTO notebookAtualizado) {
		Notebook notebook = Notebook.findById(id);
		
		if (notebook == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Notebook não encontrado").build();
        }

		if (notebook != null) {
			notebook.setModelo(notebookAtualizado.getModelo());
			notebook.setFabricante(notebookAtualizado.getFabricante());
			notebook.setProcessador(notebookAtualizado.getProcessador());
			notebook.setTamanhoTela(notebookAtualizado.getTamanhoTela());
			notebook.setMemoria(notebookAtualizado.getMemoria());
			notebook.setMemoriaRAM(notebookAtualizado.getMemoriaRAM());
			notebook.setPeso(notebookAtualizado.getPeso());
			notebook.setTipo(notebookAtualizado.getTipo());
			notebook.setEstadoConservacao(notebookAtualizado.getEstadoConservacao());

			notebook.persist();

		}
		return Response.status(Response.Status.OK).entity("Notebook atualizado com sucesso").build();
	}

	@Transactional
	public Response atualizarTablet(Long id, TabletDTO tabletAtualizado) {
		Tablet tablet = Tablet.findById(id);

		if (tablet == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Tablet não encontrado").build();
        }
		
		if (tablet != null) {
			tablet.setModelo(tabletAtualizado.getModelo());
			tablet.setFabricante(tabletAtualizado.getFabricante());
			tablet.setTamanhoTela(tabletAtualizado.getTamanhoTela());
			tablet.setCaracterísticas(tabletAtualizado.getCaracterísticas());
			tablet.setMemoria(tabletAtualizado.getMemoria());
			tablet.setMemoriaRAM(tabletAtualizado.getMemoriaRAM());
			tablet.setTipo(tabletAtualizado.getTipo());
			tablet.setEstadoConservacao(tabletAtualizado.getEstadoConservacao());
			;

			tablet.persist();

		}
		return Response.status(Response.Status.OK).entity("Tablet atualizado com sucesso").build();
	}

	public List<DispositivoInformatica> ListarDispositivoInformatica() {
		return DispositivoInformatica.listAll();
	}

	public DispositivoInformatica listarDispositivoInformaticaId(Long id) {
		return DispositivoInformatica.findById(id);
	}

	@Transactional
	public void deletarDispositivoInformatica(Long id, DispositivoInformatica dispositivoInformatica) {
		DispositivoInformatica.deleteById(id);
	}

	@Transactional
	public Response vincularLeilao(Long dispositivoId, Long leilaoId) {
		DispositivoInformatica dispositivo = DispositivoInformatica.findById(dispositivoId);
		if (dispositivo == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Dispositivo não encontrado").build();
		}

		if (dispositivo.getStatus().equals("VENDIDO")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Dispositivo já vendido").build();
		}

		Leilao leilao = Leilao.findById(leilaoId);
		if (leilao == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Leilao não encontrado").build();
		}

		dispositivo.setStatus("VINCULADO");
		dispositivo.setLeilao(leilao);

		return Response.status(Response.Status.OK).entity(dispositivo).build();
	}
	
	public Response listarDispositivoAssociadoLeilao (Long leilaoId) {
        if (leilaoId == null) {
        	return Response.status(Response.Status.BAD_REQUEST).entity("Leilao Nulo").build();
        }
        
        List<DispositivoInformatica> dispositivos = DispositivoInformatica.list("leilao.id", leilaoId);
        
        if (dispositivos.isEmpty()) {
        	return Response.status(Response.Status.NOT_FOUND).entity("Dispositivos não encontrado").build();
        }
        
		return Response.status(Response.Status.OK).entity(dispositivos).build();
	}
}
