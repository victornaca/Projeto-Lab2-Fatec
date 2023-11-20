package br.gov.sp.fatec.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class LeilaoDTO {

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime dataInicio;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime dataFim;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime dataVisitaInicio;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime dataVisitaFim;
	private String status;
	private String endereco;
	private String cidade;
    @Size(max = 2)
	private String estado;
	private List<Long> leilaoInstituicaoIds;

}
