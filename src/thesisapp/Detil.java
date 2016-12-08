/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thesisapp;

import graphmodel.GraphProcess;
import graphmodel.GraphVis;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import model.Dataset;

/**
 *
 * @author Momo
 */
public class Detil extends javax.swing.JFrame {
    /**
     * Creates new form Detil
     * @param caseName
     * @param case1
     * @param case2
     * @param parent
     */
    public Detil(String caseName, int case1, int case2, JFrame parent) {
        initComponents();
        
        String caseBase = "";
        switch (caseName) {
            case "parkiran":
                caseBase = "case1";
                break;
            case "balapan":
                caseBase = "case2";
                break;
            default :
                break;
        }
        
        Dataset app0 = new Dataset();
        app0.readFile(caseName, caseBase + "0.puml");
        String v0 = GraphVis.print(app0.getGraph());
        PlantInternalFrame plantIf0 = new thesisapp.PlantInternalFrame("Case0", v0);
        jDesktopPane1.add(plantIf0);
        plantIf0.show();
        
        Dataset app1 = new Dataset();
        app1.readFile(caseName, caseBase + case1 + ".puml");
        String v1 = GraphVis.print(app1.getGraph());
        PlantInternalFrame plantIf1 = new thesisapp.PlantInternalFrame("Case" + case1, v1);
        jDesktopPane1.add(plantIf1);
        plantIf1.show();
        
        Dataset app2 = new Dataset();
        app2.readFile(caseName, caseBase + case2 + ".puml");
        String v2 = GraphVis.print(app2.getGraph());
        PlantInternalFrame plantIf2 = new thesisapp.PlantInternalFrame("Case" + case2, v2);
        jDesktopPane1.add(plantIf2);
        plantIf2.show();
        
        GraphProcess gp = new GraphProcess(app0.getGraph(), app1.getGraph(), app2.getGraph());
        
        // if jika conflict
        if(gp.isConflict())
        {
            String conflict = "";
            conflict = conflict + GraphVis.print(gp.getDeleteDelete());
            conflict = conflict + GraphVis.print(gp.getDeleteInsert());
            conflict = conflict + GraphVis.print(gp.getInsertDelete());
            conflict = conflict + GraphVis.print(gp.getInsertInsert());
            conflict = conflict + GraphVis.print(gp.getInsertInsertSemantic());
            ConflictJInternalFrame cJf = new ConflictJInternalFrame(conflict);
            jDesktopPane1.add(cJf);
            cJf.show();
        }else
        {
            String merge = GraphVis.print(gp.mergeSemantic());
            PlantInternalFrame plantIfMerge = new thesisapp.PlantInternalFrame("Merge", merge);
            jDesktopPane1.add(plantIfMerge);
            plantIfMerge.show();
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Detil Graph");

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 767, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 435, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Detil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Detil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Detil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Detil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new Detil().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDesktopPane1;
    // End of variables declaration//GEN-END:variables
}
