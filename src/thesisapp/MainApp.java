/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thesisapp;

import graphmodel.GraphProcess;
import java.util.Vector;
import javax.swing.JTable;
import model.Dataset;

/**
 *
 * @author Momo
 */
public class MainApp extends javax.swing.JFrame {

    /**
     * Creates new form MainApp
     */
    public MainApp() {
        initComponents();

        //getParkiran();
    }
    
    private void getBalapan()
    {
        Vector<Vector<Object>> datadd = new Vector<Vector<Object>>();
        Vector<Vector<Object>> dataid = new Vector<Vector<Object>>();
        Vector<Vector<Object>> datadi = new Vector<Vector<Object>>();
        Vector<Vector<Object>> dataii = new Vector<Vector<Object>>();
        
        Vector<Vector<Object>> datasdd = new Vector<Vector<Object>>();
        Vector<Vector<Object>> datasid = new Vector<Vector<Object>>();
        Vector<Vector<Object>> datasdi = new Vector<Vector<Object>>();
        Vector<Vector<Object>> datasii = new Vector<Vector<Object>>();
        
        Vector<String> headers = new Vector<String>();
        headers.add(" ");
        for (int x = 1; x < 13; x++)
        {
            headers.add(Integer.toString(x));
        }
        
        for (int x = 1; x < 13; x++) {
            //tanpa semantic
            Vector<Object> rowdd = new Vector<Object>();
            rowdd.add(Integer.toString(x));
            
            Vector<Object> rowid = new Vector<Object>();
            rowid.add(Integer.toString(x));
            
            Vector<Object> rowdi = new Vector<Object>();
            rowdi.add(Integer.toString(x));
            
            Vector<Object> rowii = new Vector<Object>();
            rowii.add(Integer.toString(x));
            
//            dengan semntic
            Vector<Object> rowsdd = new Vector<Object>();
            rowsdd.add(Integer.toString(x));
            
            Vector<Object> rowsid = new Vector<Object>();
            rowsid.add(Integer.toString(x));
            
            Vector<Object> rowsdi = new Vector<Object>();
            rowsdi.add(Integer.toString(x));
            
            Vector<Object> rowsii = new Vector<Object>();
            rowsii.add(Integer.toString(x));
            
            for (int y = 1; y < 13; y++) {
                Dataset app0 = new Dataset();
                app0.readFile("balapan", "case20.puml");

                Dataset app1 = new Dataset();
                app1.readFile("balapan", "case2" + x + ".puml");

                Dataset app2 = new Dataset();
                app2.readFile("balapan", "case2" + y + ".puml");

                GraphProcess gp = new GraphProcess(app0.getGraph(), app1.getGraph(), app2.getGraph());
                if(x == y)
                {
                    rowdd.add(0);
                    rowid.add(0);
                    rowdi.add(0);
                    rowii.add(0);
                    
                    //menambahkan dengan semantuc
                    rowsdd.add(0);
                    rowsid.add(0);
                    rowsdi.add(0);
                    rowsii.add(0);
                }else
                {
                    rowdd.add(gp.countDeleteDelete());
                    rowid.add(gp.countInsertDelete());
                    rowdi.add(gp.countDeleteInsert());
                    rowii.add(gp.countInsertInsert());
                    
                    rowsdd.add(gp.countDeleteDelete());
                    rowsid.add(gp.countInsertDelete());
                    rowsdi.add(gp.countDeleteInsert());
                    rowsii.add(gp.countInsertInsert());   
                }
            }
            
            datadd.add(rowdd);
            dataid.add(rowid);
            datadi.add(rowdi);
            dataii.add(rowii);
            
            //semantic
            datasdd.add(rowsdd);
            datasid.add(rowsid);
            datasdi.add(rowsdi);
            datasii.add(rowsii);
        }
        
        JTable tabledd = new JTable( datadd, headers );
        jScrollPanedd.getViewport().removeAll();
        jScrollPanedd.getViewport().add(tabledd);
        
        JTable tableid = new JTable( dataid, headers );
        jScrollPaneid.getViewport().removeAll();
        jScrollPaneid.getViewport().add(tableid);
        
        JTable tabledi = new JTable( datadi, headers );
        jScrollPanedi.getViewport().removeAll();
        jScrollPanedi.getViewport().add(tabledi);
        
        JTable tableii = new JTable( dataii, headers );
        jScrollPaneii.getViewport().removeAll();
        jScrollPaneii.getViewport().add(tableii);
        
        JTable tablesdd = new JTable( datasdd, headers );
        jScrollPanesdd.getViewport().removeAll();
        jScrollPanesdd.getViewport().add(tablesdd);
        
        JTable tablesid = new JTable( datasid, headers );
        jScrollPanesid.getViewport().removeAll();
        jScrollPanesid.getViewport().add(tablesid);
        
        JTable tablesdi = new JTable( datasdi, headers );
        jScrollPanesdi.getViewport().removeAll();
        jScrollPanesdi.getViewport().add(tablesdi);
        
        JTable tablesii = new JTable( datasii, headers );
        jScrollPanesii.getViewport().removeAll();
        jScrollPanesii.getViewport().add(tablesii);
    }
    
    private void getParkiran()
    {
        Vector<Vector<Object>> datadd = new Vector<Vector<Object>>();
        Vector<Vector<Object>> dataid = new Vector<Vector<Object>>();
        Vector<Vector<Object>> datadi = new Vector<Vector<Object>>();
        Vector<Vector<Object>> dataii = new Vector<Vector<Object>>();
        
        Vector<Vector<Object>> datasdd = new Vector<Vector<Object>>();
        Vector<Vector<Object>> datasid = new Vector<Vector<Object>>();
        Vector<Vector<Object>> datasdi = new Vector<Vector<Object>>();
        Vector<Vector<Object>> datasii = new Vector<Vector<Object>>();
        
        Vector<String> headers = new Vector<String>();
        headers.add(" ");
        for (int x = 1; x < 11; x++)
        {
            headers.add(Integer.toString(x));
        }
        
        for (int x = 1; x < 11; x++) {
            Vector<Object> rowdd = new Vector<Object>();
            rowdd.add(Integer.toString(x));
            
            Vector<Object> rowid = new Vector<Object>();
            rowid.add(Integer.toString(x));
            
            Vector<Object> rowdi = new Vector<Object>();
            rowdi.add(Integer.toString(x));
            
            Vector<Object> rowii = new Vector<Object>();
            rowii.add(Integer.toString(x));
            
            //dengan semantic
            Vector<Object> rowsdd = new Vector<Object>();
            rowsdd.add(Integer.toString(x));
            
            Vector<Object> rowsid = new Vector<Object>();
            rowsid.add(Integer.toString(x));
            
            Vector<Object> rowsdi = new Vector<Object>();
            rowsdi.add(Integer.toString(x));
            
            Vector<Object> rowsii = new Vector<Object>();
            rowsii.add(Integer.toString(x));
            
            for (int y = 1; y < 11; y++) {
                Dataset app0 = new Dataset();
                app0.readFile("parkiran", "case10.puml");

                Dataset app1 = new Dataset();
                app1.readFile("parkiran", "case1" + x + ".puml");

                Dataset app2 = new Dataset();
                app2.readFile("parkiran", "case1" + y + ".puml");

                GraphProcess gp = new GraphProcess(app0.getGraph(), app1.getGraph(), app2.getGraph());
                if(x == y)
                {
                    rowdd.add(0);
                    rowid.add(0);
                    rowdi.add(0);
                    rowii.add(0);
                }else
                {
                    rowdd.add(gp.countDeleteDelete());
                    rowid.add(gp.countInsertDelete());
                    rowdi.add(gp.countDeleteInsert());
                    rowii.add(gp.countInsertInsert());
                }
                
                if(x == y)
                {
                    rowsdd.add(0);
                    rowsid.add(0);
                    rowsdi.add(0);
                    rowsii.add(0);
                }else
                {
                    rowsdd.add(gp.countDeleteDelete());
                    rowsid.add(gp.countInsertDelete());
                    rowsdi.add(gp.countDeleteInsert());
                    rowsii.add(gp.countInsertInsert());
                }
                
                    //GraphCompare gc1 = new GraphCompare(app0.getGraph(), app1.getGraph());
                //GraphCompare gc2 = new GraphCompare(app0.getGraph(), app2.getGraph());
            }
            datadd.add(rowdd);
            dataid.add(rowid);
            datadi.add(rowdi);
            dataii.add(rowii);
            
//            dengan semantic
            datasdd.add(rowsdd);
            datasid.add(rowsid);
            datasdi.add(rowsdi);
            datasii.add(rowsii);
        }
        
        JTable tabledd = new JTable( datadd, headers );
        jScrollPanedd.getViewport().removeAll();
        jScrollPanedd.getViewport().add(tabledd);
        
        JTable tableid = new JTable( dataid, headers );
        jScrollPaneid.getViewport().removeAll();
        jScrollPaneid.getViewport().add(tableid);
        
        JTable tabledi = new JTable( datadi, headers );
        jScrollPanedi.getViewport().removeAll();
        jScrollPanedi.getViewport().add(tabledi);
        
        JTable tableii = new JTable( dataii, headers );
        jScrollPaneii.getViewport().removeAll();
        jScrollPaneii.getViewport().add(tableii);
        
        JTable tablesdd = new JTable( datadd, headers );
        jScrollPanesdd.getViewport().removeAll();
        jScrollPanesdd.getViewport().add(tablesdd);
        
        JTable tablesid = new JTable( dataid, headers );
        jScrollPanesid.getViewport().removeAll();
        jScrollPanesid.getViewport().add(tablesid);
        
        JTable tablesdi = new JTable( datadi, headers );
        jScrollPanesdi.getViewport().removeAll();
        jScrollPanesdi.getViewport().add(tablesdi);
        
        JTable tablesii = new JTable( dataii, headers );
        jScrollPanesii.getViewport().removeAll();
        jScrollPanesii.getViewport().add(tablesii);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPanedd = new javax.swing.JScrollPane();
        jScrollPaneid = new javax.swing.JScrollPane();
        jScrollPanedi = new javax.swing.JScrollPane();
        jScrollPaneii = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jScrollPanesdd = new javax.swing.JScrollPane();
        jScrollPanesid = new javax.swing.JScrollPane();
        jScrollPanesdi = new javax.swing.JScrollPane();
        jScrollPanesii = new javax.swing.JScrollPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(784, 633));

        jTabbedPane1.addTab("Delete - Delete", jScrollPanedd);
        jTabbedPane1.addTab("Insert - Delete", jScrollPaneid);
        jTabbedPane1.addTab("Delete - Insert", jScrollPanedi);
        jTabbedPane1.addTab("Insert - Insert", jScrollPaneii);

        jLabel1.setText("Tanpa Semantik");

        jLabel2.setText("Dengan Semantik");

        jTabbedPane2.addTab("Delete - Delete", jScrollPanesdd);
        jTabbedPane2.addTab("Insert - Delete", jScrollPanesid);
        jTabbedPane2.addTab("Delete - Insert", jScrollPanesdi);
        jTabbedPane2.addTab("Insert - Insert", jScrollPanesii);

        jMenu1.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Exit");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Case Study");

        jMenuItem2.setText("Parkiran");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem3.setText("Balapan");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jTabbedPane2))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        getParkiran();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        getBalapan();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

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
            java.util.logging.Logger.getLogger(MainApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainApp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPanedd;
    private javax.swing.JScrollPane jScrollPanedi;
    private javax.swing.JScrollPane jScrollPaneid;
    private javax.swing.JScrollPane jScrollPaneii;
    private javax.swing.JScrollPane jScrollPanesdd;
    private javax.swing.JScrollPane jScrollPanesdi;
    private javax.swing.JScrollPane jScrollPanesid;
    private javax.swing.JScrollPane jScrollPanesii;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    // End of variables declaration//GEN-END:variables
}
