package fatec.lp.repository;

import fatec.lp.entity.Veiculo;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface VeiculoRepository extends PanacheRepository<Veiculo> {
	

}
