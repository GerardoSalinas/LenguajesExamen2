package unah.lenguajes.hn.examen2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import unah.lenguajes.hn.examen2.models.Prestamo;
import unah.lenguajes.hn.examen2.repositories.PrestamoRepositorio;

@Service
public class PrestamoServicio {
    @Autowired
    private PrestamoRepositorio prestamoRepositorio;

    public Prestamo buscarPrestamo(long codigoPrestamo){
        if (this.prestamoRepositorio.existsByCodigoPrestamo(codigoPrestamo)){
            return this.prestamoRepositorio.findByCodigoPrestamo(codigoPrestamo).get(0);
        }
        return null;
    }
}
