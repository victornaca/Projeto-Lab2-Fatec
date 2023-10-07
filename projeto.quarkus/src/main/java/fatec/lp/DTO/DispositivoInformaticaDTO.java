package fatec.lp.DTO;


import lombok.Data;

@Data
public class DispositivoInformaticaDTO {
	
    private Long id;
	private String tipo;
	private String modelo;
	private String estadoConservacao;
	private String status = "NAO VINCULADO";
}
