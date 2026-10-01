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

    // Método para registrar un nuevo cliente en Oracle
    // Método para registrar un nuevo cliente en Oracle
    // Método para registrar un nuevo cliente en Oracle
    public boolean registrarCliente(Cliente cliente) {
        String sql = "INSERT INTO CLIENTE (identificador, nombre, telefono) VALUES (?, ?, ?)";
        
        try {
            con = conexionBase.conectar();
            // 1. Desactivamos el autoguardado para controlarlo nosotros de forma segura
            con.setAutoCommit(false); 
            
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getIdentificador());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getTelefono());
            
            // 2. Usamos executeUpdate() que es el comando ideal para INSERTS, UPDATES y DELETES
            ps.executeUpdate(); 
            
            // 3. Forzamos el guardado físico en la base de datos
            con.commit(); 
            return true;
            
        } catch (SQLException e) {
            // Si algo falla, deshacemos cualquier cambio a medias (Rollback)
            try {
                if (con != null) con.rollback();
            } catch (SQLException ex) { }
            
            // AQUÍ ESTÁ EL CAMBIO: Mostrar el error real y exacto de Oracle
            JOptionPane.showMessageDialog(null, "Error REAL de Oracle: \n" + e.getMessage());
            
            return false;
        } }
    // Método para consultar y listar todos los clientes
    public java.util.List<Modelo.Cliente> listarClientes() {
        java.util.List<Modelo.Cliente> lista = new java.util.ArrayList<>();
        String sql = "SELECT * FROM CLIENTE ORDER BY id_cliente ASC";
        
        try {
            con = conexionBase.conectar();
            ps = con.prepareStatement(sql);
            // executeQuery es específico para hacer SELECT
            java.sql.ResultSet rs = ps.executeQuery(); 
            
            // Recorremos cada fila que nos devuelve Oracle
            while (rs.next()) {
                // Instanciamos un objeto Cliente con los datos de esa fila
                Modelo.Cliente c = new Modelo.Cliente(
                    rs.getInt("id_cliente"),
                    rs.getString("identificador"),
                    rs.getString("nombre"),
                    rs.getString("telefono")
                );
                lista.add(c); // Agregamos el cliente a la lista
            }
        } catch (java.sql.SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error al listar clientes: " + e.getMessage());
        } finally {
            conexionBase.desconectar();
        }
        return lista;
    }
    // Método para modificar un cliente existente en Oracle
    public boolean modificarCliente(Modelo.Cliente cliente) {
        String sql = "UPDATE CLIENTE SET identificador = ?, nombre = ?, telefono = ? WHERE id_cliente = ?";
        
        try {
            con = conexionBase.conectar();
            con.setAutoCommit(false); // Desactivamos autoguardado por seguridad
            
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getIdentificador());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getTelefono());
            ps.setInt(4, cliente.getIdCliente()); // El ID que le dice a Oracle a quién actualizar
            
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
    // Método para eliminar un cliente en Oracle
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
            
            // Capturamos la regla de negocio: Error 2292 es violación de llave foránea en Oracle
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