/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

/**
 *
 * @author abraham-alvarez
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    
    private static final String URL = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
    private static final String USUARIO = "TALLER_EL_ULTIMO_ARRANQUE"; 
    private static final String PASSWORD = "Taller2026"; 
    private Connection conexion;

    
    public Connection conectar() {
        try {
            
            Class.forName("oracle.jdbc.OracleDriver");
            conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
            System.out.println("¡Conexión a Oracle exitosa!");
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se encontró el driver de Oracle. " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error de conexión a la Base de Datos: " + e.getMessage());
        }
        return conexion;
    }

    
    public void desconectar() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Conexión cerrada.");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}