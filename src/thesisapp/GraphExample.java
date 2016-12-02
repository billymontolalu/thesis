/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thesisapp;

import com.jgraph.layout.JGraphFacade;
import com.jgraph.layout.JGraphLayout;
import com.jgraph.layout.hierarchical.JGraphHierarchicalLayout;
import graphmodel.RelationshipEdge;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.util.Map;
import javax.swing.JApplet;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import org.jgraph.*;
import org.jgraph.graph.AttributeMap;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;

import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.ext.*;

// resolve ambiguity
import org.jgrapht.graph.DefaultEdge;

/**
 *
 * @author Momo
 */
public class GraphExample extends javax.swing.JFrame {
    private static final Dimension DEFAULT_SIZE = new Dimension(530, 320);
    private static final Color DEFAULT_BG_COLOR = Color.decode("#FAFBFF");
    private JGraphModelAdapter<String, DefaultEdge> jgAdapter;
    /**
     * Creates new form MainApp
     */
    public GraphExample() {
        initComponents();
        JDesktopPane dtp = new JDesktopPane();
        //dtp.setBackground(Color.GREEN);
        this.setContentPane(dtp);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        JInternalFrame mboxFrame = new JInternalFrame("Case 0", true,
        true, true, true);
        mboxFrame.setSize(400, 300);
        mboxFrame.setLocation(50, 50);
        mboxFrame.setVisible(true);
        
        JApplet applet = new JApplet();
        initGraph(applet);
        mboxFrame.getContentPane().add(applet);
        dtp.add(mboxFrame);
    }
    
    private void adjustDisplaySettings(JGraph jg, JApplet applet)
    {
        jg.setPreferredSize(DEFAULT_SIZE);

        Color c = DEFAULT_BG_COLOR;
        String colorStr = null;

        try {
            colorStr = applet.getParameter("bgcolor");
        } catch (Exception e) {
        }

        if (colorStr != null) {
            c = Color.decode(colorStr);
        }

        jg.setBackground(c);
    }
    
    private static class ListenableDirectedMultigraph<V, E>
        extends DefaultListenableGraph<V, E>
        implements DirectedGraph<V, E>
    {
        private static final long serialVersionUID = 1L;

        ListenableDirectedMultigraph(Class<E> edgeClass)
        {
            super(new DirectedMultigraph<V, E>(edgeClass));
        }
    }
    
    public void initGraph(JApplet applet)
    {
        
// create a JGraphT graph
        ListenableGraph<String, DefaultEdge> g =
            new ListenableDirectedMultigraph<String, DefaultEdge>(
                DefaultEdge.class);
        
        DirectedGraph dg = new DirectedMultigraph<>(
                    new ClassBasedEdgeFactory<Object, RelationshipEdge>(RelationshipEdge.class));
        
        // create a visualization using JGraph, via an adapter
        jgAdapter = new JGraphModelAdapter<String, DefaultEdge>(g);

        JGraph jgraph = new JGraph(jgAdapter);
        
        adjustDisplaySettings(jgraph, applet);
        applet.getContentPane().add(jgraph);
        applet.resize(DEFAULT_SIZE);

        String v1 = "v1";
        String v2 = "v2";
        String v3 = "v3";
        String v4 = "v4";
        
        // add some sample data (graph manipulated via JGraphT)
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        

        g.addEdge(v1, v2);
        g.addEdge(v2, v3);
        g.addEdge(v3, v1);
        g.addEdge(v4, v3);
        g.addEdge(v4, v2);
        
        
        DefaultGraphCell x = jgAdapter.getEdgeCell(g.getEdge(v1, v2));
        AttributeMap attrbute = x.getAttributes();
        GraphConstants.setLineColor(attrbute, Color.RED);
        GraphConstants.setLineEnd(attrbute, GraphConstants.ARROW_CIRCLE);
        
        DefaultGraphCell cell = jgAdapter.getVertexCell(v1);
        AttributeMap attr = cell.getAttributes();
        //Rectangle2D bounds = GraphConstants.getBounds(attr);
        GraphConstants.setBackground(attr, Color.BLUE);
        
        AttributeMap cellAttr = new AttributeMap();
        cellAttr.put(cell, attr);
        jgAdapter.edit(cellAttr, null, null, null);
        
        
        // position vertices nicely within JGraph component
//        positionVertexAt(v1, 130, 40);
//        positionVertexAt(v2, 60, 200);
//        positionVertexAt(v3, 310, 230);
//        positionVertexAt(v4, 380, 70);
        JGraphLayout layout = new JGraphHierarchicalLayout();
        JGraphFacade facade = new JGraphFacade(jgraph);
        layout.run(facade);
        Map nested = facade.createNestedMap(false, false);
        jgraph.getGraphLayoutCache().edit(nested);
        // that's all there is to it!...
    }
    
    private void positionVertexAtx(Object vertex, int x, int y)
    {
        DefaultGraphCell cell = jgAdapter.getVertexCell(vertex);
        AttributeMap attr = cell.getAttributes();
        Rectangle2D bounds = GraphConstants.getBounds(attr);

        Rectangle2D newBounds =
            new Rectangle2D.Double(
                x,
                y,
                bounds.getWidth(),
                bounds.getHeight());

        GraphConstants.setBounds(attr, newBounds);

        // TODO: Clean up generics once JGraph goes generic
        AttributeMap cellAttr = new AttributeMap();
        cellAttr.put(cell, attr);
        jgAdapter.edit(cellAttr, null, null, null);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Data");

        jMenuItem1.setText("Parkiran");
        jMenu4.add(jMenuItem1);

        jMenuItem2.setText("AST");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem2);

        jMenuBar2.add(jMenu4);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 772, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed
    
    //use ASTParse to parse string
    
    
    
    
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
            java.util.logging.Logger.getLogger(GraphExample.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GraphExample.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GraphExample.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GraphExample.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GraphExample().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    // End of variables declaration//GEN-END:variables
}
