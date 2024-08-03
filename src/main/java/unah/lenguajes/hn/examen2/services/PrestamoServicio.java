package unah.lenguajes.hn.examen2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import unah.lenguajes.hn.examen2.models.Cliente;
import unah.lenguajes.hn.examen2.models.Prestamo;
import unah.lenguajes.hn.examen2.repositories.ClienteRepositorio;
import unah.lenguajes.hn.examen2.repositories.PrestamoRepositorio;

@Service
public class PrestamoServicio {
    @Autowired
    private PrestamoRepositorio prestamoRepositorio;

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    public Prestamo buscarPrestamo(long codigoPrestamo){
        if (this.prestamoRepositorio.existsByCodigoPrestamo(codigoPrestamo)){
            return this.prestamoRepositorio.findByCodigoPrestamo(codigoPrestamo).get(0);
        }
        return null;
    }

    public Prestamo crear(String dni, Prestamo prestamo){
        if (this.clienteRepositorio.existsById(dni)){
            Cliente clienteActual = this.clienteRepositorio.findById(dni).get();
            List<Prestamo> prestamosCliente = clienteActual.getPrestamo();
            if (prestamosCliente.size()<2){
                prestamo.setCliente(clienteActual);
                long plazo = prestamo.getPlazo();
                double monto = prestamo.getMonto();
                double i = 0.09/12;
                double nvaCouta = monto*i / 1 - Math.pow((1+i),plazo*12);
                prestamo.setCuota(nvaCouta);
                return this.prestamoRepositorio.save(prestamo);
            }
        }
        return null;
    }
}
