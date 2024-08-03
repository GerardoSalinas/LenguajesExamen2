package unah.lenguajes.hn.examen2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import unah.lenguajes.hn.examen2.models.Prestamo;
import java.util.List;


public interface PrestamoRepositorio extends JpaRepository<Prestamo, Long>{
    public List<Prestamo> findByCodigoPrestamo(long codigoPrestamo);

    public boolean existsByCodigoPrestamo(long codigoPrestamo);
}
