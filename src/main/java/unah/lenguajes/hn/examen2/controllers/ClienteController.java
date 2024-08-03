package unah.lenguajes.hn.examen2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import unah.lenguajes.hn.examen2.models.Cliente;
import unah.lenguajes.hn.examen2.services.ClienteServicio;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    private ClienteServicio clienteServicio;

    @PostMapping("/crear")
    public Cliente crear(@RequestBody Cliente nvoCliente){
        return this.clienteServicio.crear(nvoCliente);
    }

    @GetMapping("/todos")
    private List<Cliente> obtenerTodos(){
        return this.clienteServicio.obtenerTodos();

    }
}
