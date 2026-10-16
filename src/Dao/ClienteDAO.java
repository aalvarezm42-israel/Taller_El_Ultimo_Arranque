/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

/**
 *
 * @author abraham-alvarez
 */

import Modelo.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ClienteDAO {
    
    
    private Conexion conexionBase = new Conexion();
    private Connection con;
    private PreparedStatement ps;

   
    public boolean registrarCliente(Cliente cliente) {
        String sql = "INSERT INTO CLIENTE (identificador, nombre, telefono) VALUES (?, ?, ?)";
        
        try {
            con = conexionBase.conectar();
            
            con.setAutoCommit(false); 
            
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getIdentificador());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getTelefono());
            
           
            ps.executeUpdate(); 
            
           
            con.commit(); 
            return true;
            
        } catch (SQLException e) {
            
            try {
                if (con != null) con.rollback();
            } catch (SQLException ex) { }
            
            
            JOptionPane.showMessageDialog(null, "Error REAL de Oracle: \n" + e.getMessage());
            
            return false;
        } }
   
    public java.util.List<Modelo.Cliente> listarClientes() {
        java.util.List<Modelo.Cliente> lista = new java.util.ArrayList<>();
        String sql = "SELECT * FROM CLIENTE ORDER BY id_cliente ASC";
        
        try {
            con = conexionBase.conectar();
            ps = con.prepareStatement(sql);
           
            java.sql.ResultSet rs = ps.executeQuery(); 
            
           
            while (rs.next()) {
              
                Modelo.Cliente c = new Modelo.Cliente(
                    rs.getInt("id_cliente"),
                    rs.getString("identificador"),
                    rs.getString("nombre"),
                    rs.getString("telefono")
                );
                lista.add(c); 
            }
        } catch (java.sql.SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error al listar clientes: " + e.getMessage());
        } finally {
            conexionBase.desconectar();
        }
        return lista;
    }
    
    public boolean modificarCliente(Modelo.Cliente cliente) {
        String sql = "UPDATE CLIENTE SET identificador = ?, nombre = ?, telefono = ? WHERE id_cliente = ?";
        
        try {
            con = conexionBase.conectar();
            con.setAutoCommit(false); 
            
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getIdentificador());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getTelefono());
            ps.setInt(4, cliente.getIdCliente()); 
            
            ps.executeUpdate();
            con.commit(); 
            return true;
            
        } catch (java.sql.SQLException e) {
            try {
                if (con != null) con.rollback();
            } catch (java.sql.SQLException ex) { }
            
            javax.swing.JOptionPane.showMessageDialog(null, "Error al modificar cliente: " + e.getMessage());
            return false;
        } finally {
            conexionBase.desconectar();
        }
    }
    
    public boolean eliminarCliente(int idCliente) {
        String sql = "DELETE FROM CLIENTE WHERE id_cliente = ?";
        
        try {
            con = conexionBase.conectar();
            con.setAutoCommit(false); 
            
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCliente);
            
            ps.executeUpdate();
            con.commit(); 
            return true;
            
        } catch (java.sql.SQLException e) {
            try {
                if (con != null) con.rollback();
            } catch (java.sql.SQLException ex) { }
            
            
            if(e.getErrorCode() == 2292) { 
                javax.swing.JOptionPane.showMessageDialog(null, "Error: No se puede eliminar este cliente porque tiene vehículos u otros registros asociados en el taller.");
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, "Error al eliminar cliente: " + e.getMessage());
            }
            return false;
        } finally {
            conexionBase.desconectar();
        }
    }
    

}