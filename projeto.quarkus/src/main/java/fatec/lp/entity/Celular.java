package fatec.lp.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("CELULAR")
public class Celular extends DispositivoInformatica {
	
	private String fabricante;
	private String sistemaOperacional;
	private Double tamanhoTela;
	private String memoria;
	private String camera;
}
