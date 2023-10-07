package fatec.lp.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("MONITOR")
public class Monitor extends DispositivoInformatica {
	private String fabricante;
	private Double tamanhoTela;
	private String recursos;
	private String entradas;
	private String peso; 
}
