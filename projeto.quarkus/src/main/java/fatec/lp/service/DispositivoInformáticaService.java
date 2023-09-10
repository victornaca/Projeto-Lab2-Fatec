package fatec.lp.service;

import java.util.List;

import fatec.lp.entity.DispositivoInformática;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class DispositivoInformáticaService {

	@Transactional
	public void cadastrarDispositivoInformática (DispositivoInformática dispositivoInformática) {
		dispositivoInformática.persist();
	}
	
	public List<DispositivoInformática> ListarDispositivoInformática(){
		return DispositivoInformática.listAll();
	}
	
	public DispositivoInformática listarDispositivoInformáticaId(Long id) {
		return DispositivoInformática.findById(id);
	}
	
	@Transactional
	public DispositivoInformática atualizarDispositivoInformática(Long id, DispositivoInformática dispositivoInformáticaAtualizado) {
		DispositivoInformática dispositivoInformática = DispositivoInformática.findById(id);
		dispositivoInformática.setModelo(dispositivoInformáticaAtualizado.getModelo());
		dispositivoInformática.setTipo(dispositivoInformáticaAtualizado.getTipo());
		return dispositivoInformática;
	}
	
	@Transactional
	public void deletarDispositivoInformática(Long id, DispositivoInformática dispositivoInformática) {
		DispositivoInformática.deleteById(id);
	}
}
