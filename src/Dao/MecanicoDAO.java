/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class MecanicoDAO {
    Conexion conexionBase = new Conexion();
    Connection con;
    PreparedStatement ps;

    public boolean registrarMecanico(Modelo.Mecanico mecanico) {
        // Asegúrate de que el nombre de la tabla y las columnas coincidan con DBeaver
        String sql = "INSERT INTO MECANICO (identificador, nombre, especialidad) VALUES (?, ?, ?)";
        
        try {
            con = conexionBase.conectar();
            con.setAutoCommit(false); // Transacción manual
            
            ps = con.prepareStatement(sql);
            ps.setString(1, mecanico.getIdentificador());
            ps.setString(2, mecanico.getNombre());
            ps.setString(3, mecanico.getEspecialidad());
            
            ps.executeUpdate();
            con.commit(); // Guardamos físicamente en Oracle
            return true;
            
        } catch (java.sql.SQLException e) {
            try {
                if (con != null) con.rollback();
            } catch (java.sql.SQLException ex) { }
            
            javax.swing.JOptionPane.showMessageDialog(null, "Error al registrar mecánico: " + e.getMessage());
            return false;
        } finally {
            conexionBase.desconectar();
        }
    }
    
    // Método para consultar y listar todos los mecánicos
    public java.util.List<Modelo.Mecanico> listarMecanicos() {
        java.util.List<Modelo.Mecanico> lista = new java.util.ArrayList<>();
        String sql = "SELECT * FROM MECANICO ORDER BY id_mecanico ASC";
        
        try {
            con = conexionBase.conectar();
            ps = con.prepareStatement(sql);
            java.sql.ResultSet rs = ps.executeQuery(); 
            
            while (rs.next()) {
                Modelo.Mecanico m = new Modelo.Mecanico(
                    rs.getInt("id_mecanico"),
                    rs.getString("identificador"),
                    rs.getString("nombre"),
                    rs.getString("especialidad")
                );
                lista.add(m); 
            }
        } catch (java.sql.SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error al listar mecánicos: " + e.getMessage());
        } finally {
            conexionBase.desconectar();
        }
        return lista;
    }
    // Método para modificar
    public boolean modificarMecanico(Modelo.Mecanico mecanico) {
        String sql = "UPDATE MECANICO SET identificador = ?, nombre = ?, especialidad = ? WHERE id_mecanico = ?";
        try {
            con = conexionBase.conectar();
            con.setAutoCommit(false);
            
            ps = con.prepareStatement(sql);
            ps.setString(1, mecanico.getIdentificador());
            ps.setString(2, mecanico.getNombre());
            ps.setString(3, mecanico.getEspecialidad());
            ps.setInt(4, mecanico.getIdMecanico());
            
            ps.executeUpdate();
            con.commit();
            return true;
        } catch (java.sql.SQLException e) {
            try { if (con != null) con.rollback(); } catch (java.sql.SQLException ex) { }
            javax.swing.JOptionPane.showMessageDialog(null, "Error al modificar mecánico: " + e.getMessage());
            return false;
        } finally {
            conexionBase.desconectar();
        }
    }

    // Método para eliminar
    public boolean eliminarMecanico(int idMecanico) {
        String sql = "DELETE FROM MECANICO WHERE id_mecanico = ?";
        try {
            con = conexionBase.conectar();
            con.setAutoCommit(false);
            
            ps = con.prepareStatement(sql);
            ps.setInt(1, idMecanico);
            
            ps.executeUpdate();
            con.commit();
            return true;
        } catch (java.sql.SQLException e) {
            try { if (con != null) con.rollback(); } catch (java.sql.SQLException ex) { }
            if(e.getErrorCode() == 2292) {
                javax.swing.JOptionPane.showMessageDialog(null, "Error: No se puede eliminar este mecánico porque tiene reparaciones o vehículos asignados.");
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, "Error al eliminar mecánico: " + e.getMessage());
            }
            return false;
        } finally {
            conexionBase.desconectar();
        }
    }
}
