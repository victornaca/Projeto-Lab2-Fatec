package fatec.lp.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("NOTEBOOK")
public class Notebook extends DispositivoInformatica {

	private String fabricante;
	private String processador;
	private Double tamanhoTela;
	private String memoria;
	private String memoriaRAM;
	private String peso; 


}
