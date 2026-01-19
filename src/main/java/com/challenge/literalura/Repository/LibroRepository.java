package com.challenge.literalura.Repository;

import com.challenge.literalura.Model.Idioma;
import com.challenge.literalura.Model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    List<Libro> findByIdioma(Idioma idioma);

    Optional<Libro> findByTitulo(String titulo);
}
