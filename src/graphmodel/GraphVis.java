/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphmodel;

import model.Atom;
import java.util.Set;
import model.Attribute;
import org.jgrapht.DirectedGraph;
import model.Class;
import model.Method;

/**
 *
 * @author Momo
 */
public final class GraphVis {
    public static String plantuml = "";
    public static String print(DirectedGraph dg){
        plantuml = "";
        plantuml = plantuml + "@startuml\n";
        printEdge(dg);
        printNoEdge(dg);
        System.out.println("");
        plantuml = plantuml + "@enduml";
        return plantuml;
    }
    
    private static void printEdge(DirectedGraph dg){
        for(Object edge : dg.edgeSet()){
            RelationshipEdge re = (RelationshipEdge) edge;
            
            Atom v1 = (Atom) re.getV1();
            Atom v2 = (Atom) re.getV2();
            if(v1 instanceof Class && v2 instanceof Method)
            {
                System.out.println(v1.getLabel() + " : " + v2.getLabel() + "()");
                plantuml = plantuml + v1.getLabel() + " : " + v2.getLabel() + "()\n";
            }else if(v2 instanceof Class && v1 instanceof Method)
            {
                System.out.println(v2.getLabel() + " : " + v1.getLabel() + "()");
                plantuml = plantuml + v2.getLabel() + " : " + v1.getLabel() + "()\n";
            }
            else if(v2 instanceof Attribute && v1 instanceof Class)
            {
                System.out.println(v1.getLabel() + " : " + v2.getLabel());
                plantuml = plantuml + v1.getLabel() + " : " + v2.getLabel() + "\n";
            }
            else if(v1 instanceof Attribute && v2 instanceof Class)
            {
                System.out.println(v2.getLabel() + " : " + v1.getLabel());
                plantuml = plantuml + v2.getLabel() + " : " + v1.getLabel() + "\n";
            }else
            {
                System.out.println(v1.getLabel() + " " + re.getLabel() + " " + v2.getLabel());
                plantuml = plantuml + v1.getLabel() + " " + re.getLabel() + " " + v2.getLabel() + "\n";
            }
            
        }
    }
    
    private static void printNoEdge(DirectedGraph dg){
        for(Object vertex : dg.vertexSet()){
            Set edge = dg.edgesOf(vertex);
            if(edge.isEmpty()){
                Atom a = (Atom) vertex;
                System.out.println(a.getLabel());
                plantuml = plantuml + a.getLabel() + "\n";
            }
        }
    }
}
