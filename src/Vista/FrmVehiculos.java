/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

/**
 *
 * @author abraham-alvarez
 */
public class FrmVehiculos extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrmVehiculos.class.getName());

    /**
     * Creates new form FrmVehiculos
     */
    public FrmVehiculos() {
        initComponents();
        cargarTabla();
    }
    

    private void cargarTabla() {
        String[] columnas = {"ID", "Placa", "Marca", "Modelo", "ID Cliente"};
        javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        
        Dao.VehiculoDAO dao = new Dao.VehiculoDAO();
        java.util.List<Modelo.Vehiculo> lista = dao.listarVehiculos();
        
        for (Modelo.Vehiculo v : lista) {
            Object[] fila = new Object[5];
            fila[0] = v.getIdVehiculo();
            fila[1] = v.getPlaca();
            fila[2] = v.getMarca();
            fila[3] = v.getModelo();
            fila[4] = v.getIdCliente();
            modelo.addRow(fila);
        }
        tbVehiculos.setModel(modelo);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtPlaca = new javax.swing.JTextField();
        txtMarca = new javax.swing.JTextField();
        txtModelo = new javax.swing.JTextField();
        txtIdCliente = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbVehiculos = new javax.swing.JTable();
        btnModificar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("PLACA");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jLabel2.setText("MARCA");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        jLabel3.setText("MODELO");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        jLabel4.setText("ID Cliente");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));
        getContentPane().add(txtPlaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 90, -1));
        getContentPane().add(txtMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 90, -1));
        getContentPane().add(txtModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 80, -1));
        getContentPane().add(txtIdCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, 80, -1));

        jButton1.setText("GUARDAR VEHICULO");
        jButton1.addActionListener(this::jButton1ActionPerformed);
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 220, -1, -1));

        tbVehiculos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbVehiculos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbVehiculosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbVehiculos);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, 340, 200));

        btnModificar.setText("MODIFICAR");
        btnModificar.addActionListener(this::btnModificarActionPerformed);
        getContentPane().add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 240, -1, -1));

        jButton2.setText("ELIMINAR");
        jButton2.addActionListener(this::jButton2ActionPerformed);
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 240, 120, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
String placa = txtPlaca.getText().trim();
        String marca = txtMarca.getText().trim();
        String modelo = txtModelo.getText().trim();
        String idClienteStr = txtIdCliente.getText().trim();
        
        // Validación de campos vacíos
        if (placa.isEmpty() || marca.isEmpty() || modelo.isEmpty() || idClienteStr.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(null, "Ningún campo puede quedar vacío.");
            return;
        }
        
        try {
            int idCliente = Integer.parseInt(idClienteStr);
            Modelo.Vehiculo vehiculo = new Modelo.Vehiculo(0, placa, marca, modelo, idCliente);
            Dao.VehiculoDAO dao = new Dao.VehiculoDAO();
            
            if (dao.registrarVehiculo(vehiculo)) {
               
                javax.swing.JOptionPane.showMessageDialog(null, "¡Vehículo registrado y asignado al cliente exitosamente!");
                cargarTabla();
                
                // Limpiamos las cajas
                txtPlaca.setText("");
                txtMarca.setText("");
                txtModelo.setText("");
                txtIdCliente.setText("");
            }
        } catch (NumberFormatException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "El ID del Cliente debe ser un número válido.");
        }    
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tbVehiculosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbVehiculosMouseClicked
int fila = tbVehiculos.rowAtPoint(evt.getPoint());
        if (fila > -1) {
            txtPlaca.setText(tbVehiculos.getValueAt(fila, 1).toString());
            txtMarca.setText(tbVehiculos.getValueAt(fila, 2).toString());
            txtModelo.setText(tbVehiculos.getValueAt(fila, 3).toString());
            txtIdCliente.setText(tbVehiculos.getValueAt(fila, 4).toString());
        }     
    }//GEN-LAST:event_tbVehiculosMouseClicked

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
int filaSeleccionada = tbVehiculos.getSelectedRow();
        if (filaSeleccionada == -1) {
            javax.swing.JOptionPane.showMessageDialog(null, "Seleccione un vehículo de la tabla.");
            return; 
        }
        
        String placa = txtPlaca.getText().trim();
        String marca = txtMarca.getText().trim();
        String modelo = txtModelo.getText().trim();
        String idClienteStr = txtIdCliente.getText().trim();
        
        if (placa.isEmpty() || marca.isEmpty() || modelo.isEmpty() || idClienteStr.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(null, "Ningún campo puede quedar vacío.");
            return;
        }
        
        try {
            int idVehiculo = Integer.parseInt(tbVehiculos.getValueAt(filaSeleccionada, 0).toString());
            int idCliente = Integer.parseInt(idClienteStr);
            
            Modelo.Vehiculo vehiculo = new Modelo.Vehiculo(idVehiculo, placa, marca, modelo, idCliente);
            Dao.VehiculoDAO dao = new Dao.VehiculoDAO();
            
            if (dao.modificarVehiculo(vehiculo)) {
                javax.swing.JOptionPane.showMessageDialog(null, "¡Vehículo modificado!");
                cargarTabla();
                txtPlaca.setText("");
                txtMarca.setText("");
                txtModelo.setText("");
                txtIdCliente.setText("");
            }
        } catch (NumberFormatException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error: Verifique que el ID Cliente sea numérico.");
        }        
    }//GEN-LAST:event_btnModificarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
int filaSeleccionada = tbVehiculos.getSelectedRow();
        if (filaSeleccionada == -1) {
            javax.swing.JOptionPane.showMessageDialog(null, "Seleccione un vehículo de la tabla.");
            return; 
        }
        
        int confirmacion = javax.swing.JOptionPane.showConfirmDialog(null, 
                "¿Eliminar este vehículo permanentemente?", "Confirmar", 
                javax.swing.JOptionPane.YES_NO_OPTION, javax.swing.JOptionPane.WARNING_MESSAGE);
                
        if (confirmacion == javax.swing.JOptionPane.YES_OPTION) {
            int idVehiculo = Integer.parseInt(tbVehiculos.getValueAt(filaSeleccionada, 0).toString());
            Dao.VehiculoDAO dao = new Dao.VehiculoDAO();
            
            if (dao.eliminarVehiculo(idVehiculo)) {
                javax.swing.JOptionPane.showMessageDialog(null, "¡Vehículo eliminado!");
                cargarTabla();
                txtPlaca.setText("");
                txtMarca.setText("");
                txtModelo.setText("");
                txtIdCliente.setText("");
            }
        }        
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new FrmVehiculos().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbVehiculos;
    private javax.swing.JTextField txtIdCliente;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtModelo;
    private javax.swing.JTextField txtPlaca;
    // End of variables declaration//GEN-END:variables
}
