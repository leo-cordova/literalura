package com.challenge.literalura.Model;

public enum Idioma {
    ESPANOL("es", "1"),
    INGLES("en", "2"),
    FRANCES("fr", "3"),
    PORTUGUES("pt", "4");

    private String categoriaIdioma;
    private String opcionMenu;

    Idioma(String categoriaIdioma, String opcionMenu){
        this.categoriaIdioma = categoriaIdioma;
        this.opcionMenu = opcionMenu;
    }

    public static Idioma fromString(String text) {
        for (Idioma idioma : Idioma.values()) {
            if (idioma.categoriaIdioma.equalsIgnoreCase(text)) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("Idioma no encontrado para: " + text);
    }

    public static Idioma fromMenu(String text) {
        for (Idioma idioma : Idioma.values()) {
            if (idioma.opcionMenu.equalsIgnoreCase(text)) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("Idioma no encontrado para la opcion del menu: " + text);
    }
}