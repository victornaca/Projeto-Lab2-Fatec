package fatec.lp.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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

	@ManyToMany
	@JoinTable(name = "leilao_instituicao", joinColumns = @JoinColumn(name = "leilao_id"), inverseJoinColumns = @JoinColumn(name = "instituicao_id"))
	private List<InstituicaoFinanceira> instituicoes = new ArrayList<>();

}
