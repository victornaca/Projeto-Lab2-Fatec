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
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class VeiculoService {
	
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
    public VeiculoDTO cadastrarCarro(CarroDTO carroDTO) {
        Carro carro = modelMapper.map(carroDTO, Carro.class);
        carro.persist();
		return carro.toDTO();
    }
	
	@Transactional
    public VeiculoDTO cadastrarCaminhao(CaminhaoDTO caminhaoDTO) {
		Caminhao caminhao = modelMapper.map(caminhaoDTO, Caminhao.class);
	    caminhao.persist();
	    return caminhao.toDTO();	
    }
	
	@Transactional
    public VeiculoDTO cadastrarMotocicleta(MotocicletaDTO motocicletaDTO) {
		Motocicleta motocicleta = modelMapper.map(motocicletaDTO, Motocicleta.class);
		motocicleta.persist();
		return motocicleta.toDTO();
    }
	
    @Transactional
    public VeiculoDTO atualizarCarro(Long id, CarroDTO carroDTO) {
    	Carro carro = Carro.findById(id);
    	if (carro != null) {
    		modelMapper.map(carroDTO, carro);
    		carro.persist();
    		return carro.toDTO();
    	} else {
            return null;
        }
    }
    
    @Transactional
    public VeiculoDTO atualizarCaminhao(Long id, CaminhaoDTO caminhaoDTO) {
    	Caminhao caminhao = Caminhao.findById(id);
    	if (caminhao != null) {
    		modelMapper.map(caminhaoDTO, caminhao);
    		caminhao.persist();
    		return caminhao.toDTO();
    	} else {
            return null;
        }
    }
	
    @Transactional
    public VeiculoDTO atualizarMotocicleta(Long id, MotocicletaDTO motocicletaDTO) {   	
        Motocicleta motocicleta = Motocicleta.findById(id);
        if (motocicleta != null) {
    		modelMapper.map(motocicletaDTO, motocicleta);
    		motocicleta.persist();
    		return motocicleta.toDTO();
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
    public List<VeiculoDTO> listarVeiculosAssociadosLeilaoByNome(Long leilaoId, String buscaNome) {
        List<Veiculo> veiculos;

        if (buscaNome != null && !buscaNome.trim().isEmpty()) {
            veiculos = Veiculo.list("leilao.id = ?1 and (marca like ?2 or modelo like ?2)",
                    Sort.by("id"), leilaoId, "%" + buscaNome + "%");
        } else {
            veiculos = Veiculo.list("leilao.id", Sort.by("id"), leilaoId);
        }

        return veiculos.stream()
                .map(veiculo -> modelMapper.map(veiculo, VeiculoDTO.class))
                .collect(Collectors.toList());
    }
}
