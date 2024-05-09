package com.literaluraSpring.literalura.principal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.literaluraSpring.literalura.model.DatosLibros;
import com.literaluraSpring.literalura.model.Libro;
import com.literaluraSpring.literalura.repository.LibreriaRepository;
import com.literaluraSpring.literalura.service.ConsumoAPI;
import com.literaluraSpring.literalura.service.ConvierteDatos;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private Scanner sc = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos conversor = new ConvierteDatos();
    private List<DatosLibros> datosLibros = new ArrayList<>();
    private LibreriaRepository repositorio;
    private List<Libro> libros;
    private Optional<Libro> libroBuscado;


    public Principal(LibreriaRepository repository){
        this.repositorio = repository;
    }

    public void menu() {
        int opcion;

        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Elige una opción:");
            System.out.println("1. Buscar libro");
            System.out.println("2. Ver libros actuales");
            System.out.println("3. Ver libros actuales por idioma");
            System.out.println("4. Ver autores actuales");
            System.out.println("5. Buscar autor por año");
            System.out.println("6. Salir");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    buscarLibro();
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:

                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        } while (opcion != 6);
    }



    private DatosLibros getDatosLibro(){
        System.out.println("Escribe el nombre del libro que deseas buscar");
        String nombreLibro = sc.nextLine();
        String nombreBuscar = (nombreLibro.replace(" ", "%20")).toLowerCase();

        String json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + nombreBuscar);

        System.out.println(json);

        //primer dato

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(json);
            json = rootNode.get("results").get(0).toString();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        System.out.println(json );

        DatosLibros datos = conversor.obtenerDatos(json, DatosLibros.class);
        return datos;

    }

    private void buscarLibro(){
        DatosLibros datos = getDatosLibro();
        Libro libro = new Libro(datos);
        repositorio.save(libro);
        System.out.println(datos);

    }























}
