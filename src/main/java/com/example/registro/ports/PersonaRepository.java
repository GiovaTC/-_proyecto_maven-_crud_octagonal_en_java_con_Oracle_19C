package com.example.registro.ports;

import com.example.registro.domain.Persona;
import java.util.List;
import java.util.Optional;

public interface PersonaRepository {
    Persona save(Persona persona);
    Optional<Persona> findById(Long id);
    List<Persona> findAll();
    boolean deleteById(Long id);
}
