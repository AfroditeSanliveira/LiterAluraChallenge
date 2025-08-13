package com.literAlura.repository;

import com.literAlura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    Optional<Livro> findByTitulo(String titulo);
    boolean existsByTitulo(String titulo);

    @Query("SELECT l FROM Livro l WHERE :idioma MEMBER OF l.idiomas")
    List<Livro> findByIdioma(String idioma);

    @Query("SELECT DISTINCT i FROM Livro l JOIN l.idiomas i")
    List<String> findAllIdiomas();

    Optional<Livro> findTopByOrderByDownloadsDesc();
    List<Livro> findByOrderByTituloAsc();
}