/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class VehiculoDAO {
    Conexion conexionBase = new Conexion();
    Connection con;
    PreparedStatement ps;

    public boolean registrarVehiculo(Modelo.Vehiculo vehiculo) {
        String sql = "INSERT INTO VEHICULO (placa, marca, modelo, id_cliente) VALUES (?, ?, ?, ?)";
        
        try {
            con = conexionBase.conectar();
            con.setAutoCommit(false); // Transacción manual
            
            ps = con.prepareStatement(sql);
            ps.setString(1, vehiculo.getPlaca());
            ps.setString(2, vehiculo.getMarca());
            ps.setString(3, vehiculo.getModelo());
            ps.setInt(4, vehiculo.getIdCliente()); // Aquí va la llave foránea
            
            ps.executeUpdate();
            con.commit(); 
            return true;
            
        } catch (java.sql.SQLException e) {
            try {
                if (con != null) con.rollback();
            } catch (java.sql.SQLException ex) { }
            
            // Si el cliente no existe, Oracle lanzará un error de llave foránea
            if (e.getErrorCode() == 2291) {
                javax.swing.JOptionPane.showMessageDialog(null, "Error: El ID del Cliente no existe en la base de datos.");
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, "Error al registrar vehículo: " + e.getMessage());
            }
            return false;
        } finally {
            conexionBase.desconectar();
        }
    }
    // Método para listar vehículos
    public java.util.List<Modelo.Vehiculo> listarVehiculos() {
        java.util.List<Modelo.Vehiculo> lista = new java.util.ArrayList<>();
        String sql = "SELECT * FROM VEHICULO ORDER BY id_vehiculo ASC";
        
        try {
            con = conexionBase.conectar();
            ps = con.prepareStatement(sql);
            java.sql.ResultSet rs = ps.executeQuery(); 
            
            while (rs.next()) {
                Modelo.Vehiculo v = new Modelo.Vehiculo(
                    rs.getInt("id_vehiculo"),
                    rs.getString("placa"),
                    rs.getString("marca"),
                    rs.getString("modelo"),
                    rs.getInt("id_cliente")
                );
                lista.add(v); 
            }
        } catch (java.sql.SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error al listar vehículos: " + e.getMessage());
        } finally {
            conexionBase.desconectar();
        }
        return lista;
    }

    // Método para modificar
    public boolean modificarVehiculo(Modelo.Vehiculo vehiculo) {
        String sql = "UPDATE VEHICULO SET placa = ?, marca = ?, modelo = ?, id_cliente = ? WHERE id_vehiculo = ?";
        try {
            con = conexionBase.conectar();
            con.setAutoCommit(false);
            
            ps = con.prepareStatement(sql);
            ps.setString(1, vehiculo.getPlaca());
            ps.setString(2, vehiculo.getMarca());
            ps.setString(3, vehiculo.getModelo());
            ps.setInt(4, vehiculo.getIdCliente());
            ps.setInt(5, vehiculo.getIdVehiculo());
            
            ps.executeUpdate();
            con.commit();
            return true;
        } catch (java.sql.SQLException e) {
            try { if (con != null) con.rollback(); } catch (java.sql.SQLException ex) { }
            if (e.getErrorCode() == 2291) {
                javax.swing.JOptionPane.showMessageDialog(null, "Error: El nuevo ID del Cliente no existe en la base de datos.");
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, "Error al modificar vehículo: " + e.getMessage());
            }
            return false;
        } finally {
            conexionBase.desconectar();
        }
    }

    // Método para eliminar
    public boolean eliminarVehiculo(int idVehiculo) {
        String sql = "DELETE FROM VEHICULO WHERE id_vehiculo = ?";
        try {
            con = conexionBase.conectar();
            con.setAutoCommit(false);
            
            ps = con.prepareStatement(sql);
            ps.setInt(1, idVehiculo);
            
            ps.executeUpdate();
            con.commit();
            return true;
        } catch (java.sql.SQLException e) {
            try { if (con != null) con.rollback(); } catch (java.sql.SQLException ex) { }
            if(e.getErrorCode() == 2292) {
                javax.swing.JOptionPane.showMessageDialog(null, "Error: No se puede eliminar el vehículo porque tiene reparaciones asociadas.");
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, "Error al eliminar vehículo: " + e.getMessage());
            }
            return false;
        } finally {
            conexionBase.desconectar();
        }
    }
}