/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thesisapp;

import graphmodel.GraphProcess;
import graphmodel.RelationshipEdge;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import model.Atom;
import model.Attribute;
import model.Dataset;
import model.Method;
import org.jgrapht.DirectedGraph;

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
    
    private void printSemantic(ArrayList graphList, ArrayList indexList)
    {
        ArrayList<DirectedGraph> dgList = graphList;
        int count = 0;
        for(DirectedGraph dg : dgList)
        {
            for(Object o : dg.edgeSet())
            {
                RelationshipEdge re = (RelationshipEdge) o;
                Atom v1 = (Atom)re.getV1();
                Atom v2 = (Atom)re.getV2();
                String index = (String)indexList.get(count);
                if(v1 instanceof Method){
                    Method m1 = (Method) v1;
                    Method m2 = (Method) v2;
                    System.out.println(index + "-" + m1.getParent().getLabel() + ":" + m1.getLabel() + " -- " + m2.getParent().getLabel() + ":" + m2.getLabel());
                }else if(v1 instanceof Attribute)
                {
                    Attribute a1 = (Attribute) v1;
                    Attribute a2 = (Attribute) v2;
                    System.out.println(index + "-" + a1.getParent().getLabel() + ":" + a1.getLabel() + " -- " + a2.getParent().getLabel() + ":" + a2.getLabel());
                }
                else
                {
                    System.out.println(index + "-" + v1.getLabel() + " -- " + v2.getLabel());
                }
            }
            count++;
        }
    }

    private void getData(String data, String order) {
        jProgressBar1.setValue(0);
        jProgressBar1.setMaximum(30);
        jProgressBar1.setStringPainted(true);

        int count_case = 30;
        int totalII = 0;
        int totalIIS = 0;
        ArrayList semanticList = new ArrayList();
        ArrayList indexList = new ArrayList();
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
        for (int x = 1; x <= count_case; x++) {
            headers.add(Integer.toString(x));
        }

        for (int x = 1; x <= count_case; x++) {
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

            for (int y = 1; y <= count_case; y++) {
                Dataset app0 = new Dataset();
                app0.readFile(data, "case" + order + "0.puml");

                Dataset app1 = new Dataset();
                app1.readFile(data, "case" + order + x + ".puml");

                Dataset app2 = new Dataset();
                app2.readFile(data, "case" + order + y + ".puml");

                GraphProcess gp = new GraphProcess(app0.getGraph(), app1.getGraph(), app2.getGraph());
                if (x == y) {
                    rowdd.add(0);
                    rowid.add(0);
                    rowdi.add(0);
                    rowii.add(0);

                    //menambahkan dengan semantuc
                    rowsdd.add(0);
                    rowsid.add(0);
                    rowsdi.add(0);
                    rowsii.add(0);
                } else {
                    int cII = gp.countInsertInsert();
                    int cIIS = gp.countInsertSemantic();
                    //gp.getInsertInsertSemantic();
                    semanticList.add(gp.getInsertInsertSemantic());
                    indexList.add(x + "," + y);
                    totalII = totalII + cII;
                    totalIIS = totalIIS + cIIS;
                    
                    rowdd.add(gp.countDeleteDelete());
                    rowid.add(gp.countInsertDelete());
                    rowdi.add(gp.countDeleteInsert());
                    rowii.add(cII);

                    rowsdd.add(gp.countDeleteDelete());
                    rowsid.add(gp.countInsertDelete());
                    rowsdi.add(gp.countDeleteInsert());
                    rowsii.add(cIIS);
                }
                jProgressBar1.setString(x + " vs " + y);
            }
            
            jLabelII.setText(Integer.toString(totalII));
            jLabelIIS.setText(Integer.toString(totalIIS));

            datadd.add(rowdd);
            dataid.add(rowid);
            datadi.add(rowdi);
            dataii.add(rowii);

            //semantic
            datasdd.add(rowsdd);
            datasid.add(rowsid);
            datasdi.add(rowsdi);
            datasii.add(rowsii);
            jProgressBar1.setValue(x);

            JTable tabledd = new JTable(datadd, headers);
            jScrollPanedd.getViewport().removeAll();
            jScrollPanedd.getViewport().add(tabledd);

            JTable tableid = new JTable(dataid, headers);
            jScrollPaneid.getViewport().removeAll();
            jScrollPaneid.getViewport().add(tableid);

            JTable tabledi = new JTable(datadi, headers);
            jScrollPanedi.getViewport().removeAll();
            jScrollPanedi.getViewport().add(tabledi);

            JTable tableii = new JTable(dataii, headers);
            jScrollPaneii.getViewport().removeAll();
            jScrollPaneii.getViewport().add(tableii);

            JTable tablesdd = new JTable(datasdd, headers);
            jScrollPanesdd.getViewport().removeAll();
            jScrollPanesdd.getViewport().add(tablesdd);

            JTable tablesid = new JTable(datasid, headers);
            jScrollPanesid.getViewport().removeAll();
            jScrollPanesid.getViewport().add(tablesid);

            JTable tablesdi = new JTable(datasdi, headers);
            jScrollPanesdi.getViewport().removeAll();
            jScrollPanesdi.getViewport().add(tablesdi);

            JTable tablesii = new JTable(datasii, headers);
            jScrollPanesii.getViewport().removeAll();
            jScrollPanesii.getViewport().add(tablesii);
            tablesii.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                //int score = (int) tableii.getModel().getValueAt(row, column);

                    if (!table.isRowSelected(row)) {
                        if (!value.equals(tableii.getModel().getValueAt(row, column))) {
                            c.setBackground(new java.awt.Color(239, 255, 0));
                        } else {
                            c.setBackground(table.getBackground());
                        }
                    }
                    return c;
                }
            });
            JFrame jFrame = this;
            tablesii.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    int row = tablesii.rowAtPoint(evt.getPoint());
                    int col = tablesii.columnAtPoint(evt.getPoint());
                    if (row >= 0 && col >= 0) {
                        java.awt.EventQueue.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                Detil detil = new Detil(jLabelStudiKasus.getText(), (row + 1), col, jFrame);
                                detil.setVisible(true);
                            }
                        });
                        
                    }
                }
            });
        }
        printSemantic(semanticList, indexList);
    }

    public enum Type {

        SEMANTICINSERTINSERT
    }

    private void setListener(JTable table, Type type) {
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint());
                int col = table.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    JOptionPane.showMessageDialog(rootPane, row + " " + col);
                }
                switch (type) {
                    case SEMANTICINSERTINSERT:

                    default:
                        break;
                }
            }
        });
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
        jLabelStudiKasus = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel3 = new javax.swing.JLabel();
        jLabelII = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabelIIS = new javax.swing.JLabel();
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

        jLabelStudiKasus.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel3.setText("Total Kasus Insert Insert :");

        jLabelII.setText("0");

        jLabel4.setText("Total Kasus Insert Insert Semantic :");

        jLabelIIS.setText("0");

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
                            .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(438, 438, 438)
                        .addComponent(jLabelStudiKasus, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelII, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelIIS, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabelStudiKasus, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabelII)
                    .addComponent(jLabel4)
                    .addComponent(jLabelIIS))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        jLabelStudiKasus.setText("parkiran");
        new Thread(new Runnable() {

            @Override
            public void run() {
                long startTime = System.currentTimeMillis();
                //getParkiran();
                getData("parkiran", "1");
                long endTime = System.currentTimeMillis();
                long totalTime = endTime - startTime;
                System.out.println(TimeUnit.MILLISECONDS.toMinutes(totalTime));
            }
        }).start();

    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        jLabelStudiKasus.setText("balapan");

        new Thread(new Runnable() {
            @Override
            public void run() {
                long startTime = System.currentTimeMillis();
                //getBalapan();
                getData("balapan", "2");
                long endTime = System.currentTimeMillis();
                long totalTime = endTime - startTime;
                System.out.println(TimeUnit.MILLISECONDS.toMinutes(totalTime));
            }
        }).start();
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelII;
    private javax.swing.JLabel jLabelIIS;
    private javax.swing.JLabel jLabelStudiKasus;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JProgressBar jProgressBar1;
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
