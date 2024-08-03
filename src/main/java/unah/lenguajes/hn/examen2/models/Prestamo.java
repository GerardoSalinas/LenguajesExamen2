package unah.lenguajes.hn.examen2.models;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="prestamos")
@Data
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="codigoprestamo")
    private long codigoPrestamo;

    @Column(name="fechaapertura")
    private LocalDate fechaApertura;

    @Column(name="monto")
    private double monto;

    @Column(name="cuota")
    private double cuota;

    @Column(name="plazo")
    private long plazo;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "dni", referencedColumnName = "dni")
    private Cliente cliente;

    @JsonIgnore
    @OneToMany(mappedBy = "prestamo")
    private List<Cuota> cuotas;
}
