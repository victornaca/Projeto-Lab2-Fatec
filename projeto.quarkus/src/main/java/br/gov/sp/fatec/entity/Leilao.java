package br.gov.sp.fatec.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.ejb.PrePassivate;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PreUpdate;
import lombok.Data;

@Data
@Entity
public class Leilao extends PanacheEntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDateTime dataInicio;

	private LocalDateTime dataFim;
	
	private LocalDateTime dataVisitaInicio;
	
	private LocalDateTime dataVisitaFim;

	private String status;

	private String endereco;

	private String cidade;

	@Column(length = 2)
	private String estado;

	@OneToMany(mappedBy = "leilao", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JsonManagedReference
    private List<LeilaoInstituicaoFinanceira> leilaoInstituicoes;
	
	@PreUpdate
	@PrePassivate
    public void definirStatusComBaseNoHorario() {
        LocalDateTime agora = LocalDateTime.now();

        if (agora.isBefore(dataInicio)) {
            status = "Fechado";
        } else if (agora.isAfter(dataFim)) {
            status = "Finalizado";
        } else {
            status = "Aberto";
        }
    }
}
