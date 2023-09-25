package fatec.lp.service;

import java.util.List;

import fatec.lp.entity.DispositivoInformatica;
import fatec.lp.entity.Leilao;
import fatec.lp.resource.Request.VincularLeilaoRequest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class DispositivoInformaticaService {

	@Transactional
	public void cadastrarDispositivoInformatica (DispositivoInformatica dispositivoInformatica) {
		dispositivoInformatica.persist();
	}
	
	public List<DispositivoInformatica> ListarDispositivoInformatica(){
		return DispositivoInformatica.listAll();
	}
	
	public DispositivoInformatica listarDispositivoInformaticaId(Long id) {
		return DispositivoInformatica.findById(id);
	}
	
	@Transactional
	public DispositivoInformatica atualizarDispositivoInformatica(Long id, DispositivoInformatica dispositivoInformaticaAtualizado) {
		DispositivoInformatica dispositivoInformatica = DispositivoInformatica.findById(id);
		dispositivoInformatica.setModelo(dispositivoInformaticaAtualizado.getModelo());
		dispositivoInformatica.setTipo(dispositivoInformaticaAtualizado.getTipo());
		dispositivoInformatica.setEstadoConservacao(dispositivoInformaticaAtualizado.getEstadoConservacao());
		return dispositivoInformatica;
	}
	
	@Transactional
	public void deletarDispositivoInformatica(Long id, DispositivoInformatica dispositivoInformatica) {
		DispositivoInformatica.deleteById(id);
	}
	
	@Transactional
	public DispositivoInformatica vincularLeilao (Long dispositivoId, VincularLeilaoRequest request) {
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
