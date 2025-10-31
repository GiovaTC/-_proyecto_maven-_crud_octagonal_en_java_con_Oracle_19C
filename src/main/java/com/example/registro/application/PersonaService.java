package com.example.registro.application;

import com.example.registro.domain.Persona;
import com.example.registro.ports.PersonaRepository;

import java.util.List;
import java.util.Optional;

public class PersonaService {
    private final PersonaRepository repository;

    public PersonaService(PersonaRepository repository) {
        this.repository = repository;
    }

    public Persona crearPersona(String nombre, String Apellido, String email, Integer edad) {
        return repository.save(new Persona(nombre, Apellido, email, edad));
    }

    public Persona actualizarPersona(Long id, String nombre, String Apellido, String email, Integer edad) {
        Persona p = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Persona no encontrada: " + id));
        p.setNombre(nombre);
        p.setApellido(Apellido);
        p.setEmail(email);
        p.setEdad(edad);
        return repository.save(p);
    }

    public Optional<Persona> obtenerPorId(Long id) { return repository.findById(id); }
    public List<Persona> listarTodas() { return repository.findAll(); }
    public boolean eliminar(Long id) { return repository.deleteById(id); }
}
