package com.challenge.literalura.Principal;

import com.challenge.literalura.Model.*;
import com.challenge.literalura.Repository.AutorRepository;
import com.challenge.literalura.Repository.LibroRepository;
import com.challenge.literalura.Service.ConsumoAPI;
import com.challenge.literalura.Service.ConvierteDatos;
import java.util.*;

public class Principal {
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private Scanner lectura = new Scanner(System.in);
    private String datosJson;
    private LibroRepository libroRepository;
    private AutorRepository autorRepository;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void mostrarMenu() {
        IO.println("***¡BIENVENIDO A LITERALURA!***");
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                Elija una opción:
                1 - Buscar libro por título
                2 - Listar libros registrados
                3 - Listar autor registrados
                4 - Listar autor vivos en un determinado año
                5 - Listar libros por idioma
                6 - Mostrar estadisticas
                0 - Salir
                """;
            System.out.println(menu);
            opcion = lectura.nextInt();
            lectura.nextLine();

            switch (opcion) {
                case 1 -> buscarLibroPorTitulo();
                case 2 -> listarLibrosRegistrados();
                case 3 -> listarAutores();
                case 4 -> autoresVivosPorFecha();
                case 5 -> filtrarLibrosPorIdioma();
                case 6 -> mostarEstadisticas();
                case 0 -> System.out.println("Cerrando la aplicación...");
                default -> System.out.println("Opción inválida");
            }
        }
    }

    private void buscarLibroPorTitulo() {
        IO.println("Ingrese el titulo del libro que desea buscar:");
        var libro = lectura.nextLine();
        var urlCompleta = URL_BASE + libro.replace(" ", "+");
        datosJson = consumoAPI.obtenerDatos(urlCompleta);
        mapearDatos().ifPresentOrElse(l -> {
            guardarLibro(l);
            IO.println("Título: " + l.titulo() +
                    "\n Autores: " + l.autores() +
                    "\n Idiomas: " + l.idiomas() +
                    "\n Total de descargas: " + l.totalDescargas());
            }, () -> IO.println("No hubo resultados para la busqueda realizada."));
    }

    private void listarLibrosRegistrados() {
        List<Libro> libros = libroRepository.findAll();
        if (libros.isEmpty()) {
            IO.println("No hay libros registrados en la base de datos.");
        } else {
            libros.stream()
                    .sorted(Comparator.comparing(Libro::getTitulo))
                    .forEach(System.out::println);
        }
    }

    private void listarAutores() {
        List<Autor> autores = autorRepository.findAll();
        if (autores.isEmpty()){
            IO.println("Aun no hay autores registrados");
        } else {
            autores.stream()
                    .sorted(Comparator.comparing(Autor::getNombre))
                    .forEach(System.out::println);
        }
    }

    private void autoresVivosPorFecha() {
        IO.println("Ingrese el año para filtrar autores vivos en ese año: ");
        try {
            var fecha = Integer.parseInt(lectura.nextLine());
            List<Autor> autoresVivos = autorRepository.
                    findByFechaDeNacimientoLessThanEqualAndFechaDeFallecimientoGreaterThanEqual(fecha, fecha);
            if(autoresVivos.isEmpty()) {
                IO.println("No hay autores vivos registrados en el año " + fecha);
            }else {
                autoresVivos.forEach(System.out::println);
            }
        } catch (NumberFormatException e) {
            IO.println("Por favor, ingrese un numero de año valido.");
        }
    }

    public void filtrarLibrosPorIdioma() {
        IO.println("""
                Elija la opción para buscar libros:
                1. español.
                2. Ingles.
                3. Frances.
                4. Portugues.
                """);
        var opcion = lectura.nextLine();
        try {
            Idioma idiomaSeleccionado = Idioma.fromMenu(opcion);
            List<Libro> libros = libroRepository.findByIdioma(idiomaSeleccionado);

            if (libros.isEmpty()) {
                IO.println("No se encontraron libros en ese idioma.");
            } else {
                libros.forEach(System.out::println);
            }
        } catch (IllegalArgumentException e) {
            IO.println("Opción no válida.");
        }
    }

    private void mostarEstadisticas() {
        List<Libro> libros = libroRepository.findAll();
        if (libros.isEmpty()) {
            IO.println("No hay datos suficientes para mostrar estadísticas.");
            return;
        }
        DoubleSummaryStatistics est = libros.stream()
                .filter(l -> l.getTotalDescargas() > 0)
                .mapToDouble(Libro :: getTotalDescargas)
                .summaryStatistics();

        IO.println("\n---ESTADISTICAS DE DESCARGAS---");
        IO.println("Total de libros registrados: " + est.getCount());
        IO.println("Proomedio de decargas: " + String.format("%.2f", est.getAverage()));
        IO.println("Maximo de descargas: " + est.getMax());
        IO.println("Minimo de descargas: " + est.getMin());
        IO.println("-------------------------------\n");
    }

    private Optional<DatosLibro> mapearDatos(){
        ResultadoApi datos = conversor.convertirDatos(datosJson, ResultadoApi.class);
        return datos.ResultadoDatosLibros().stream()
                .findFirst();
    }
    // persistencia de datos
    private void guardarLibro(DatosLibro datosLibro) {
        // Nos aseguramos que el libro no exista en la base de datos.
        Optional<Libro> libroExistente = libroRepository.findByTitulo(datosLibro.titulo());
        if (libroExistente.isPresent()) {
            IO.println("El libro ya existe en la base de datos.");
            IO.println(datosLibro.titulo());
        }else {
        // Nos aseguramos que el autor exista en la base de datos o lo creamos
        var datosAutor = datosLibro.autores().getFirst();
        Autor autor = autorRepository.findByNombre(datosAutor.nombre())
                .orElseGet(() -> {
                    Autor nuevoAutor = new Autor(datosAutor);
                    return autorRepository.save(nuevoAutor);
                });

        Libro libro = new Libro(datosLibro);
        libro.setAutor(autor);
        libroRepository.save(libro);
        }
    }
}