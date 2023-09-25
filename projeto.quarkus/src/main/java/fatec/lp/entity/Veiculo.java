package fatec.lp.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Veiculo extends PanacheEntityBase{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String marca;
	
	private String modelo;
	
	private Integer ano;
	
	private String estadoConservacao;
	
	private String status = "NAO VINCULADO";
	
    @ManyToOne
    @JoinColumn(name = "leilaoId")
    private Leilao leilao;
}
