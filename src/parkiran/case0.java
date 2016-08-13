/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parkiran;

import graphmodel.EdgeVis;
import graphmodel.GraphCompare;
import graphmodel.GraphVis;
import graphmodel.RelationshipEdge;
import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.ClassBasedEdgeFactory;
import org.jgrapht.graph.DirectedMultigraph;
import model.Class;

/**
 *
 * @author Momo
 */
public class case0 {
    DirectedGraph getData0(){
        DirectedGraph<Object, RelationshipEdge> graph =
            new DirectedMultigraph<>(
                    new ClassBasedEdgeFactory<Object, RelationshipEdge>(RelationshipEdge.class));
        
        Class camera = new Class();
        camera.setLabel("Camera");
        
        Class controlSystem = new Class();
        controlSystem.setLabel("ControlSystem");
        
        Class ticket = new Class();
        ticket.setLabel("Ticket");
        
        Class gate = new Class();
        gate.setLabel("Gate");
        
        graph.addVertex(camera);
        graph.addVertex(controlSystem);
        graph.addVertex(ticket);
        graph.addVertex(gate);
        
        graph.addEdge(controlSystem, camera, new RelationshipEdge(controlSystem, camera, EdgeVis.METHOD));
        graph.addEdge(controlSystem, ticket, new RelationshipEdge(controlSystem, ticket, EdgeVis.METHOD));
        graph.addEdge(controlSystem, gate, new RelationshipEdge(controlSystem, gate, EdgeVis.METHOD));
        
        return graph;
    }
    DirectedGraph getData1(){
        DirectedGraph<Object, RelationshipEdge> graph =
            new DirectedMultigraph<>(
                    new ClassBasedEdgeFactory<Object, RelationshipEdge>(RelationshipEdge.class));
        
        Class camera = new Class();
        camera.setLabel("Camera");
        
        Class controlSystem = new Class();
        controlSystem.setLabel("ControlSystem");
        
        Class ticket = new Class();
        ticket.setLabel("Ticket");
        
        Class gate = new Class();
        gate.setLabel("Gate");
        
        Class vehicle = new Class();
        vehicle.setLabel("Vehicle");
        
        graph.addVertex(camera);
        graph.addVertex(controlSystem);
        graph.addVertex(ticket);
        graph.addVertex(gate);
        graph.addVertex(vehicle);
        
        graph.addEdge(vehicle, controlSystem, new RelationshipEdge(vehicle, controlSystem, EdgeVis.METHOD));
        graph.addEdge(controlSystem, camera, new RelationshipEdge(controlSystem, camera, EdgeVis.METHOD));
        graph.addEdge(controlSystem, ticket, new RelationshipEdge(controlSystem, ticket, EdgeVis.METHOD));
        graph.addEdge(controlSystem, gate, new RelationshipEdge(controlSystem, gate, EdgeVis.METHOD));
        
        return graph;
    }
    
    public static void main(String[] args){
        case0 c = new case0();
        DirectedGraph dg0 = c.getData0();
        DirectedGraph dg1 = c.getData1();
        
        //GraphVis.print(dg2);
        GraphCompare gc = new GraphCompare(dg0, dg1);
        DirectedGraph la = gc.getLA();
        GraphVis.print(la);
    }
}
