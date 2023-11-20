import br.gov.sp.fatec.entity.Leilao;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class QuarkusApplication {
	

	public static void main(String[] args) {
		Quarkus.run(args);
	}

	public int run(String... args) {
		Leilao leilao = new Leilao();
		leilao.definirStatusComBaseNoHorario();
		return 0;
	}
}
