package com.challenge.literalura.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    @Enumerated(EnumType.STRING)
    private Idioma idioma;
    private Integer totalDescargas;

    @ManyToOne
    private Autor autor;

    public Libro (){}
    public Libro (DatosLibro datosLibro) {
        this.titulo = datosLibro.titulo();
        this.idioma = Idioma.fromString(datosLibro.idiomas().get(0));
        this.totalDescargas = datosLibro.totalDescargas();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }


    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(String idiomas) {
        this.idioma = Idioma.fromString(idiomas);
    }

    public Integer getTotalDescargas() {
        return totalDescargas;
    }

    public void setTotalDescargas(Integer totalDescargas) {
        this.totalDescargas = totalDescargas;
    }

    @Override
    public String toString() {
        return "Titulo: " + titulo +
                "\n idiomas: " + idioma +
                "\n autor: " + autor.getNombre() +
                "\n totalDescargas: " + totalDescargas;
    }
}
