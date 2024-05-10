package com.literaluraSpring.literalura.repository;

import com.literaluraSpring.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    Optional<Autor> findByNombreContainingIgnoreCase(String nombre);

    @Query("SELECT a.nombre FROM Autor a WHERE :fecha >= a.fechaNacimiento AND :fecha <= a.fechaFallecimiento")
    List<String> autoresPorEpoca(int fecha);

    @Query("SELECT a.nombre FROM Autor a WHERE a.nombre LIKE %:nombre%")
    List<String> autorNombre(String nombre);

}