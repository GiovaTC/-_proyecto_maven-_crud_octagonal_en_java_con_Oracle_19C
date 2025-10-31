package com.example.registro.cli;

import com.example.registro.application.PersonaService;
import com.example.registro.domain.Persona;
import java.util.*;
import java.util.Scanner;

public class PersonaControllerCLI {
    private final PersonaService service;
    private final Scanner scanner = new Scanner(System.in);

    public PersonaControllerCLI(PersonaService service) {
        this.service = service;
    }
}
