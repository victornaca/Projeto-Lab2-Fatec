package br.gov.sp.fatec.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import br.gov.sp.fatec.dto.DispositivoInformaticaDTO;
import br.gov.sp.fatec.entity.DispositivoInformatica;
import br.gov.sp.fatec.entity.Leilao;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class DispositivoInformaticaService {
	
    @PersistenceContext
    EntityManager entityManager;
	
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
    public <T extends PanacheEntityBase> DispositivoInformaticaDTO cadastrarDispositivo(DispositivoInformaticaDTO dispositivoDTO, Class<T> entityClass) {
        T dispositivo = modelMapper.map(dispositivoDTO, entityClass);
        dispositivo.persist();
        return modelMapper.map(dispositivo, DispositivoInformaticaDTO.class);
    }

    @Transactional
    public <T extends PanacheEntityBase> DispositivoInformaticaDTO atualizarDispositivo(Long id, DispositivoInformaticaDTO dispositivoDTO, Class<T> entityClass) {
        T dispositivo = entityManager.find(entityClass, id);
        if (dispositivo != null) {
            modelMapper.map(dispositivoDTO, dispositivo);
            dispositivo.persist();
            return modelMapper.map(dispositivo, DispositivoInformaticaDTO.class);
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
	
    @Transactional
    public Response listarDispositivosAssociadosLeilaoByNome(Long leilaoId, String buscaNome) {
    	if (leilaoId == null) {
	    	throw new WebApplicationException ("Leilão Nulo", Response.Status.NOT_FOUND);
        }
    	
    	List<DispositivoInformatica> dispositivos;

        if (buscaNome != null && !buscaNome.trim().isEmpty()) {
        	dispositivos = DispositivoInformatica.list("leilao.id = ?1 and modelo like ?2",
        	        Sort.by("id"), leilaoId, "%" + buscaNome + "%");
    	} else {
    		dispositivos = DispositivoInformatica.list("leilao.id", Sort.by("id"), leilaoId);
    	}

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
