package com.literaluraSpring.literalura.repository;

import com.literaluraSpring.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    Optional<Autor> findByNombreContainingIgnoreCase(String nombre);

}