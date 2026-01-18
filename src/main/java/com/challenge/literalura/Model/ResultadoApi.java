package com.challenge.literalura.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ResultadoApi(
        @JsonAlias("results")List<DatosLibro> ResultadoDatosLibros
        ) {
}
