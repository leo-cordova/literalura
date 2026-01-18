package com.challenge.literalura.Principal;

import com.challenge.literalura.Model.DatosLibro;
import com.challenge.literalura.Model.ResultadoApi;
import com.challenge.literalura.Service.ConsumoAPI;
import com.challenge.literalura.Service.ConvierteDatos;

import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private Scanner lectura = new Scanner(System.in);
    private String datosJson;

    public void mostrarMenu() {
        IO.println("***¡BIENVENIDO A LITERALURA!***");
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                1 - Buscar libro por título
                2 - Listar libros registrados
                3 - Listar autor registrados
                4 - Listar autor vivos en un determinado año
                5 - Listar libros por idioma
                0 - Salir
                """;
            System.out.println(menu);
            opcion = lectura.nextInt();
            lectura.nextLine();

            switch (opcion) {
                case 1 -> buscarLibroPorTitulo();
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
                    IO.println("Título: " + l.titulo() +
                    " Autores: " + l.autor() +
                    " Idiomas: " + l.idiomas() +
                    " Total de descargas: " + l.totalDescargas());
                    },
                () -> IO.println("No hubo resultados para la busqueda realizada."));
    }

    private Optional<DatosLibro> mapearDatos(){
        ResultadoApi datos = conversor.convertirDatos(datosJson, ResultadoApi.class);
        return datos.ResultadoDatosLibros().stream()
                .findFirst();
    }
}
