package fatec.lp.entity;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class InstituicaoFinanceira extends PanacheEntityBase {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String nome;
	
	private String cnpj;
}
