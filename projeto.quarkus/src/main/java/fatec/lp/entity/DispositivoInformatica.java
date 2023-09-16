package fatec.lp.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class DispositivoInformatica  extends PanacheEntityBase {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String tipo;
	
	private String modelo;
	
	private String estadoConservacao;
	
	private String status = "NAO VINCULADO";
	
    @ManyToOne
    @JoinColumn(name = "leilaoId")
    private Leilao leilao;

}
