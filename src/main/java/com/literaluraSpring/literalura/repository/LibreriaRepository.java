package com.literaluraSpring.literalura.repository;

import com.literaluraSpring.literalura.model.Idioma;
import com.literaluraSpring.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibreriaRepository extends JpaRepository<Libro, Long> {

    Optional<Libro> findByTituloContainsIgnoreCase(String nombreLibro);

    List<Libro> findByIdioma(Idioma idioma);

    @Query("SELECT l.titulo, l.numDescargas FROM Libro l ORDER BY l.numDescargas DESC")
    List<Object[]> top10();



}
