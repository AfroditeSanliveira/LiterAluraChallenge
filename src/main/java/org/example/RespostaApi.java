package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RespostaApi(
        Integer count,
        String next,
        String previous,
        List<DadosLivro> results
) {}