package br.gov.sp.fatec.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import br.gov.sp.fatec.dto.CelularDTO;
import br.gov.sp.fatec.dto.DispositivoInformaticaDTO;
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
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class DispositivoInformaticaService {
	
	private ModelMapper modelMapper;

    public DispositivoInformaticaService() {
        this.modelMapper = new ModelMapper();
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
	public DispositivoInformaticaDTO cadastrarCelular(CelularDTO celularDTO) {
		Celular celular = modelMapper.map(celularDTO, Celular.class);
		celular.persist();
		return celular.toDTO();
	}

	@Transactional
	public DispositivoInformaticaDTO cadastrarMonitor(MonitorDTO monitorDTO) {
		Monitor monitor = modelMapper.map(monitorDTO, Monitor.class);
		monitor.persist();
		return monitor.toDTO();
	}

	@Transactional
	public DispositivoInformaticaDTO cadastrarNotebook(NotebookDTO notebookDTO) {
		Notebook notebook = modelMapper.map(notebookDTO, Notebook.class);
		notebook.persist();
		return notebook.toDTO();
	}

	@Transactional
	public DispositivoInformaticaDTO cadastrarTablet(TabletDTO tabletDTO) {
		Tablet tablet = modelMapper.map(tabletDTO, Tablet.class);
		tablet.persist();
		return tablet.toDTO();
	}

	@Transactional
	public DispositivoInformaticaDTO atualizarCelular(Long id, CelularDTO celularDTO) {
		Celular celular = Celular.findById(id);
		if (celular != null) {
    		modelMapper.map(celularDTO, celular);
    		celular.persist();
    		return celular.toDTO();
    	} else {
            return null;
        }
    }

	@Transactional
	public DispositivoInformaticaDTO atualizarMonitor(Long id, MonitorDTO monitorDTO) {
		Monitor monitor = Monitor.findById(id);
		if (monitor != null) {
    		modelMapper.map(monitorDTO, monitor);
    		monitor.persist();
    		return monitor.toDTO();
    	} else {
            return null;
        }
    }

	@Transactional
	public DispositivoInformaticaDTO atualizarNotebook(Long id, NotebookDTO notebookDTO) {
		Notebook notebook = Notebook.findById(id);
		if (notebook != null) {
    		modelMapper.map(notebookDTO, notebook);
    		notebook.persist();
    		return notebook.toDTO();
    	} else {
            return null;
        }
    }

	@Transactional
	public DispositivoInformaticaDTO atualizarTablet(Long id, TabletDTO tabletDTO) {
		Tablet tablet = Tablet.findById(id);
		if (tablet != null) {
    		modelMapper.map(tabletDTO, tablet);
    		tablet.persist();
    		return tablet.toDTO();
    	} else {
            return null;
        }
    }


	@Transactional
	public DispositivoInformaticaDTO vincularLeilao(Long dispositivoId, Long leilaoId) {
		DispositivoInformatica dispositivo = DispositivoInformatica.findById(dispositivoId);
		if (dispositivo == null) {
            throw new WebApplicationException("Dispositivo não encontrado", Response.Status.NOT_FOUND);
		}

		if (dispositivo.getStatus().equals("VENDIDO")) {
            throw new WebApplicationException("Dispositivo já vendido", Response.Status.BAD_REQUEST);
		}

		Leilao leilao = Leilao.findById(leilaoId);
		if (leilao == null) {
            throw new WebApplicationException("Leilão não encontrado", Response.Status.NOT_FOUND);
		}

		dispositivo.setStatus("VINCULADO");
		dispositivo.setLeilao(leilao);
		DispositivoInformatica.persist(leilao);

		return dispositivo.toDTO();
	}
	
	public Response listarDispositivoAssociadoLeilao (Long leilaoId) {
        if (leilaoId == null) {
	    	throw new WebApplicationException ("Leilão Nulo", Response.Status.NOT_FOUND);
        }
        
        List<DispositivoInformatica> dispositivos = DispositivoInformatica.list("leilao.id", leilaoId);
        
        if (dispositivos.isEmpty()) {
	    	throw new WebApplicationException ("Dispositivos não encontrados para o Leilão informado", Response.Status.NOT_FOUND);
        }
        
        List<DispositivoInformaticaDTO> dispositivoDTOs = 
	    		dispositivos.stream()
	            .map(DispositivoInformatica::toDTO) //
	            .collect(Collectors.toList());
        
		return Response.status(Response.Status.OK).entity(dispositivoDTOs).build();
	}
}
