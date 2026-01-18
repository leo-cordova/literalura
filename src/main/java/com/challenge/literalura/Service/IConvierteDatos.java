package com.challenge.literalura.Service;

public interface IConvierteDatos {
    <T> T convertirDatos(String datosJson, Class<T> clase);
}