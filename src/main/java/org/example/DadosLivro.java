package org.example;

import com.fasterxml.jackson.annotation.JsonAlias;
import java.util.List;

public record DadosLivro(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<DadosAutor> autores,
        @JsonAlias("languages") List<String> idiomas,
        @JsonAlias("download_count") Integer downloads
) {}