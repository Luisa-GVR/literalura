package com.literaluraSpring.literalura.principal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.literaluraSpring.literalura.model.*;
import com.literaluraSpring.literalura.repository.AutorRepository;
import com.literaluraSpring.literalura.repository.LibreriaRepository;
import com.literaluraSpring.literalura.service.ConsumoAPI;
import com.literaluraSpring.literalura.service.ConvierteDatos;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class Principal {

    private Scanner sc = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos conversor = new ConvierteDatos();
    private List<DatosLibros> datosLibros = new ArrayList<>();
    private LibreriaRepository repositorio;
    private AutorRepository repositorioAutor;
    private List<Libro> libros;
    private Optional<Libro> libroBuscado;

    String nombreAutor = null;

    @Autowired
    public Principal(LibreriaRepository repositorioLibreria, AutorRepository repositorioAutor) {
        this.repositorio = repositorioLibreria;
        this.repositorioAutor = repositorioAutor;
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
                    verLibros();
                    break;
                case 3:
                    verLibrosPorIdioma();
                    break;
                case 4:
                    verAutores();
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

    private void verLibrosPorIdioma() {
        Idioma idioma = null;
        int opcion;

        do {
            System.out.println("Elige el idioma:");
            System.out.println("1. Español");
            System.out.println("2. Inglés");
            System.out.println("3. Francés");
            System.out.println("4. Italiano");
            System.out.println("5. Alemán");
            System.out.println("6. Portugués");

            try {
                opcion = sc.nextInt();
                switch (opcion) {
                    case 1:
                        idioma = Idioma.es;
                        break;
                    case 2:
                        idioma = Idioma.en;
                        break;
                    case 3:
                        idioma = Idioma.fr;
                        break;
                    case 4:
                        idioma = Idioma.it;
                        break;
                    case 5:
                        idioma = Idioma.de;
                        break;
                    case 6:
                        idioma = Idioma.pt;
                        break;
                    default:
                        System.out.println("Opción no válida");
                        sc.nextLine(); // Consume the invalid input
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, introduce un número válido.");
                sc.nextLine(); // Consume the invalid input
                opcion = 0;
            }
        } while (opcion < 1 || opcion > 6);

        if (idioma != null) {
            List<Libro> libros = repositorio.findByIdioma(idioma);
            if (libros.isEmpty()) {
                System.out.println("No hay libros en la base de datos con ese idioma.");
            } else {
                for (Libro libro : libros) {
                    System.out.println(libro);
                }
            }
        }
    }


    private void verAutores() {
        System.out.println("Los autores que tienes actualmente son:");
        List<Autor> autores = repositorioAutor.findAll();
        for (Autor autor : autores) {
            System.out.println(autor);
        }
    }

    private void verLibros() {
        System.out.println("Los libros que tienes actualmente son:");
        List<Libro> libros = repositorio.findAll();
        for (Libro libro : libros) {
            System.out.println(libro);
        }
    }


    private DatosLibros getDatosLibro(){
        System.out.println("Escribe el nombre del libro que deseas buscar");
        String nombreLibro = sc.nextLine();
        String nombreBuscar = (nombreLibro.replace(" ", "%20")).toLowerCase();

        String json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + nombreBuscar);


        //primer dato

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(json);
            json = rootNode.get("results").get(0).toString();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }



        nombreAutor = buscarAutor(json);


        DatosLibros datos = conversor.obtenerDatos(json, DatosLibros.class);
        return datos;

    }
    @Transactional
    private String buscarAutor(String json) {
        String nombreAutor = null;
        JsonNode authorsNode = null;

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(json);
            authorsNode = jsonNode.get("authors");
            json = authorsNode.get(0).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }


        DatosAutor datos = getDatosAutor(json);

        Autor autor = new Autor(datos);

        repositorioAutor.save(autor);

        return datos.name();

    }

    private DatosAutor getDatosAutor(String json) {
        return conversor.obtenerDatosAutor(json, DatosAutor.class);
    }

    private void buscarLibro(){

        DatosLibros datos = getDatosLibro();
        Libro libro = new Libro(datos);

        libro.setAutor(nombreAutor);

        System.out.println(libro.toString());

        repositorio.save(libro);

    }























}
