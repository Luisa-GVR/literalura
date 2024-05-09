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

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor ;

    public Libro(){

    }

    public Libro(DatosLibros datosLibros) {
        this.titulo = datosLibros.titulo();
        System.out.println("Titulo: " + this.titulo);

        this.numDescargas = datosLibros.numdescargas();
        System.out.println("NumDescargas: " + this.numDescargas);

        this.idioma = Idioma.fromString(datosLibros.idiomas()[0].toString());
        System.out.println("Idioma: " + idioma.toString());

        Autor[] autores = datosLibros.autores()[0].autores();
        this.autor = autores[0];
        System.out.println("Autor: " + this.autor.toString());

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



    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}
