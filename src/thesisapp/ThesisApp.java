/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thesisapp;

import graphmodel.GraphCompare;
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
        app0.readFile("case10.puml");
        
        Dataset app1 = new Dataset();
        app1.readFile("case11.puml");
        
        Dataset app2 = new Dataset();
        app2.readFile("case12.puml");
        
        GraphProcess gp = new GraphProcess(app0.getGraph(), app1.getGraph(), app2.getGraph());
        GraphCompare gc1 = new GraphCompare(app0.getGraph(), app1.getGraph());
        GraphCompare gc2 = new GraphCompare(app0.getGraph(), app2.getGraph());
        //DirectedGraph la = gc.getDelete();
        //gp.getC1();
        //GraphVis.print(gc2.getNewNode());
        GraphVis.print(gp.mergeSemantic());
        //GraphVis.print(gp.getC2());
        //GraphVis.print(gp.getX());
        
        //GraphVis.print(gc1.getUnchange());
        //GraphVis.print(gc1.getR1());
//        GraphVis.print(app0.graph);
        //GraphVis.print(gc.getUnchange());
        //GraphVis.print(gc.getDelete());
    }
    
}
