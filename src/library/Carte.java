
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

package library; 

//Importurile pentru a putea utiliza metodele de implementare 

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
 *
 * @author gheor
 */
public class Carte extends javax.swing.JFrame {

    /** Creates new form Categorii */
    
    // constructorul care initializeaza componentele si apoi apeleaza metodele scrise mai jos 
    public Carte() {
        initComponents();
        Connect();
        Categorii();
        Autor();
        Editor();
        Carte_Load();
    }
    
    // clasa CategoriiObiecte care contine doi membrii de date id si nume 
    public class CategoriiObiecte
    {
        
       int id;
       String nume;
       
       // constructorul care initializeaza membrii de date
       public CategoriiObiecte(int id,String nume)
       {
           
           this.id = id;
           this.nume = nume;
           
       }
       // metoda toString pentru a afisa numele
       public String toString()
       {
           return nume;
       }
               
    }
    
    // clasa AutorObiecte care contine doi membrii de date id si nume
     public class AutorObiecte
    {
        
       int id;
       String nume;
       
       // constructorul care initializeaza membrii de date
       public AutorObiecte(int id,String nume)
       {
           
           this.id = id;
           this.nume = nume;
           
       }
       // metoda toString pentru a afisa numele
       public String toString()
       {
           return nume;
       }
               
    }
    
    
    // clasa EditorObiecte care contine doi membrii de date id si nume
    public class EditorObiecte
    {
        
       int id;
       String nume;
       
       // constructorul care initializeaza membrii de date
       public EditorObiecte(int id,String nume)
       {
           
           this.id = id;
           this.nume = nume;
           
       }
       // metoda toString pentru a afisa numele
       public String toString()
       {
           return nume;
       }
               
    }
    
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    Connection con;  // declararea variabilei de conexiune
    PreparedStatement pst; // declararea variabilei pentru declaratiile pregatite
    ResultSet rs; // declararea variabilei pentru setul de rezultate
    
     // metoda Connect() care se ocupa cu conectarea la baza de date
    public void Connect()
    {
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // incarcarea driver-ului
            // conectarea la baza de date "library" folosind user-ul "root" si parola goala
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root",""); 
            
            // prinderea exceptiei daca driver-ul nu este gasit
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Carte.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) { // prinderea exceptiei daca conexiunea nu poate fi stabilit
            Logger.getLogger(Carte.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    // metoda Categorii() care se ocupa cu selectarea si afisarea datelor din tabela "categorii"
    public void Categorii()   
    {
        
        // declararea si executarea declaratiei pregatite pentru selectarea datelor din tabela "categorii"
        try {
            pst = con.prepareStatement("select * from categorii");
            rs = pst.executeQuery();
            categorie_txt.removeAllItems(); // stergerea elementelor din combobox "categorie_txt"
            
            // adaugarea elementelor din tabela "categorii" in combobox "categorie_txt"
            
            while(rs.next())
            {
                
                categorie_txt.addItem(new CategoriiObiecte(rs.getInt(1),rs.getString(2)));
                
            }
            
           // prinderea exceptiei daca interogarea nu poate fi executata 
        } catch (SQLException ex) {   
            Logger.getLogger(Carte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public void Autor()   
    {
        
        
        try {
            pst = con.prepareStatement("select * from autor");
            rs = pst.executeQuery();
            autor_txt.removeAllItems();
            
            while(rs.next())
            {
                
                autor_txt.addItem(new AutorObiecte(rs.getInt(1),rs.getString(2)));
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Carte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void Editor()   
    {
        
        
        try {
            pst = con.prepareStatement("select * from editor");
            rs = pst.executeQuery();
            editor_txt.removeAllItems();
            
            while(rs.next())
            {
                
                editor_txt.addItem(new EditorObiecte(rs.getInt(1),rs.getString(2)));
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Carte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
    
    public void Carte_Load()
    {
        int b;        //schimbam c in loc de b
        
        try {
            pst = con.prepareStatement("select b.Id,b.`Nume Carte`,c.`Nume Categorie`,a.`Nume Autor`,p.`Nume Editor`,b.`Continut`,b.`Nr Pagini`,b.`Editie` from carte b Join categorii c On b.`Categorie` = c.ID JOIN autor a On b.`Autor` = a.Id JOIN editor p On b.`Editor` = p.Id;");
            rs = pst.executeQuery();
            
            ResultSetMetaData rsd = rs.getMetaData();
            b = rsd.getColumnCount();
            
            
            DefaultTableModel d = (DefaultTableModel)jTable1.getModel();
            d.setRowCount(0);
            
            
            while (rs.next())
            {
                Vector v2 = new Vector();
                
                for(int i = 1; i<=b; i++)
                {
                    //System.out.println(rs.getString("b.Id"));
                    
                    
                    v2.add(rs.getString("Id"));
                    v2.add(rs.getString("Nume Carte"));
                    v2.add(rs.getString("Nume Categorie"));
                    v2.add(rs.getString("Nume Autor"));
                    v2.add(rs.getString("Nume Editor"));
                    v2.add(rs.getString("Continut"));
                    v2.add(rs.getString("Nr Pagini"));
                    v2.add(rs.getString("Editie"));
                    
                   
                }   
                
                d.addRow(v2);
                
                
                
            }
            
           
        } catch (SQLException ex) {
            Logger.getLogger(Carte.class.getName()).log(Level.SEVERE, null, ex);
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
        nume_carte_txt = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        continut_txt = new javax.swing.JTextField();
        categorie_txt = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        autor_txt = new javax.swing.JComboBox();
        editor_txt = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        nr_pagini_txt = new javax.swing.JTextField();
        editie_txt = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 30, 97));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 48)); // NOI18N
        jLabel1.setText("Carte");

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel2.setText("Nume Carte");

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel3.setText("Autor");

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
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nume Carte", "Categorie", "Autor", "Editor", "Continut", "Nr Pagini", "Editie"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Object.class
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

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel4.setText("Continut");

        categorie_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categorie_txtActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel5.setText("Editor");

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel6.setText("Categorie");

        jLabel7.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel7.setText("Editie");

        jLabel8.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel8.setText("Nr Pagini");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(113, 113, 113)
                        .addComponent(jButton2)
                        .addGap(110, 110, 110)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(112, 112, 112)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel4))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(editor_txt, javax.swing.GroupLayout.Alignment.LEADING, 0, 191, Short.MAX_VALUE)
                                            .addComponent(autor_txt, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(continut_txt)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel7))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(editie_txt)
                                            .addComponent(nr_pagini_txt)))))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(categorie_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel2)
                                    .addGap(18, 18, 18)
                                    .addComponent(nume_carte_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 716, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(491, 491, 491))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(nume_carte_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(categorie_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(autor_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(editor_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(continut_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nr_pagini_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(editie_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton2))
                .addContainerGap())
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
       String Nume_Carte = nume_carte_txt.getText();
       CategoriiObiecte Categorii = (CategoriiObiecte)categorie_txt.getSelectedItem();
       AutorObiecte Autor = (AutorObiecte)autor_txt.getSelectedItem();        
       EditorObiecte Editor = (EditorObiecte)editor_txt.getSelectedItem();  
       
       String Continut = continut_txt.getText();
       String Nr_pagini = nr_pagini_txt.getText();
       String Editie = editie_txt.getText();
               
               
        try {
            pst = con.prepareStatement("insert into carte(`Nume Carte`,`Categorie`,`Autor`,`Editor`,`Continut`,`Nr Pagini`,`Editie`)values(?,?,?,?,?,?,?)");
            pst.setString(1, Nume_Carte);
            pst.setInt(2, Categorii.id);
            pst.setInt(3, Autor.id);
            pst.setInt(4, Editor.id);
            pst.setString(5, Continut);
            pst.setString(6, Nr_pagini);
            pst.setString(7, Editie);
            
            
            int k = pst.executeUpdate();
             if(k== 1)
            {
                JOptionPane.showMessageDialog(this,"Carte Inregistrata");
                
                nume_carte_txt.setText("");
                categorie_txt.setSelectedIndex(-1);
                autor_txt.setSelectedIndex(-1);
                editor_txt.setSelectedIndex(-1);
                continut_txt.setText("");
                nr_pagini_txt.setText("");
                editie_txt.setText("");
                nume_carte_txt.requestFocus();
                Carte_Load();
                
                
                
                
              //  Editor_Load();
            }
            else
            {
                JOptionPane.showMessageDialog(this,"Eroare");  
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Carte.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        DefaultTableModel d1 = (DefaultTableModel)jTable1.getModel();
        int selectIndex = jTable1.getSelectedRow();
        
        int id = Integer.parseInt(d1.getValueAt(selectIndex, 0).toString());
        

       String Nume_Carte = nume_carte_txt.getText();
       CategoriiObiecte Categorii = (CategoriiObiecte)categorie_txt.getSelectedItem();
       AutorObiecte Autor = (AutorObiecte)autor_txt.getSelectedItem();        
       EditorObiecte Editor = (EditorObiecte)editor_txt.getSelectedItem();  
       
       String Continut = continut_txt.getText();
       String Nr_pagini = nr_pagini_txt.getText();
       String Editie = editie_txt.getText();
       
       
       jButton1.setEnabled(false);
       

        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
        
        DefaultTableModel d1 = (DefaultTableModel)jTable1.getModel();
        int selectIndex = jTable1.getSelectedRow();
        
        int id = Integer.parseInt(d1.getValueAt(selectIndex, 0).toString());
        
        
        String Nume_Carte = nume_carte_txt.getText();
       CategoriiObiecte Categorii = (CategoriiObiecte)categorie_txt.getSelectedItem();
       AutorObiecte Autor = (AutorObiecte)autor_txt.getSelectedItem();        
       EditorObiecte Editor = (EditorObiecte)editor_txt.getSelectedItem();  
       
       String Continut = continut_txt.getText();
       String Nr_pagini = nr_pagini_txt.getText();
       String Editie = editie_txt.getText();
               
               
        try {
            pst = con.prepareStatement("update carte set `Nume Carte` = ?,`Categorie` = ?,`Autor` = ?,`Editor` = ?,`Continut` = ?,`Nr Pagini` = ?,`Editie` = ? where`Id` = ?");
            pst.setString(1, Nume_Carte);
            pst.setInt(2, Categorii.id);
            pst.setInt(3, Autor.id);
            pst.setInt(4, Editor.id);
            pst.setString(5, Continut);
            pst.setString(6, Nr_pagini);
            pst.setString(7, Editie);
            pst.setInt(8, id);
            
            int k = pst.executeUpdate();
             if(k== 1)
            {
                JOptionPane.showMessageDialog(this,"Carte Modificata");
                
                nume_carte_txt.setText("");
                categorie_txt.setSelectedIndex(-1);
                autor_txt.setSelectedIndex(-1);
                editor_txt.setSelectedIndex(-1);
                continut_txt.setText("");
                nr_pagini_txt.setText("");
                editie_txt.setText("");
                nume_carte_txt.requestFocus();
                Carte_Load();
                jButton1.setEnabled(true);
                
                
                
                
              //  Editor_Load();
            }
            else
            {
                JOptionPane.showMessageDialog(this,"Eroare");  
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Carte.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        
       DefaultTableModel d1 = (DefaultTableModel)jTable1.getModel();
        int selectIndex = jTable1.getSelectedRow();
        
        int id = Integer.parseInt(d1.getValueAt(selectIndex, 0).toString());
        
        
        
               
               
        try {
            pst = con.prepareStatement("delete from carte where`Id` = ?");

            pst.setInt(1, id);
            
            
            
            int k = pst.executeUpdate();
             if(k== 1)
            {
                JOptionPane.showMessageDialog(this,"Carte Stearsa");
                
                nume_carte_txt.setText("");
                categorie_txt.setSelectedIndex(-1);
                autor_txt.setSelectedIndex(-1);
                editor_txt.setSelectedIndex(-1);
                continut_txt.setText("");
                nr_pagini_txt.setText("");
                editie_txt.setText("");
                nume_carte_txt.requestFocus();
                Carte_Load();
                jButton1.setEnabled(true);
                
                
                
                
              //  Editor_Load();
            }
            else
            {
                JOptionPane.showMessageDialog(this,"Eroare");  
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Carte.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        
        this.setVisible(false);
        
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void categorie_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categorie_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_categorie_txtActionPerformed

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
            java.util.logging.Logger.getLogger(Carte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Carte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Carte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Carte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Carte().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox autor_txt;
    private javax.swing.JComboBox categorie_txt;
    private javax.swing.JTextField continut_txt;
    private javax.swing.JTextField editie_txt;
    private javax.swing.JComboBox editor_txt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField nr_pagini_txt;
    private javax.swing.JTextField nume_carte_txt;
    // End of variables declaration//GEN-END:variables

}
