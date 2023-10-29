package fatec.lp.entity;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Leilao extends PanacheEntityBase {

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

	@OneToMany(mappedBy = "leilao", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JsonManagedReference
    private List<LeilaoInstituicaoFinanceira> leilaoInstituicoes;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "leilao")
	private List<Veiculo> veiculos;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "leilao")
	private List<DispositivoInformatica> dispositivoInformatica;
}
