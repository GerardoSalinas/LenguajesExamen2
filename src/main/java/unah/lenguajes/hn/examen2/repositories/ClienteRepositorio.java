package unah.lenguajes.hn.examen2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import unah.lenguajes.hn.examen2.models.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente, String>{
    
}
