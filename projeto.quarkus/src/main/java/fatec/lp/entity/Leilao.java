package fatec.lp.entity;

import java.sql.Date;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Leilao extends PanacheEntityBase{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private Date dataOcorrencia;
	
	private Date dataVisita;
	
	private String status;
	
	private String endereco;
	
	private String cidade;
	
	@Column(length = 2)
	private String estado;
}
