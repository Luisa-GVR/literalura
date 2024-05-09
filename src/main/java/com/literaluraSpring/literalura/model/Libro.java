package com.literaluraSpring.literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    private String titulo;

    @Column(unique = false)
    private int numDescargas;
    @Enumerated(EnumType.STRING)
    private Idioma idioma;

    @Column(name = "autor_id")
    private String autor;

    public Libro(){

    }

    public Libro(DatosLibros datosLibros) {
        this.titulo = datosLibros.titulo();
        this.numDescargas = datosLibros.numdescargas();
        this.idioma = Idioma.fromString(datosLibros.idiomas()[0].toString());
        this.autor = datosLibros.autor();
    }

    @Override
    public String toString() {
        return "Libro{" +
                "titulo='" + titulo + '\'' +
                ", numDescargas=" + numDescargas +
                ", idioma=" + idioma +
                ", autor=" + autor +
                '}';
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getNumDescargas() {
        return numDescargas;
    }

    public void setNumDescargas(int numDescargas) {
        this.numDescargas = numDescargas;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
