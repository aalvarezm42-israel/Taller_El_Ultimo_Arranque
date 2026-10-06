/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Date;

/**
 *
 * @author abraham-alvarez
 */
public class Reparacion {
    private int idReparacion;
    private int idVehiculo;
    private int idMecanico;
    private Date fechaIngreso;
    private String descripcion;
    private double costoTotal;

    // Constructor completo
    public Reparacion(int idReparacion, int idVehiculo, int idMecanico, Date fechaIngreso, String descripcion, double costoTotal) {
        this.idReparacion = idReparacion;
        this.idVehiculo = idVehiculo;
        this.idMecanico = idMecanico;
        this.fechaIngreso = fechaIngreso;
        this.descripcion = descripcion;
        this.costoTotal = costoTotal;
    }

    // Constructor vacío (opcional, siempre es buena práctica tenerlo)
    public Reparacion() {
    }

    // Getters y Setters
    public int getIdReparacion() { return idReparacion; }
    public void setIdReparacion(int idReparacion) { this.idReparacion = idReparacion; }

    public int getIdVehiculo() { return idVehiculo; }
    public void setIdVehiculo(int idVehiculo) { this.idVehiculo = idVehiculo; }

    public int getIdMecanico() { return idMecanico; }
    public void setIdMecanico(int idMecanico) { this.idMecanico = idMecanico; }

    public Date getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(Date fechaIngreso) { this.fechaIngreso = fechaIngreso; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getCostoTotal() { return costoTotal; }
    public void setCostoTotal(double costoTotal) { this.costoTotal = costoTotal; }
}
