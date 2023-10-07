package fatec.lp.service;

import java.util.List;

import fatec.lp.DTO.CaminhaoDTO;
import fatec.lp.DTO.CarroDTO;
import fatec.lp.DTO.MotocicletaDTO;
import fatec.lp.entity.Caminhao;
import fatec.lp.entity.Carro;
import fatec.lp.entity.Leilao;
import fatec.lp.entity.Motocicleta;
import fatec.lp.entity.Veiculo;
import fatec.lp.resource.Request.VincularLeilaoRequest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

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
    public Carro atualizarCarro(Long id, CarroDTO carroAtualizado) {
        Carro carro = Carro.findById(id);

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

        return carro;
    }
    
    @Transactional
    public Caminhao atualizarCaminhao(Long id, CaminhaoDTO caminhaoAtualizado) {
        Caminhao caminhao = Caminhao.findById(id);

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

        return caminhao;
    }
	
    @Transactional
    public Motocicleta atualizarMotocicleta(Long id, MotocicletaDTO motocicletaAtualizada) {
        Motocicleta motocicleta = Motocicleta.findById(id);

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

        return motocicleta;
    }
	
	@Transactional
	public void deletarVeiculo(Long id, Veiculo veiculo) {
		Veiculo.deleteById(id);
	}
	
	@Transactional
	public Veiculo vincularLeilao (Long veiculoId, VincularLeilaoRequest request) {
		Veiculo veiculo = Veiculo.findById(veiculoId);
		if (veiculo == null) {
			return null;
		}
		
		if (veiculo.getStatus().equals("VENDIDO")) {
			return null;
		}
		
		Leilao leilao = Leilao.findById(request.getLeilaoId());
        if (leilao == null) {
            return null;
        }
        
		veiculo.setStatus("VINCULADO");
		veiculo.setLeilao(leilao);
		
		return veiculo;
	}
}
