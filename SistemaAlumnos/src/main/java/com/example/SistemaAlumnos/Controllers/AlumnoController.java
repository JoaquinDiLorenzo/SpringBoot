package com.example.SistemaAlumnos.Controllers;

import com.example.SistemaAlumnos.Domain.Alumno;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {

    private List<Alumno> alumnos = new ArrayList<>(Arrays.asList(
            new Alumno(1,"Joaquin","joaquin@Email",18,"curso1"),
            new Alumno(2,"Luca", "Luca@mail", 23,"Curso2"),
            new Alumno(3,"Laureano","Laureano@mail",34,"curso3")
    ));

    @GetMapping
    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    @GetMapping("/{email}")
    public Alumno getAlumnoByEmail(@PathVariable String email) {
        for(Alumno a: alumnos) {
            if (a.getEmail().equalsIgnoreCase(email)) return a;
        }
        return null;
    }

    @PostMapping
    public Alumno postAlumno(@RequestBody Alumno alumno) {
        alumnos.add(alumno);
        return alumno;
    }

    @PutMapping
    public Alumno putAlumno(@RequestBody Alumno alumno) {
        for(Alumno a: alumnos) {
            if (a.getID() == alumno.getID()) {
                a.setCurso(alumno.getCurso());
                a.setEdad(alumno.getEdad());
                a.setEmail(alumno.getEmail());
                a.setNombre(alumno.getNombre());

                return a;
            }
        }
        return null;
    }

    @PatchMapping
    public Alumno patchAlumno(@RequestBody Alumno alumno) {
        for(Alumno a:alumnos) {
            if (a.getID() == alumno.getID()) {
                if (alumno.getEdad() != 0) a.setEdad(alumno.getEdad());
                if (alumno.getCurso() != null) a.setCurso(alumno.getCurso());
                if (alumno.getEmail() != null) a.setEmail(alumno.getEmail());
                if (alumno.getNombre() != null) a.setNombre(alumno.getNombre());
                return a;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Alumno deleteAlumno(@PathVariable int id) {
        for (Alumno a: alumnos) {
            if (a.getID() == id) {
                alumnos.remove(a);
                return a;
            }
        }
        return null;
    }



}
