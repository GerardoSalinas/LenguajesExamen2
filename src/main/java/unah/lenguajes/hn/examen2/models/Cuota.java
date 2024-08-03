package unah.lenguajes.hn.examen2.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="cuotas")
@Data
public class Cuota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="codigocuota")
    private long codigoCuota;

    @Column(name="mes")
    private long mes;

    @Column(name="interes")
    private double interes;

    @Column(name="capital")
    private double capital;

    @Column(name="saldo")
    private double saldo;

    @ManyToOne()
    @JoinColumn(name="codigoprestamo", referencedColumnName="codigoprestamo")
    private Prestamo prestamo;
}
