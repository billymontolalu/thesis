/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphmodel;

import model.Atom;
import java.util.Set;
import org.jgrapht.DirectedGraph;

/**
 *
 * @author Momo
 */
public final class GraphVis {
    public static void print(DirectedGraph dg){
        printEdge(dg);
        printNoEdge(dg);
        System.out.println("");
    }
    
    private static void printEdge(DirectedGraph dg){
        for(Object edge : dg.edgeSet()){
            RelationshipEdge re = (RelationshipEdge) edge;
            
            Atom v1 = (Atom) re.getV1();
            Atom v2 = (Atom) re.getV2();
            System.out.println(v1.getLabel() + " " + re.getLabel() + " " + v2.getLabel());
        }
    }
    
    private static void printNoEdge(DirectedGraph dg){
        for(Object vertex : dg.vertexSet()){
            Set edge = dg.edgesOf(vertex);
            if(edge.isEmpty()){
                Atom a = (Atom) vertex;
                System.out.println(a.getLabel());
            }
        }
    }
}
