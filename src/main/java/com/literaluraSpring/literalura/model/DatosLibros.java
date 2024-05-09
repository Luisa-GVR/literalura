package com.literaluraSpring.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibros(
        @JsonAlias("title") String titulo,
        @JsonAlias("download_count") int numdescargas,
        @JsonAlias("languages") Idioma[] idiomas,
        String autor
) {

    public Idioma[] idiomas() {
        return idiomas;
    }



}
