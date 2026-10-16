/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;


public class FrmClientes extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrmClientes.class.getName());

    
    public FrmClientes() {
        initComponents();
        this.setTitle("CLIENTES");
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        cargarTabla(); 
    }
    
  
    private void cargarTabla() {
        String[] columnas = {"ID", "DPI/NIT", "Nombre", "Teléfono"};
        javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        
        Dao.ClienteDAO dao = new Dao.ClienteDAO();
        java.util.List<Modelo.Cliente> lista = dao.listarClientes();
        
        for (Modelo.Cliente c : lista) {
            Object[] fila = new Object[4];
            fila[0] = c.getIdCliente();
            fila[1] = c.getIdentificador();
            fila[2] = c.getNombre();
            fila[3] = c.getTelefono();
            modelo.addRow(fila);
        }
        tbClientes.setModel(modelo);
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtIdentificador = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbClientes = new javax.swing.JTable();
        btnModificar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtIdentificador.addActionListener(this::txtIdentificadorActionPerformed);
        getContentPane().add(txtIdentificador, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 220, -1));
        getContentPane().add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 220, -1));
        getContentPane().add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, 220, -1));

        jButton1.setText("GUARDAR CLIENTE");
        jButton1.addActionListener(this::jButton1ActionPerformed);
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, -1, -1));

        jLabel1.setText("DPI / NIT");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 120, 40));

        jLabel2.setText("NOMBRE");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 130, -1));

        jLabel3.setText("TELEFONO");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 150, -1));

        tbClientes.setModel(new javax.swing.table.DefaultTableModel(
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
        tbClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbClientes);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, 360, 250));

        btnModificar.setText("MODIFICAR");
        btnModificar.addActionListener(this::btnModificarActionPerformed);
        getContentPane().add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 270, 130, 30));

        jButton2.setText("ELIMINAR");
        jButton2.addActionListener(this::jButton2ActionPerformed);
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 274, 130, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdentificadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdentificadorActionPerformed
     
    }//GEN-LAST:event_txtIdentificadorActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

String identificador = txtIdentificador.getText().trim();
String nombre = txtNombre.getText().trim();
String telefono = txtTelefono.getText().trim();

System.out.println("Intentando guardar -> DPI: [" + identificador + "] Nombre: [" + nombre + "]");

if(identificador.isEmpty() || nombre.isEmpty()) {
    javax.swing.JOptionPane.showMessageDialog(this, "Por favor, complete el DPI/NIT y el Nombre.");
    return; 
}


Modelo.Cliente nuevoCliente = new Modelo.Cliente(0, identificador, nombre, telefono);


Dao.ClienteDAO dao = new Dao.ClienteDAO();

if(dao.registrarCliente(nuevoCliente)) {
    javax.swing.JOptionPane.showMessageDialog(this, "¡Cliente guardado exitosamente en Oracle!");
    
    
    txtIdentificador.setText("");
    txtNombre.setText("");
    txtTelefono.setText("");
}



System.out.println("Intentando guardar -> DPI: [" + identificador + "] Nombre: [" + nombre + "]");       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed

        int filaSeleccionada = tbClientes.getSelectedRow();
        if (filaSeleccionada == -1) {
            javax.swing.JOptionPane.showMessageDialog(null, "Por favor, seleccione un cliente de la tabla para modificar.");
            return; 
        }
        
        
        int idCliente = Integer.parseInt(tbClientes.getValueAt(filaSeleccionada, 0).toString());
        
        
        String identificador = txtIdentificador.getText().trim();
        String nombre = txtNombre.getText().trim();
        String telefono = txtTelefono.getText().trim();
        
        
        if (identificador.isEmpty() || nombre.isEmpty() || telefono.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error: Ningún campo puede quedar vacío.");
            return; 
        }
        
        
        Modelo.Cliente clienteActualizado = new Modelo.Cliente(idCliente, identificador, nombre, telefono);
        Dao.ClienteDAO dao = new Dao.ClienteDAO();
        
        if (dao.modificarCliente(clienteActualizado)) {
            javax.swing.JOptionPane.showMessageDialog(null, "¡Cliente modificado exitosamente!");
            cargarTabla();
            txtIdentificador.setText("");
            txtNombre.setText("");
            txtTelefono.setText("");
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void tbClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbClientesMouseClicked
int fila = tbClientes.rowAtPoint(evt.getPoint());
        
        if (fila > -1) {
            
            txtIdentificador.setText(tbClientes.getValueAt(fila, 1).toString());
            txtNombre.setText(tbClientes.getValueAt(fila, 2).toString());
            txtTelefono.setText(tbClientes.getValueAt(fila, 3).toString());
        } 
    }//GEN-LAST:event_tbClientesMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        int filaSeleccionada = tbClientes.getSelectedRow();
        if (filaSeleccionada == -1) {
            javax.swing.JOptionPane.showMessageDialog(null, "Por favor, seleccione un cliente de la tabla para eliminar.");
            return; 
        }
        
       
        int confirmacion = javax.swing.JOptionPane.showConfirmDialog(null, 
                "¿Está seguro que desea eliminar este cliente de forma permanente?", 
                "Confirmar Eliminación", 
                javax.swing.JOptionPane.YES_NO_OPTION,
                javax.swing.JOptionPane.WARNING_MESSAGE);
                
       
        if (confirmacion == javax.swing.JOptionPane.YES_OPTION) {
            int idCliente = Integer.parseInt(tbClientes.getValueAt(filaSeleccionada, 0).toString());
            
            Dao.ClienteDAO dao = new Dao.ClienteDAO();
            if (dao.eliminarCliente(idCliente)) {
                javax.swing.JOptionPane.showMessageDialog(null, "¡Cliente eliminado exitosamente!");
                
                
                cargarTabla();
                txtIdentificador.setText("");
                txtNombre.setText("");
                txtTelefono.setText("");
            }
        }        
    }//GEN-LAST:event_jButton2ActionPerformed

    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(() -> new FrmClientes().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbClientes;
    private javax.swing.JTextField txtIdentificador;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables

    
    
   
}
