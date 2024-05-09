package com.literaluraSpring.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.literaluraSpring.literalura.model.DatosLibros;
import com.literaluraSpring.literalura.model.Libro;

public class ConvierteDatos implements IConvierteDatos {
    private ObjectMapper objectMapper = new ObjectMapper();

    /*
    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        try {
            return objectMapper.readValue(json,clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

     */

    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        try {
            if (clase.equals(Libro.class)) {
                DatosLibros datosLibros = objectMapper.readValue(json, DatosLibros.class);
                return (T) new Libro(datosLibros);
            } else {
                return objectMapper.readValue(json, clase);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


}
