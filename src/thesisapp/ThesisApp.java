/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thesisapp;

import graphmodel.GraphProcess;
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
        System.out.print(" ");
        for (int x = 1; x < 5; x++) {
            System.out.print(" " + x);
        }
        System.out.println("");
        for (int x = 1; x < 5; x++) {
            System.out.print(x);
            for (int y = 1; y < 5; y++) {
                Dataset app0 = new Dataset();
                app0.readFile("case10.puml");

                Dataset app1 = new Dataset();
                app1.readFile("case1" + x + ".puml");

                Dataset app2 = new Dataset();
                app2.readFile("case1" + y + ".puml");

                GraphProcess gp = new GraphProcess(app0.getGraph(), app1.getGraph(), app2.getGraph());
                System.out.print(" " + gp.countDeleteDelete());
                    //GraphCompare gc1 = new GraphCompare(app0.getGraph(), app1.getGraph());
                //GraphCompare gc2 = new GraphCompare(app0.getGraph(), app2.getGraph());
            }
            System.out.println();
        }

    }

}
