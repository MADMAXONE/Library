/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Clasa Autor care extinde JFrame si contine un tabel care afiseaza informatii despre autori dintr-o baza de date MySQL locala
 *
 * @author gheor
 */
public class Autor extends javax.swing.JFrame {

    /** Creates new form Categorii */
    /**
     * Constructorul care initializeaza componentele grafice si apeleaza metodele Connect() si Autor_Load()
     */
    
    public Autor() {
        initComponents();
        Connect();
        Autor_Load();
    }
    
    //Declararea variabilelor de conexiune la baza de date
    
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    /**
     * Metoda Connect() se ocupa cu conectarea la baza de date MySQL locala si instantierea obiectului de conexiune "con"
     */
    
    public void Connect()
    {
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Autor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Autor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * Metoda Autor_Load() interogheaza baza de date pentru a obtine informatiile despre autori si le afiseaza in tabelul 
     * (jTable1) prin intermediul unui obiect de tip "DefaultTableModel"
     */
    
    public void Autor_Load()
    {
        int a; 
        
        try {
            pst = con.prepareStatement("select * from autor");
            rs = pst.executeQuery();
            
            ResultSetMetaData rsd = rs.getMetaData();
            a = rsd.getColumnCount();
            
            
            DefaultTableModel d = (DefaultTableModel)jTable1.getModel();
            d.setRowCount(0); // seteaza numarul de randuri din tabel la 0
            
            // Parcurgem rezultatele interogarii si adaugam randurile in tabel
            
            while (rs.next())
            {
                Vector v2 = new Vector();
                
                for(int i = 1; i<=a; i++)
                {
                    v2.add(rs.getString("Id"));
                    v2.add(rs.getString("Nume Autor"));
                    v2.add(rs.getString("Adresa"));
                    v2.add(rs.getString("Telefon"));
                }   
                
                d.addRow(v2);
                
                
                
            }
            
           
        } catch (SQLException ex) {
            Logger.getLogger(Autor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
    }    
    
    
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        nume_autor_txt = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        adresa_txt = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        telefon_txt = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 30, 97));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 48)); // NOI18N
        jLabel1.setText("Autor");

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel2.setText("Nume Autor");

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel3.setText("Adresa");

        jButton1.setBackground(new java.awt.Color(255, 149, 238));
        jButton1.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jButton1.setText("Adauga");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 149, 238));
        jButton2.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jButton2.setText("Actualizati");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 149, 238));
        jButton3.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jButton3.setText("Sterge");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 149, 238));
        jButton4.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jButton4.setText("Cancel");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Nume Autor", "Adresa", "Telefon"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        adresa_txt.setColumns(20);
        adresa_txt.setRows(5);
        jScrollPane2.setViewportView(adresa_txt);

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel4.setText("Telefon");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(nume_autor_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(62, 62, 62)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton2)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane2)
                                    .addComponent(telefon_txt))))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(362, 362, 362)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nume_autor_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(telefon_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)
                        .addGap(19, 19, 19))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Restul codului contine initializarea si implementarea componentelor grafice, cum ar fi butoane, etichete si tabel.
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        // se iau valorile din campurile de text din interfata
        
       String Nume_Autor = nume_autor_txt.getText();
       String Adresa = adresa_txt.getText();
       String Telefon = telefon_txt.getText();
        try {
            
            // se pregatesc valorile pentru a fi introduse in tabela "autor"
            
            pst = con.prepareStatement("insert into autor(`Nume Autor`,`Adresa`,`Telefon`)values(?,?,?)");
            pst.setString(1, Nume_Autor);
            pst.setString(2, Adresa);
            pst.setString(3, Telefon);
            
            // se executa interogarea SQL
            
            int k = pst.executeUpdate();
             if(k== 1)
            {
                
                // daca interogarea a avut succes, se afiseaza un mesaj de confirmare si se golesc campurile
                
                JOptionPane.showMessageDialog(this,"Autor Inregistrat");
                
                nume_autor_txt.setText("");
                adresa_txt.setText("");
                telefon_txt.setText("");
                nume_autor_txt.requestFocus();
                Autor_Load();
            }
            else
            {
                
                // daca interogarea nu a avut succes, se afiseaza un mesaj de eroare
                
                JOptionPane.showMessageDialog(this,"Eroare");  
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Autor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        
        // se ia modelul tabelului
        
        DefaultTableModel d1 = (DefaultTableModel)jTable1.getModel();
        
        // se determina index-ul randului selectat
        
        int selectIndex = jTable1.getSelectedRow();
        
        // se ia valoarea din prima coloana (id-ul)
        
        int id = Integer.parseInt(d1.getValueAt(selectIndex, 0).toString());
        
        // se pun valorile din tabel in campurile de text pentru a fi editate
        
          nume_autor_txt.setText(d1.getValueAt(selectIndex, 1).toString());
          adresa_txt.setText(d1.getValueAt(selectIndex, 2).toString());
          telefon_txt.setText(d1.getValueAt(selectIndex, 3).toString());
          
          
          // se dezactiveaza butonul de inregistrare pentru a evita crearea unui nou autor cu acelasi id
          jButton1.setEnabled(false);
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        // se ia modelul tabelului
        DefaultTableModel d1 = (DefaultTableModel)jTable1.getModel();
        
        // se determina index-ul randului selectat
        
        int selectIndex = jTable1.getSelectedRow();
        
        // se ia valoarea din prima coloana (id-ul)
        
        int id = Integer.parseInt(d1.getValueAt(selectIndex, 0).toString());
        
        
        
        // se iau valorile din campurile de text pentru a fi actualizate
        String Nume_Autor = nume_autor_txt.getText();
        String Adresa = adresa_txt.getText();
        String Telefon = telefon_txt.getText();
        
        
        try {
            
             // se pregatesc valorile pentru a fi actualizate in tabela "autor"
             
            pst = con.prepareStatement("update autor set`Nume Autor` = ?,`Adresa` = ?,`Telefon` = ? where `Id` = ?");
            pst.setString(1, Nume_Autor);
            pst.setString(2, Adresa);
            pst.setString(3, Telefon);
            pst.setInt(4, id);
            
            // se executa interogarea SQL
            
            int k = pst.executeUpdate();
            
            if(k== 1)
            {
                // daca interogarea a avut succes, se afiseaza un mesaj de confirmare si se golesc campurile
                
                JOptionPane.showMessageDialog(this,"Autor Modificat");
                
                nume_autor_txt.setText("");
                adresa_txt.setText("");
                telefon_txt.setText("");
                nume_autor_txt.requestFocus();
                
                // se reactiveaza butonul de inregistrare
                
                jButton1.setEnabled(true);
                
                // se incarca din nou tabelul cu datele din baza de date
                
          Autor_Load();    
                
            }
            else
            {
                // daca interogarea nu a avut succes, se afiseaza un mesaj de eroare
                
                JOptionPane.showMessageDialog(this,"Eroare");  
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Autor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        
       DefaultTableModel d1 = (DefaultTableModel)jTable1.getModel();
        int selectIndex = jTable1.getSelectedRow();
        
        int id = Integer.parseInt(d1.getValueAt(selectIndex, 0).toString());
        
       
        
        
        try {
            pst = con.prepareStatement("delete from autor where `Id` = ?");
         
            pst.setInt(1, id);
            
            int k = pst.executeUpdate();
            
            if(k== 1)
            {
                JOptionPane.showMessageDialog(this,"Autor Sters");
                
                nume_autor_txt.setText("");
                adresa_txt.setText("");
                telefon_txt.setText("");
                nume_autor_txt.requestFocus();
                
          Autor_Load(); 
                jButton1.setEnabled(true);
                
            }
            else
            {
                JOptionPane.showMessageDialog(this,"Eroare");  
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Autor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        
        this.setVisible(false);
        
        
    }//GEN-LAST:event_jButton4ActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Autor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Autor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Autor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Autor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Autor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea adresa_txt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField nume_autor_txt;
    private javax.swing.JTextField telefon_txt;
    // End of variables declaration//GEN-END:variables

}
