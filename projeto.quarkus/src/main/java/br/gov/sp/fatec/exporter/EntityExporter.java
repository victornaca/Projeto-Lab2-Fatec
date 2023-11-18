package br.gov.sp.fatec.exporter;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.gov.sp.fatec.entity.Leilao;

public class EntityExporter {

    private static final Logger LOGGER = Logger.getLogger(EntityExporter.class.getName());

    public static <T> void exportarDetalhesParaDET(List<T> entidades, String nomeArquivo) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String jsonData = objectMapper.writeValueAsString(entidades);
            objectMapper.writeValue(new File(nomeArquivo), entidades);
            LOGGER.info("Exportação para DET concluída com sucesso.");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Erro ao exportar para DET", e);
        }
    }
}
