/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphmodel;

import model.Atom;
import model.Relation;
import org.jgrapht.DirectedGraph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.ClassBasedEdgeFactory;
import org.jgrapht.graph.DirectedMultigraph;

/**
 *
 * @author Momo
 */
public class GraphCompare {
    private final DirectedGraph v1, v2;
    
    public GraphCompare(DirectedGraph v1, DirectedGraph v2){
        this.v1 = v1;
        this.v2 = v2;
    }
    
    public DirectedGraph getAdditon()
    {
        DirectedGraph dg = new DirectedMultigraph<>(
                    new ClassBasedEdgeFactory<Object, RelationshipEdge>(RelationshipEdge.class));
        boolean found;
        for(Object vertex2 : v2.vertexSet()){
            found = false;
            Atom a2 = (Atom) vertex2;
            for(Object vertex1 : v1.vertexSet()){
                Atom a1 = (Atom) vertex1;
                
                if(a1.getLabel().equals(a2.getLabel())){
                    found = true;
                    break;
                }
            }
            if(found == false)
            {
                dg.addVertex(vertex2);
            }
        }
        return dg;
    }
    
    public DirectedGraph getEdit()
    {
        DirectedGraph dg = new DirectedMultigraph<>(
                    new ClassBasedEdgeFactory<Object, RelationshipEdge>(RelationshipEdge.class));
        //mencari node yang didelete
        boolean found;
        for(Object vertex1 : v1.vertexSet()){
            found = false;
            Atom a1 = (Atom) vertex1;
            for(Object vertex2 : v2.vertexSet()){
                Atom a2 = (Atom) vertex2;
                
                if(a1.getLabel().equals(a2.getLabel())){
                    found = true;
                    break;
                }
            }
            if(found == false)
            {
                dg.addVertex(vertex1);
            }
        }
        //mencari relasi yang didelete
        for(Object edge1 : v1.edgeSet())
        {
            found = false;
            RelationshipEdge re1 = (RelationshipEdge) edge1;
            Atom v1_1 = (Atom) re1.getV1();
            Atom v1_2 = (Atom) re1.getV2();
            for(Object edge2 : v2.edgeSet()){
                RelationshipEdge re2 = (RelationshipEdge) edge2;
                
                Atom v2_1 = (Atom) re2.getV1();
                Atom v2_2 = (Atom) re2.getV2();
                if(v1_1.getLabel().equals(v2_1.getLabel()) && v1_2.getLabel().equals(v2_2.getLabel()) && re1.getLabel().equals(re2.getLabel())){
                    found = true;
                    break;
                }
            }
            if(found == false)
            {
                Relation rel = new Relation();
                rel.setType(re1.getLabel());
                rel.setSource(v1_1);
                rel.setDestination(v1_2);
                dg.addVertex(rel);
            }
        }
        return dg;
    }
    
    public DirectedGraph getDelete()
    {
        DirectedGraph dg = new DirectedMultigraph<>(
                    new ClassBasedEdgeFactory<Object, RelationshipEdge>(RelationshipEdge.class));
        //mencari node yang didelete
        boolean found;
        for(Object vertex1 : v1.vertexSet()){
            found = false;
            Atom a1 = (Atom) vertex1;
            for(Object vertex2 : v2.vertexSet()){
                Atom a2 = (Atom) vertex2;
                
                if(a1.getLabel().equals(a2.getLabel())){
                    found = true;
                    break;
                }
            }
            if(found == false)
            {
                dg.addVertex(vertex1);
            }
        }
        //mencari relasi yang didelete
        for(Object edge1 : v1.edgeSet())
        {
            found = false;
            RelationshipEdge re1 = (RelationshipEdge) edge1;
            Atom v1_1 = (Atom) re1.getV1();
            Atom v1_2 = (Atom) re1.getV2();
            for(Object edge2 : v2.edgeSet()){
                RelationshipEdge re2 = (RelationshipEdge) edge2;
                
                Atom v2_1 = (Atom) re2.getV1();
                Atom v2_2 = (Atom) re2.getV2();
                if(v1_1.getLabel().equals(v2_1.getLabel()) && v1_2.getLabel().equals(v2_2.getLabel()) && re1.getLabel().equals(re2.getLabel())){
                    found = true;
                    break;
                }
            }
            if(found == false)
            {
                Relation rel = new Relation();
                rel.setType(re1.getLabel());
                rel.setSource(v1_1);
                rel.setDestination(v1_2);
                dg.addVertex(rel);
            }
        }
        return dg;
    }
    
    public DirectedGraph getUnchange(){
        DirectedGraph dgSameEdge = getSameEdge();
        DirectedGraph dgSameNoEdge = getSameNoEdge();
        
        Graphs.addGraph(dgSameEdge, dgSameNoEdge);
        return dgSameEdge;
    }
    
    DirectedGraph getSameNoEdge(){
        DirectedGraph dg = new DirectedMultigraph<>(
                    new ClassBasedEdgeFactory<Object, RelationshipEdge>(RelationshipEdge.class));
        for(Object vertex1 : v1.vertexSet()){
            Atom a1 = (Atom) vertex1;
            for(Object vertex2 : v2.vertexSet()){
                Atom a2 = (Atom) vertex2;
                if(a1.getLabel().equals(a2.getLabel())){
                    dg.addVertex(a1);
                }
            }
        }
        return dg;
    }
    
    DirectedGraph getSameEdge(){
        DirectedGraph dg = new DirectedMultigraph<>(
                    new ClassBasedEdgeFactory<Object, RelationshipEdge>(RelationshipEdge.class));
        for(Object edge1 : v1.edgeSet()){
            RelationshipEdge re1 = (RelationshipEdge) edge1;
            for(Object edge2 : v2.edgeSet()){
                RelationshipEdge re2 = (RelationshipEdge) edge2;
                Atom v1_1 = (Atom) re1.getV1();
                Atom v1_2 = (Atom) re1.getV2();
                Atom v2_1 = (Atom) re2.getV1();
                Atom v2_2 = (Atom) re2.getV2();
                if(v1_1.getLabel().equals(v2_1.getLabel()) && v1_2.getLabel().equals(v2_2.getLabel()) && re1.getLabel().equals(re2.getLabel())){
                    dg.addVertex(v1_1);
                    dg.addVertex(v1_2);
                    dg.addEdge(v1_1, v1_2, new RelationshipEdge(v1_1, v1_2, re1.getLabel()));
                    break;
                }
            }
        }
        return dg;
    }
    
    public DirectedGraph getB1(){
        DirectedGraph dg = new DirectedMultigraph<>(
                    new ClassBasedEdgeFactory<Object, RelationshipEdge>(RelationshipEdge.class));
        boolean find;
        for(Object edge1 : v1.edgeSet()){
            find = false;
            RelationshipEdge re1 = (RelationshipEdge) edge1;
            for(Object edge2 : v2.edgeSet()){
                RelationshipEdge re2 = (RelationshipEdge) edge2;
                Atom v1_1 = (Atom) re1.getV1();
                Atom v1_2 = (Atom) re1.getV2();
                Atom v2_1 = (Atom) re2.getV1();
                Atom v2_2 = (Atom) re2.getV2();
                if(v1_1.getLabel().equals(v2_1.getLabel()) && v1_2.getLabel().equals(v2_2.getLabel())){
                    find = true;
                    break;
                }
            }
            if(find == false){
                dg.addVertex(re1.getV1());
                dg.addVertex(re1.getV2());
            }
        }
        return dg;
    }
    
    public DirectedGraph getB2(){
        boolean find;
        DirectedGraph dg = new DirectedMultigraph<>(
                    new ClassBasedEdgeFactory<Object, RelationshipEdge>(RelationshipEdge.class));
        DirectedGraph del = getUnchange();
        for(Object edge2 : v2.edgeSet()){
            find = false;
            RelationshipEdge re2 = (RelationshipEdge) edge2;
            for(Object edgeDel : del.edgeSet()){
                RelationshipEdge reDel = (RelationshipEdge) edgeDel;
                Atom v2_1 = (Atom) re2.getV1();
                Atom v2_2 = (Atom) re2.getV2();
                Atom vdel_1 = (Atom) reDel.getV1();
                Atom vdel_2 = (Atom) reDel.getV2();
                if(v2_1.getLabel().equals(vdel_1.getLabel()) && v2_2.getLabel().equals(vdel_2.getLabel())){
                    find = true;
                    break;
                }
            }
            if(find == false){
                dg.addVertex(re2.getV1());
                dg.addVertex(re2.getV2());
            }
        }
        return dg;
    }
    
    public DirectedGraph getL1(){
        boolean node1, node2;
        DirectedGraph dg = new DirectedMultigraph<>(
                    new ClassBasedEdgeFactory<Object, RelationshipEdge>(RelationshipEdge.class));
        DirectedGraph B1 = getB1();
        for(Object edge1 : v1.edgeSet()){
            node1 = false;
            node2 = false;
            RelationshipEdge re1 = (RelationshipEdge) edge1;
            Atom a1 = (Atom) re1.getV1();
            Atom a2 = (Atom) re1.getV2();
            for(Object vertexb1 : B1.vertexSet()){
                Atom b1 = (Atom) vertexb1;
                if(a1.getLabel().equals(b1.getLabel())){
                    node1 = true;
                }
                if(a2.getLabel().equals(b1.getLabel())){
                    node2 = true;
                }
            }
            if(node1 == true && node2 == true){
                dg.addVertex(a1);
                dg.addVertex(a2);
                dg.addEdge(a1, a2, new RelationshipEdge(a1, a2, re1.getLabel()));
            }
            
        }
        return dg;
    }
    
    public DirectedGraph getR1(){
        boolean node1, node2;
        DirectedGraph dg = new DirectedMultigraph<>(
                    new ClassBasedEdgeFactory<Object, RelationshipEdge>(RelationshipEdge.class));
        DirectedGraph B2 = getB2();
        for(Object edge2 : v2.edgeSet()){
            node1 = false;
            node2 = false;
            RelationshipEdge re2 = (RelationshipEdge) edge2;
            Atom a1 = (Atom) re2.getV1();
            Atom a2 = (Atom) re2.getV2();
            for(Object vertexb2 : B2.vertexSet()){
                Atom b2 = (Atom) vertexb2;
                if(a1.getLabel().equals(b2.getLabel())){
                    node1 = true;
                }
                if(a2.getLabel().equals(b2.getLabel())){
                    node2 = true;
                }
            }
            if(node1 == true && node2 == true){
                dg.addVertex(a1);
                dg.addVertex(a2);
                dg.addEdge(a1, a2, new RelationshipEdge(a1, a2, re2.getLabel()));
            }
            
        }
        return dg;
    }
    
    public DirectedGraph getKA(){
        DirectedGraph B1 = getB1();
        DirectedGraph B2 = getB2();
        Graphs.addGraph(B1, B2);
        return B1;
    }
    
    public DirectedGraph getRA(){
        DirectedGraph ka = getKA();
        DirectedGraph r1 = getR1();
        Graphs.addGraph(ka, r1);
        return ka;
    }
    
    public DirectedGraph getLA(){
        DirectedGraph ka = getKA();
        DirectedGraph l1 = getL1();
        Graphs.addGraph(ka, l1);
        return ka;
    }
}
