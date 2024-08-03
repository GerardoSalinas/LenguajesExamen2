package unah.lenguajes.hn.examen2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import unah.lenguajes.hn.examen2.models.Cliente;
import unah.lenguajes.hn.examen2.models.Cuota;
import unah.lenguajes.hn.examen2.models.Prestamo;
import unah.lenguajes.hn.examen2.repositories.ClienteRepositorio;
import unah.lenguajes.hn.examen2.repositories.CuotaRepositorio;
import unah.lenguajes.hn.examen2.repositories.PrestamoRepositorio;

@Service
public class PrestamoServicio {
    @Autowired
    private PrestamoRepositorio prestamoRepositorio;

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private CuotaRepositorio cuotaRepositorio;

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

                Prestamo nvoPrestamo = this.prestamoRepositorio.save(prestamo);
                //genera cuotas
                Cuota cuotaAnterior = new Cuota();
                    cuotaAnterior.setMes(0);
                    cuotaAnterior.setCapital(0);
                    cuotaAnterior.setInteres(0);
                    cuotaAnterior.setSaldo(prestamo.getMonto());
                    
                    for (int j = 1; j<plazo*12; j++){
                        Cuota siguienteCuota = new Cuota();
                        siguienteCuota.setMes(j);
                        siguienteCuota.setInteres(cuotaAnterior.getSaldo()*i);
                        siguienteCuota.setCapital(nvaCouta-siguienteCuota.getInteres());
                        siguienteCuota.setSaldo(cuotaAnterior.getSaldo()-siguienteCuota.getCapital());
                        siguienteCuota.setPrestamo(prestamo);
                        this.cuotaRepositorio.save(siguienteCuota);
                        cuotaAnterior = siguienteCuota;
                    }

                return nvoPrestamo;
            }
        }
        return null;
    }
}
