package com.challenge.literalura.Repository;

import com.challenge.literalura.Model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Long> {

}
