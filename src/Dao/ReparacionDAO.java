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
    // Método para listar reparaciones
    public java.util.List<Modelo.Reparacion> listarReparaciones() {
        java.util.List<Modelo.Reparacion> lista = new java.util.ArrayList<>();
        String sql = "SELECT * FROM REPARACION ORDER BY id_reparacion ASC";
        
        try {
            con = conexionBase.conectar();
            ps = con.prepareStatement(sql);
            java.sql.ResultSet rs = ps.executeQuery(); 
            
            while (rs.next()) {
                Modelo.Reparacion r = new Modelo.Reparacion(
                    rs.getInt("id_reparacion"),
                    rs.getInt("id_vehiculo"),
                    rs.getInt("id_mecanico"),
                    rs.getDate("fecha_ingreso"),
                    rs.getString("descripcion"),
                    rs.getDouble("costo_total")
                );
                lista.add(r); 
            }
        } catch (java.sql.SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error al listar reparaciones: " + e.getMessage());
        } finally {
            conexionBase.desconectar();
        }
        return lista;
    }

    // Método para modificar una reparación
    public boolean modificarReparacion(Modelo.Reparacion reparacion) {
        // La fecha no se actualiza para mantener el registro original de ingreso
        String sql = "UPDATE REPARACION SET id_vehiculo = ?, id_mecanico = ?, descripcion = ?, costo_total = ? WHERE id_reparacion = ?";
        try {
            con = conexionBase.conectar();
            con.setAutoCommit(false);
            
            ps = con.prepareStatement(sql);
            ps.setInt(1, reparacion.getIdVehiculo());
            ps.setInt(2, reparacion.getIdMecanico());
            ps.setString(3, reparacion.getDescripcion());
            ps.setDouble(4, reparacion.getCostoTotal());
            ps.setInt(5, reparacion.getIdReparacion());
            
            ps.executeUpdate();
            con.commit();
            return true;
        } catch (java.sql.SQLException e) {
            try { if (con != null) con.rollback(); } catch (java.sql.SQLException ex) { }
            if (e.getErrorCode() == 2291) {
                javax.swing.JOptionPane.showMessageDialog(null, "Error: El ID del Vehículo o Mecánico no existen.");
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, "Error al modificar: " + e.getMessage());
            }
            return false;
        } finally {
            conexionBase.desconectar();
        }
    }

    // Método para eliminar una reparación
    public boolean eliminarReparacion(int idReparacion) {
        String sql = "DELETE FROM REPARACION WHERE id_reparacion = ?";
        try {
            con = conexionBase.conectar();
            con.setAutoCommit(false);
            
            ps = con.prepareStatement(sql);
            ps.setInt(1, idReparacion);
            
            ps.executeUpdate();
            con.commit();
            return true;
        } catch (java.sql.SQLException e) {
            try { if (con != null) con.rollback(); } catch (java.sql.SQLException ex) { }
            javax.swing.JOptionPane.showMessageDialog(null, "Error al eliminar: " + e.getMessage());
            return false;
        } finally {
            conexionBase.desconectar();
        }
    }
}