package com.example.registro.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Persona {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private Integer edad;
    private LocalDateTime fechaCreacion;

    public Persona() {}

    public Persona(Long id, String nombre, String apellido, String email, Integer edad, LocalDateTime fechaCreacion) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.edad = edad;
        this.fechaCreacion = fechaCreacion;
    }

    public Persona(String nombre, String apellido, String email, Integer edad) {
        this(null, nombre, apellido, email, edad, null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", edad=" + edad +
                ", fechaCreacion=" + fechaCreacion +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
