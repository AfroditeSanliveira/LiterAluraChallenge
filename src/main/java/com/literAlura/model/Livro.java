package com.literAlura.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    @ManyToOne(fetch = FetchType.EAGER)
    private Autor autor;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> idiomas;

    private Integer downloads;

    public Livro() {}

    public Livro(String titulo, List<String> idiomas, Integer downloads) {
        this.titulo = titulo;
        this.idiomas = idiomas;
        this.downloads = downloads;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public Autor getAutor() { return autor; }
    public List<String> getIdiomas() { return idiomas; }
    public Integer getDownloads() { return downloads; }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}