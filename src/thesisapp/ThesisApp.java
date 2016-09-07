/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thesisapp;

import graphmodel.GraphProcess;
import graphmodel.GraphVis;
import model.Dataset;

/**
 *
 * @author Momo
 */
public class ThesisApp {
    
    
    public ThesisApp()
    {
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Dataset app0 = new Dataset();
        app0.readFile("g.puml");
        
        Dataset app1 = new Dataset();
        app1.readFile("b.puml");
        
        Dataset app2 = new Dataset();
        app2.readFile("c.puml");
        
        GraphProcess gp = new GraphProcess(app0.getGraph(), app1.getGraph(), app2.getGraph());
        
        //GraphCompare gc = new GraphCompare(app0.getGraph(), app2.getGraph());
        //DirectedGraph la = gc.getDelete();
        //gp.getC1();
        GraphVis.print(gp.getX());
//        GraphVis.print(app0.graph);
    }
    
}
