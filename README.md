# 📚 Literalura - Catálogo de Libros

<p align="left">
   <img src="https://img.shields.io/badge/STATUS-EN%20DESARROLLO-green">
   <img src="https://img.shields.io/badge/JAVA-25-orange">
   <img src="https://img.shields.io/badge/SPRING%20BOOT-4-green">
   <img src="https://img.shields.io/badge/POSTGRESQL-DB-blue">
</p>

<img width="1394" height="474" alt="{Captura de pantalla}" src="https://github.com/user-attachments/assets/d8551e86-d8bd-41f1-b0d6-cdbee4aa674a" />

## 📝 Descripción

Literalura es una aplicación de consola desarrollada en Java utilizando el framework **Spring Boot**. Su objetivo principal es consumir la API externa [Gutendex](https://gutendex.com/) para buscar libros, guardar la información en una base de datos relacional **PostgreSQL** y permitir realizar consultas complejas sobre los datos persistidos.

Este proyecto forma parte del desafío de backend de Alura Latam / Oracle Next Education.

## 🔨 Funcionalidades del Proyecto

* 📡 **Consumo de API:** Conexión con Gutendex para buscar libros por título.
* 💾 **Persistencia de Datos:** Almacenamiento de libros y autores en PostgreSQL evitando duplicados.
* 🔍 **Búsquedas Avanzadas:**
    * Listar todos los libros registrados.
    * Listar autores registrados.
    * Filtrar autores vivos en un año específico (Lógica de fechas).
    * Filtrar libros por idioma (ES, EN, FR, PT) mediante menú interactivo.
* 📊 **Estadísticas:** Cálculo de datos agregados (media, máximo, mínimo, cantidad) sobre las descargas de los libros.

## 🛠️ Tecnologías Utilizadas

* **Java 25** - Lenguaje de programación.
* **Spring Boot 4** - Framework para el desarrollo de la aplicación.
* **Spring Data JPA** - Para el mapeo objeto-relacional (ORM) y gestión de la base de datos.
* **PostgreSQL** - Motor de base de datos.
* **Jackson** - Para la deserialización de datos JSON provenientes de la API.

## 🚀 Cómo ejecutar el proyecto

1.  **Clonar el repositorio:**
    ```bash
    git clone https://github.com/leo-cordova/literalura
    ```
2.  **Configurar variables de entorno:**
    Asegúrate de tener PostgreSQL corriendo y actualiza el archivo `application.properties` con tus credenciales:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
    spring.datasource.username=tu_usuario
    spring.datasource.password=tu_contraseña
    ```
3.  **Ejecutar la aplicación:**
    Desde tu IDE favorito (IntelliJ, Eclipse) o mediante consola:
    ```bash
    ./mvnw spring-boot:run
    ```

## ✒️ Autor

* **Leonel Cordova abad** - [mi perfil de Github](https://github.com/leo-cordova)
  
