/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author abraham-alvarez
 */

public abstract class Persona {
    // Encapsulamiento: Atributos privados
    private String identificador;
    private String nombre;

    public Persona(String identificador, String nombre) {
        this.identificador = identificador;
        this.nombre = nombre;
    }

    // Método que será sobrescrito (Polimorfismo)[cite: 3]
    public abstract String mostrarInformacion();

    // Getters y Setters
    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
    

