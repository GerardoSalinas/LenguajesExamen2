package unah.lenguajes.hn.examen2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import unah.lenguajes.hn.examen2.models.Prestamo;
import unah.lenguajes.hn.examen2.services.PrestamoServicio;

@RestController
@RequestMapping("/api/prestamo")
public class PrestamoController {

    @Autowired
    private PrestamoServicio prestamoServicio;

    @GetMapping("/buscar/{codigoprestamo}")
    public Prestamo buscarPrestamo(@PathVariable(name="codigoprestamo") long codigoPrestamo){
        return this.prestamoServicio.buscarPrestamo(codigoPrestamo);
    }

    @PostMapping("/crear/{dni}")
    public Prestamo crearPrestamo(@PathVariable(name="dni") String dni, @RequestBody Prestamo prestamo){
        return this.prestamoServicio.crear(dni, prestamo);
    }
}
