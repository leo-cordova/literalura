package com.challenge.literalura.Repository;

import com.challenge.literalura.Model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    Optional<Autor> findByNombre(String nombre);

    List<Autor> findByFechaDeNacimientoLessThanEqualAndFechaDeFallecimientoGreaterThanEqual(Integer fechaNacimineto, Integer fechaFallecimineto);
}
