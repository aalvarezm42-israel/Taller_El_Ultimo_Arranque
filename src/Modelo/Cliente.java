/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;


public class Cliente extends Persona {
    private int idCliente;
    private String telefono;

    public Cliente(int idCliente, String identificador, String nombre, String telefono) {
        super(identificador, nombre); 
        this.idCliente = idCliente;
        this.telefono = telefono;
    }

   
    @Override
    public String mostrarInformacion() {
        return "Cliente: " + getNombre() + " | DPI/NIT: " + getIdentificador() + " | Tel: " + telefono;
    }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }
    
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
}
