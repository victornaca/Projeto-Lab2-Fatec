package fatec.lp.service;

import java.util.List;

import fatec.lp.DTO.CelularDTO;
import fatec.lp.DTO.MonitorDTO;
import fatec.lp.DTO.NotebookDTO;
import fatec.lp.DTO.TabletDTO;
import fatec.lp.entity.Carro;
import fatec.lp.entity.Celular;
import fatec.lp.entity.DispositivoInformatica;
import fatec.lp.entity.Leilao;
import fatec.lp.entity.Monitor;
import fatec.lp.entity.Notebook;
import fatec.lp.entity.Tablet;
import fatec.lp.resource.Request.VincularLeilaoRequest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

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
	public Celular atualizarCelular(Long id, CelularDTO celularAtualizado) {
		Celular celular = Celular.findById(id);

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

		return celular;
	}

	@Transactional
	public Monitor atualizarMonitor(Long id, MonitorDTO monitorAtualizado) {
		Monitor monitor = Monitor.findById(id);

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

		return monitor;
	}

	@Transactional
	public NotebookDTO atualizarNotebook(Long id, NotebookDTO notebookAtualizado) {
		Notebook notebook = Notebook.findById(id);

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
		return notebookAtualizado;
	}

	@Transactional
	public TabletDTO atualizarTablet(Long id, TabletDTO tabletAtualizado) {
		Tablet tablet = Tablet.findById(id);

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
		return tabletAtualizado;
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
	public DispositivoInformatica vincularLeilao(Long dispositivoId, VincularLeilaoRequest request) {
		DispositivoInformatica dispositivo = DispositivoInformatica.findById(dispositivoId);
		if (dispositivo == null) {
			return null;
		}

		if (dispositivo.getStatus().equals("VENDIDO")) {
			return null;
		}

		Leilao leilao = Leilao.findById(request.getLeilaoId());
		if (leilao == null) {
			return null;
		}

		dispositivo.setStatus("VINCULADO");
		dispositivo.setLeilao(leilao);

		return dispositivo;
	}
}
