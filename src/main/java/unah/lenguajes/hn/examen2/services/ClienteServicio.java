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
public class ClienteServicio {
    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private PrestamoRepositorio prestamoRepositorio;

    @Autowired
    private CuotaRepositorio cuotaRepositorio;

    public Cliente crear(Cliente nvoCliente){
        if (!this.clienteRepositorio.existsById(nvoCliente.getDni())){
            //si recibe prestamo
            Cliente cliente = this.clienteRepositorio.save(nvoCliente);
            List<Prestamo> prestamoActual = nvoCliente.getPrestamo();
            if (prestamoActual != null){
                for (Prestamo prestamo : prestamoActual){
                    prestamo.setCliente(nvoCliente);
                    //calcular cuota
                    long plazo = prestamo.getPlazo();
                    double monto = prestamo.getMonto();
                    double i = 0.09/12;
                    double nvaCouta = monto*i / 1 - Math.pow((1+i),plazo*12);
                    prestamo.setCuota(nvaCouta);
                    this.prestamoRepositorio.save(prestamo);
                    //generar tabla de pagos
                    //generar mes 0
                    Cuota cuotaAnterior = new Cuota();
                    cuotaAnterior.setMes(0);
                    cuotaAnterior.setCapital(0);
                    cuotaAnterior.setInteres(0);
                    cuotaAnterior.setSaldo(prestamo.getMonto());
                    
                    // for (int j = 1; j<plazo*12; j++){
                    //     Cuota siguienteCuota = new Cuota();
                    //     siguienteCuota.setMes(j);
                    //     siguienteCuota.setInteres(cuotaAnterior.getSaldo()*i);
                    //     siguienteCuota.setCapital(cuotaAnterior.getCapital());
                    //     siguienteCuota.setPrestamo(prestamo);
                    //     this.cuotaRepositorio.save(siguienteCuota);

                    //     cuotaAnterior = siguienteCuota;
                    // }
                }
            }
            return cliente;
        }
        return null;
    }

    public List<Cliente> obtenerTodos(){
        return this.clienteRepositorio.findAll();
    }
}
