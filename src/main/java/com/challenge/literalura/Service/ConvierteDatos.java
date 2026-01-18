package com.challenge.literalura.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvierteDatos implements IConvierteDatos{
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T convertirDatos(String datosJson, Class<T> clase) {
        try {
            return mapper.readValue(datosJson, clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
