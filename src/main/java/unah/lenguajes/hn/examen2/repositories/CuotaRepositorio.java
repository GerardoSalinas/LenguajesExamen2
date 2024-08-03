package unah.lenguajes.hn.examen2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import unah.lenguajes.hn.examen2.models.Cuota;

public interface CuotaRepositorio extends JpaRepository<Cuota, Long>{
    
}
