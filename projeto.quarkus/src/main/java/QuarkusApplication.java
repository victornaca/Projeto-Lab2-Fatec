import br.gov.sp.fatec.service.LeilaoService;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.inject.Inject;

@QuarkusMain
public class QuarkusApplication {
	
	@Inject
	LeilaoService leilaoService;

	public static void main(String[] args) {
		Quarkus.run(args);
	}

	public int run(String... args) {
		leilaoService.atualizarStatusLeiloes();

		return 0;
	}
}
