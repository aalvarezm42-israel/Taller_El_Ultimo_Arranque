/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ReparacionDAO {
    Conexion conexionBase = new Conexion();
    Connection con;
    PreparedStatement ps;

    public boolean registrarReparacion(Modelo.Reparacion reparacion) {
        // No incluimos fecha_ingreso porque Oracle pondrá el SYSDATE automáticamente
        String sql = "INSERT INTO REPARACION (id_vehiculo, id_mecanico, descripcion, costo_total) VALUES (?, ?, ?, ?)";
        
        try {
            con = conexionBase.conectar();
            con.setAutoCommit(false); // Transacción manual para proteger los datos
            
            ps = con.prepareStatement(sql);
            ps.setInt(1, reparacion.getIdVehiculo());
            ps.setInt(2, reparacion.getIdMecanico());
            ps.setString(3, reparacion.getDescripcion());
            ps.setDouble(4, reparacion.getCostoTotal());
            
            ps.executeUpdate();
            con.commit(); 
            return true;
            
        } catch (java.sql.SQLException e) {
            try {
                if (con != null) con.rollback();
            } catch (java.sql.SQLException ex) { }
            
            // El error 2291 de Oracle salta si el ID del Vehículo o del Mecánico no existen
            if (e.getErrorCode() == 2291) {
                javax.swing.JOptionPane.showMessageDialog(null, "Error: El ID del Vehículo o del Mecánico no existe en la base de datos.");
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, "Error al registrar reparación: " + e.getMessage());
            }
            return false;
        } finally {
            conexionBase.desconectar();
        }
    }
}