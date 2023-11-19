package br.gov.sp.fatec.entity;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

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
public class Lance extends PanacheEntityBase {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    private Date dataHora;
    
    private Double valorInicial;
    
    private Double valorAdicional;

    
    @ManyToOne
    @JoinColumn(name = "Dispositivoid") 
    private DispositivoInformatica dispositivo;
    
    @ManyToOne
    @JoinColumn(name = "Clienteid") 
    private Cliente cliente;
    
    @ManyToOne
    @JoinColumn(name = "Veidculoid") 
    private Veiculo veiculo;

}
