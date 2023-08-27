package fatec.lp.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "leilao")
public class Leilao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "data_ocorrencia")
	private Date dataOcorrencia;
	
	@Column(name = "data_visita")
	private Date dataVisita;
	
	@Column(name = "local")
	private String local;
}
