/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author abraham-alvarez
 */

// Herencia usando 'extends'[cite: 3]
public class Mecanico extends Persona {
    private int idMecanico;
    private String especialidad;

    public Mecanico(int idMecanico, String identificador, String nombre, String especialidad) {
        super(identificador, nombre); // Llama al constructor de Persona
        this.idMecanico = idMecanico;
        this.especialidad = especialidad;
    }

    // Polimorfismo: Sobrescritura del método[cite: 3]
    @Override
    public String mostrarInformacion() {
        return "Mecánico: " + getNombre() + " | Especialidad: " + especialidad;
    }

    public int getIdMecanico() { return idMecanico; }
    public void setIdMecanico(int idMecanico) { this.idMecanico = idMecanico; }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
}