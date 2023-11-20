package br.gov.sp.fatec.entity;

import br.gov.sp.fatec.dto.VeiculoDTO;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoVeiculo", discriminatorType = DiscriminatorType.STRING)
public abstract class Veiculo extends PanacheEntityBase{
	
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
    
 
    
    public Veiculo() {
    }
    
    public abstract VeiculoDTO toDTO();
}
