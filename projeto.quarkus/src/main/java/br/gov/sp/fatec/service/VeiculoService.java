package br.gov.sp.fatec.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import br.gov.sp.fatec.dto.CaminhaoDTO;
import br.gov.sp.fatec.dto.CarroDTO;
import br.gov.sp.fatec.dto.MotocicletaDTO;
import br.gov.sp.fatec.dto.VeiculoDTO;
import br.gov.sp.fatec.entity.Caminhao;
import br.gov.sp.fatec.entity.Carro;
import br.gov.sp.fatec.entity.Leilao;
import br.gov.sp.fatec.entity.Motocicleta;
import br.gov.sp.fatec.entity.Veiculo;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class VeiculoService {
	
	@PersistenceContext
    EntityManager entityManager;
	
	private ModelMapper modelMapper;

    public VeiculoService() {
        this.modelMapper = new ModelMapper();
    }
    
    
    public List<Veiculo> listarVeiculos() {
        return Veiculo.listAll();
    }
    
	public Veiculo listarVeiculoId(Long id) {
        return listarVeiculoId(id);
    }
	
	@Transactional
	public void deletarVeiculo(Long id, Veiculo veiculo) {
		Veiculo.deleteById(id);
	}
	
    @Transactional
    public <T extends PanacheEntityBase> VeiculoDTO cadastrarVeiculo(VeiculoDTO veiculoDTO, Class<T> entityClass) {
        T veiculo = modelMapper.map(veiculoDTO, entityClass);
        veiculo.persist();
        return modelMapper.map(veiculo, VeiculoDTO.class);
    }
	
    @Transactional
    public <T extends PanacheEntityBase> VeiculoDTO atualizarVeiculo(Long id, VeiculoDTO veiculoDTO, Class<T> entityClass) {
        T veiculo = entityManager.find(entityClass, id);
        if (veiculo != null) {
            modelMapper.map(veiculoDTO, veiculo);
            veiculo.persist();
            return modelMapper.map(veiculo, VeiculoDTO.class);
        } else {
            return null;
        }
    }
	
	@Transactional
	public VeiculoDTO vincularLeilao (Long veiculoId, Long leilaoId) {
		Veiculo veiculo = Veiculo.findById(veiculoId);
		if (veiculo == null) {
            throw new WebApplicationException("Veículo não encontrado", Response.Status.NOT_FOUND);
		}
		
		if ("VENDIDO".equals(veiculo.getStatus())) {
            throw new WebApplicationException("Veículo já vendido", Response.Status.BAD_REQUEST);
		}
		
		Leilao leilao = Leilao.findById(leilaoId);
		if (leilao == null) {
            throw new WebApplicationException("Leilão não encontrado", Response.Status.NOT_FOUND);
		}
        
		veiculo.setStatus("VINCULADO");
		veiculo.setLeilao(leilao);
		Veiculo.persist(veiculo);
		
        return veiculo.toDTO();
	}
	
	public Response listarVeiculoAssociadoLeilao (Long leilaoId) {
	    if (leilaoId == null) {
	    	throw new WebApplicationException ("Leilão Nulo", Response.Status.NOT_FOUND);
	    }

	    List<Veiculo> veiculos = Veiculo.list("leilao.id", leilaoId);

	    if (veiculos.isEmpty()) {
	    	throw new WebApplicationException ("Veículos não encontrados para o Leilão informado", Response.Status.NOT_FOUND);
	    }

	    List<VeiculoDTO> veiculoDTOs = 
	    		veiculos.stream()
	            .map(Veiculo::toDTO) //
	            .collect(Collectors.toList());

	    return Response.status(Response.Status.OK).entity(veiculoDTOs).build();
	}
	
	@Transactional
	public Response listarVeiculosAssociadosLeilaoByNome(Long leilaoId, String buscaNome) {
		if (leilaoId == null) {
	    	throw new WebApplicationException ("Leilão Nulo", Response.Status.NOT_FOUND);
        }
		
		List<Veiculo> veiculos;

	    if (buscaNome != null && !buscaNome.trim().isEmpty()) {
	    	veiculos = Veiculo.list("leilao.id = ?1 and marca like ?2",
	    	        Sort.by("id"), leilaoId, "%" + buscaNome + "%");
	    } else {
	        veiculos = Veiculo.list("leilao.id", Sort.by("id"), leilaoId);
	    }
	    if (veiculos.isEmpty()) {
	    	throw new WebApplicationException ("Veículos não encontrados para o Leilão informado", Response.Status.NOT_FOUND);
	    }

	    List<VeiculoDTO> veiculoDTOs = 
	    		veiculos.stream()
	            .map(Veiculo::toDTO) //
	            .collect(Collectors.toList());

	    return Response.status(Response.Status.OK).entity(veiculoDTOs).build();
	}
}
