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
        app0.readFile("scase0.puml");
        
        Dataset app1 = new Dataset();
        app1.readFile("scase1.puml");
        
        Dataset app2 = new Dataset();
        app2.readFile("scase2.puml");
        
        GraphProcess gp = new GraphProcess(app0.getGraph(), app1.getGraph(), app2.getGraph());
        GraphCompare gc1 = new GraphCompare(app0.getGraph(), app1.getGraph());
        GraphCompare gc2 = new GraphCompare(app0.getGraph(), app2.getGraph());
        //DirectedGraph la = gc.getDelete();
        //gp.getC1();
//        GraphVis.print(gp.getC1());
        //GraphVis.print(gp.getC2());
        GraphVis.print(gc2.getAdditon());
//        GraphVis.print(app0.graph);
        //GraphVis.print(gc.getUnchange());
        //GraphVis.print(gc.getDelete());
    }
    
}
