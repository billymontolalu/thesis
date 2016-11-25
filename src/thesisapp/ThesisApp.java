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

    public ThesisApp() {

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        /*System.out.print(" ");
        for (int x = 1; x < 5; x++) {
            System.out.print(" " + x);
        }
        System.out.println("");
        for (int x = 1; x < 5; x++) {
            System.out.print(x);
            for (int y = 1; y < 5; y++) {
                Dataset app0 = new Dataset();
                app0.readFile("parkiran", "case10.puml");

                Dataset app1 = new Dataset();
                app1.readFile("parkiran", "case1" + x + ".puml");

                Dataset app2 = new Dataset();
                app2.readFile("parkiran", "case1" + y + ".puml");

                GraphProcess gp = new GraphProcess(app0.getGraph(), app1.getGraph(), app2.getGraph());
                //System.out.print(" " + gp.countDeleteDelete());
                    //GraphCompare gc1 = new GraphCompare(app0.getGraph(), app1.getGraph());
                //GraphCompare gc2 = new GraphCompare(app0.getGraph(), app2.getGraph());
            }
            System.out.println();
        }*/
        
        Dataset app0 = new Dataset();
        app0.readFile("parkiran", "scase0.puml");

        Dataset app1 = new Dataset();
        app1.readFile("parkiran", "scase1.puml");

        Dataset app2 = new Dataset();
        app2.readFile("parkiran", "scase2.puml");
        
        GraphProcess gp = new GraphProcess(app0.getGraph(), app1.getGraph(), app2.getGraph());
        GraphVis.print(gp.getX());
        System.out.println("");
        GraphVis.print(gp.mergeSemantic());
        //System.out.println(gp.getX());
    }

}
