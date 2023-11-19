package br.gov.sp.fatec.dto;

import java.time.LocalDateTime;
import java.util.List;

import br.gov.sp.fatec.entity.Lance;
import lombok.Data;

@Data
public class DetalhesLeilaoDTO {

    private Long id;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private String status;
    private String endereco;
    private String cidade;
    private String estado;
    private List<VeiculoDTO> veiculos;
    private List<DispositivoInformaticaDTO> dispositivosInformatica;
    private List<Lance> lances;
}
