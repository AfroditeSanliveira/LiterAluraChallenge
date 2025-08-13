package com.literAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.example.DadosLivro;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Dados(
        @JsonAlias("results") List<DadosLivro> livros
) {}