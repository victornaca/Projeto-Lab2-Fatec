package fatec.lp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class LeilaoInstituicaoFinanceira {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "instituicao_financeira_id")
    private InstituicaoFinanceira instituicaoFinanceira;

    @ManyToOne
    @JoinColumn(name = "leilao_id")
    @JsonBackReference
    private Leilao leilao;
}
