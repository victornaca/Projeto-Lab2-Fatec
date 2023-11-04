package br.gov.sp.fatec.service;

import java.util.List;

import br.gov.sp.fatec.dto.CaminhaoDTO;
import br.gov.sp.fatec.dto.CarroDTO;
import br.gov.sp.fatec.dto.MotocicletaDTO;
import br.gov.sp.fatec.entity.Caminhao;
import br.gov.sp.fatec.entity.Carro;
import br.gov.sp.fatec.entity.Leilao;
import br.gov.sp.fatec.entity.Motocicleta;
import br.gov.sp.fatec.entity.Veiculo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class VeiculoService {

	@Transactional
    public void cadastrarCarro(CarroDTO carroDTO) {
		Carro carro = new Carro();
		carro.setMarca(carroDTO.getMarca());
	    carro.setModelo(carroDTO.getModelo());
	    carro.setAno(carroDTO.getAno());
	    carro.setEstadoConservacao(carroDTO.getEstadoConservacao());
	    carro.setNumeroPortas(carroDTO.getNumeroPortas());
	    carro.setCapacidadePassageiros(carroDTO.getCapacidadePassageiros());
	    carro.setCombustivel(carroDTO.getCombustivel());
	    carro.setTipoTransmissao(carroDTO.getTipoTransmissao());
	    carro.setArCondicionado(carroDTO.isArCondicionado());
	    carro.setTravaEletrica(carroDTO.isTravaEletrica());
	    carro.setStatus("NAO VINCULADO");
        carro.persist();
		
    }
	
	@Transactional
    public void cadastrarCaminhao(CaminhaoDTO caminhaoDTO) {
		Caminhao caminhao = new Caminhao();
		caminhao.setMarca(caminhaoDTO.getMarca());
		caminhao.setModelo(caminhaoDTO.getModelo());
		caminhao.setAno(caminhaoDTO.getAno());
		caminhao.setEstadoConservacao(caminhaoDTO.getEstadoConservacao());
		caminhao.setCapacidadeCarga(caminhaoDTO.getCapacidadeCarga());
		caminhao.setNumeroEixos(caminhaoDTO.getNumeroEixos());
		caminhao.setTemCarreta(caminhaoDTO.isTemCarreta());
		caminhao.setTipoCarroceria(caminhaoDTO.getTipoCarroceria());;
	    caminhao.setStatus("NAO VINCULADO");
	    caminhao.persist();
		
    }
	
	@Transactional
    public void cadastrarMoto(MotocicletaDTO motocicletaDTO) {
		Motocicleta motocicleta = new Motocicleta();
		motocicleta.setMarca(motocicletaDTO.getMarca());
		motocicleta.setModelo(motocicletaDTO.getModelo());
		motocicleta.setAno(motocicletaDTO.getAno());
		motocicleta.setEstadoConservacao(motocicletaDTO.getEstadoConservacao());
		motocicleta.setCilindrada(motocicletaDTO.getCilindrada());
		motocicleta.setFreioAbs(motocicletaDTO.isFreioAbs());
		motocicleta.setPartidaEletrica(motocicletaDTO.isPartidaEletrica());
		motocicleta.setTipoMotocicleta(motocicletaDTO.getTipoMotocicleta());;
		motocicleta.setStatus("NAO VINCULADO");
		motocicleta.persist();
		
    }

    public List<Veiculo> listarVeiculos() {
        return Veiculo.listAll();
    }
    
	public Veiculo listarVeiculoId(Long id) {
		Veiculo veiculo = Veiculo.findById(id);
        return veiculo;
    }
	
    @Transactional
    public Response atualizarCarro(Long id, CarroDTO carroAtualizado) {
        Carro carro = Carro.findById(id);
        
        if (carro == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Carro não encontrada").build();
        }

        if (carro != null) {
        	carro.setNumeroPortas(carroAtualizado.getNumeroPortas());
            carro.setCapacidadePassageiros(carroAtualizado.getCapacidadePassageiros());
            carro.setCombustivel(carroAtualizado.getCombustivel());
            carro.setTipoTransmissao(carroAtualizado.getTipoTransmissao());
            carro.setArCondicionado(carroAtualizado.isArCondicionado());
            carro.setTravaEletrica(carroAtualizado.isTravaEletrica());
            carro.setMarca(carroAtualizado.getMarca());
            carro.setModelo(carroAtualizado.getModelo());
            carro.setAno(carroAtualizado.getAno());
            carro.setEstadoConservacao(carroAtualizado.getEstadoConservacao());
            carro.persist();
        }

        return Response.status(Response.Status.OK).entity("Carro atualizado com sucesso").build();
    }
    
    @Transactional
    public Response atualizarCaminhao(Long id, CaminhaoDTO caminhaoAtualizado) {
        Caminhao caminhao = Caminhao.findById(id);

        if (caminhao == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Caminhao não encontrada").build();
        }
        
        if (caminhao != null) {
            caminhao.setCapacidadeCarga(caminhaoAtualizado.getCapacidadeCarga());
            caminhao.setTipoCarroceria(caminhaoAtualizado.getTipoCarroceria());
            caminhao.setNumeroEixos(caminhaoAtualizado.getNumeroEixos());
            caminhao.setTemCarreta(caminhaoAtualizado.isTemCarreta());
            caminhao.setMarca(caminhaoAtualizado.getMarca());
            caminhao.setModelo(caminhaoAtualizado.getModelo());
            caminhao.setAno(caminhaoAtualizado.getAno());
            caminhao.setEstadoConservacao(caminhaoAtualizado.getEstadoConservacao());

            caminhao.persist();
        }

        return Response.status(Response.Status.OK).entity("Caminhao atualizado com sucesso").build();
    }
	
    @Transactional
    public Response atualizarMotocicleta(Long id, MotocicletaDTO motocicletaAtualizada) {   	
        Motocicleta motocicleta = Motocicleta.findById(id);

        if (motocicleta == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Motocicleta não encontrada").build();
        }
        
        if (motocicleta != null) {
            motocicleta.setTipoMotocicleta(motocicletaAtualizada.getTipoMotocicleta());
            motocicleta.setCilindrada(motocicletaAtualizada.getCilindrada());
            motocicleta.setPartidaEletrica(motocicletaAtualizada.isPartidaEletrica());
            motocicleta.setFreioAbs(motocicletaAtualizada.isFreioAbs());
            motocicleta.setMarca(motocicletaAtualizada.getMarca());
            motocicleta.setModelo(motocicletaAtualizada.getModelo());
            motocicleta.setAno(motocicletaAtualizada.getAno());
            motocicleta.setEstadoConservacao(motocicletaAtualizada.getEstadoConservacao());

            motocicleta.persist();
        }

        return Response.status(Response.Status.OK).entity("Motocicleta atualizado com sucesso").build();
    }
	
	@Transactional
	public void deletarVeiculo(Long id, Veiculo veiculo) {
		Veiculo.deleteById(id);
	}
	
	@Transactional
	public Response vincularLeilao (Long veiculoId, Long leilaoId) {
		Veiculo veiculo = Veiculo.findById(veiculoId);
		if (veiculo == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Veiculo não encontrado").build();
		}
		
		if (veiculo.getStatus().equals("VENDIDO")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Veiculo já vendido").build();
		}
		
		Leilao leilao = Leilao.findById(leilaoId);
		if (leilao == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Leilao não encontrado").build();
		}
        
		veiculo.setStatus("VINCULADO");
		veiculo.setLeilao(leilao);
		
        return Response.status(Response.Status.OK).entity(veiculo).build();
	}
	
	public Response listarVeiculoAssociadoLeilao (Long leilaoId) {
        if (leilaoId == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Leilao Nulo").build();
        }
        
        List<Veiculo> veiculos = Veiculo.list("leilao.id", leilaoId);
        
        if (veiculos.isEmpty()) {
        	return Response.status(Response.Status.NOT_FOUND).entity("Veiculos não encontrado").build();
        }
        
		return Response.status(Response.Status.OK).entity(veiculos).build();
	}
}
