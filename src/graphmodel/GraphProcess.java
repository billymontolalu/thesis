/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphmodel;

import model.Atom;
import model.Class;
import model.Method;
import model.Attribute;
import org.jgrapht.DirectedGraph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.ClassBasedEdgeFactory;
import org.jgrapht.graph.DirectedMultigraph;

/**
 *
 * @author Momo
 */
public class GraphProcess {
    private DirectedGraph v0, v1, v2;
    public GraphProcess(DirectedGraph v0, DirectedGraph v1, DirectedGraph v2)
    {
        this.v0 = v0;
        this.v1 = v1;
        this.v2 = v2;
    }
    
    public void getSimantic()
    {
        //jika ada relasi yang sama maka, itu akan jadi kunci lokasi, 
        //sedangkan relasi yang tidak sama akan dipindahkan
        GraphCompare gc1 = new GraphCompare(v0, v1);
        DirectedGraph dg1 = gc1.getAdditon();
        
        GraphCompare gc2 = new GraphCompare(v0, v2);
        DirectedGraph dg2 = gc2.getAdditon();
        for(Object vertex1 : dg1.vertexSet())
        {
            Atom a1 = (Atom) vertex1;
            for(Object vertex2 : dg2.vertexSet())
            {
                Atom a2 = (Atom) vertex2;
                if(a1 instanceof Class)
                {
                    boolean isSimiliar = Semantic.isSimiliar(a1.getLabel(), a2.getLabel());
                    if(isSimiliar)
                    {
                        
                    }
                }
                else if(a1 instanceof Method || a1 instanceof Attribute)
                {
                }
            }
        }
    }
    
    public DirectedGraph getDeleteInsert()
    {
        DirectedGraph dg = new DirectedMultigraph<>(
                    new ClassBasedEdgeFactory<Object, RelationshipEdge>(RelationshipEdge.class));
        
        GraphCompare gc1 = new GraphCompare(v0, v1);
        DirectedGraph del1 = gc1.getDelete();
        
        GraphCompare gc2 = new GraphCompare(v0, v2);
        DirectedGraph edit2 = gc2.getKA();
        
        for(Object vertex1 : del1.vertexSet()){
            Atom a1 = (Atom) vertex1;
            for(Object vertex2 : edit2.vertexSet()){
                Atom a2 = (Atom) vertex2;
                if(a1.getLabel().equals(a2.getLabel())){
                    dg.addVertex(a1);
                }
            }
        }
        
        return dg;
    }
    
    public DirectedGraph getInsertDelete()
    {
        DirectedGraph dg = new DirectedMultigraph<>(
                    new ClassBasedEdgeFactory<Object, RelationshipEdge>(RelationshipEdge.class));
        
        GraphCompare gc1 = new GraphCompare(v0, v2);
        DirectedGraph del1 = gc1.getDelete();
        
        GraphCompare gc2 = new GraphCompare(v0, v1);
        DirectedGraph edit2 = gc2.getKA();
        
        for(Object vertex1 : del1.vertexSet()){
            Atom a1 = (Atom) vertex1;
            for(Object vertex2 : edit2.vertexSet()){
                Atom a2 = (Atom) vertex2;
                if(a1.getLabel().equals(a2.getLabel())){
                    dg.addVertex(a1);
                }
            }
        }
        
        return dg;
    }
    
    public DirectedGraph getDeleteDelete()
    {
        DirectedGraph dg = new DirectedMultigraph<>(
                    new ClassBasedEdgeFactory<Object, RelationshipEdge>(RelationshipEdge.class));
        
        GraphCompare gc1 = new GraphCompare(v0, v1);
        DirectedGraph del1 = gc1.getDelete();
        GraphCompare gc2 = new GraphCompare(v0, v2);
        DirectedGraph del2 = gc2.getDelete();
        
        for(Object vertex1 : del1.vertexSet()){
            Atom a1 = (Atom) vertex1;
            for(Object vertex2 : del2.vertexSet()){
                Atom a2 = (Atom) vertex2;
                if(a1.getLabel().equals(a2.getLabel())){
                    dg.addVertex(a1);
                }
            }
        }
        
        return dg;
    }
    
    public DirectedGraph getD1()
    {
        GraphCompare gc = new GraphCompare(v0, v1);
        return gc.getUnchange();
    }
    
    public DirectedGraph getD2()
    {
        GraphCompare gc = new GraphCompare(v0, v2);
        return gc.getUnchange();
    }
    
    public DirectedGraph getD()
    {
        GraphCompare gc = new GraphCompare(getD1(), getD2());
        return gc.getUnchange();
    }
    
    public DirectedGraph getC1()
    {
        GraphCompare gc = new GraphCompare(v1, getD1());
        DirectedGraph del = gc.getDelete();
        
        GraphCompare gc2 = new GraphCompare(del, getD());
        DirectedGraph del2 = gc2.getDelete();
        
        DirectedGraph d = getD();
        Graphs.addGraph(d, del2);
        return d;
    }
    
    public DirectedGraph getC2()
    {
        GraphCompare gc = new GraphCompare(v2, getD2());
        DirectedGraph del = gc.getDelete();
        
        GraphCompare gc2 = new GraphCompare(del, getD());
        DirectedGraph del2 = gc2.getDelete();
        
        DirectedGraph d = getD();
        Graphs.addGraph(d, del2);
        return d;
    }
    
    public DirectedGraph getX()
    {
        DirectedGraph dg = new DirectedMultigraph<>(
                    new ClassBasedEdgeFactory<Object, RelationshipEdge>(RelationshipEdge.class));
        
        DirectedGraph c1 = getC1();
        
        DirectedGraph c2 = getC2();
        //Graphs.addGraph(c1, c2);
        for(Object vertex1 : c1.vertexSet()){
            boolean found = false;
            Atom a1 = (Atom) vertex1;
//            if(a1 instanceof Relation)
//            {
//                System.out.print("ketemu");
//                continue;
//            }
            for(Object vertex2 : c2.vertexSet()){
                Atom a2 = (Atom) vertex2;
                if(a1.getLabel().equals(a2.getLabel())){
                    dg.addVertex(a1);
                    found = true;
                    break;
                }
            }
            if(found == false)
            {
                dg.addVertex(a1);
            }
        }
        
        for(Object vertex2 : c2.vertexSet()){
            boolean found = false;
            Atom a2 = (Atom) vertex2;
//            if(a2 instanceof Relation)
//            {
//                System.out.print("ketemu");
//                continue;
//            }
            for(Object vertex1 : c1.vertexSet()){
                Atom a1 = (Atom) vertex1;
                if(a1.getLabel().equals(a2.getLabel())){
                    found = true;
                    break;
                }
            }
            if(found == false)
            {
                dg.addVertex(a2);
            }
        }
        
        for(Object edge1 : c1.edgeSet()){
            boolean found = false;
            RelationshipEdge re1 = (RelationshipEdge) edge1;
            for(Object edge2 : c2.edgeSet()){
                RelationshipEdge re2 = (RelationshipEdge) edge2;
                Atom v1_1 = (Atom) re1.getV1();
                Atom v1_2 = (Atom) re1.getV2();
                Atom v2_1 = (Atom) re2.getV1();
                Atom v2_2 = (Atom) re2.getV2();
                if(v1_1.getLabel().equals(v2_1.getLabel()) && v1_2.getLabel().equals(v2_2.getLabel())){
                    dg.addVertex(v1_1);
                    dg.addVertex(v1_2);
                    dg.addEdge(v1_1, v1_2, new RelationshipEdge(v1_1, v1_2, re1.getLabel()));
                    found = true;
                    break;
                }
            }
            
            if(found == false)
            {
                dg.addVertex(re1.getV1());
                dg.addVertex(re1.getV2());
                dg.addEdge(re1.getV1(), re1.getV2(), new RelationshipEdge(re1.getV1(), re1.getV2(), re1.getLabel()));
            }
        }
        
        for(Object edge2 : c2.edgeSet()){
            boolean found = false;
            RelationshipEdge re2 = (RelationshipEdge) edge2;
            for(Object edge1 : c1.edgeSet()){
                RelationshipEdge re1 = (RelationshipEdge) edge1;
                Atom v1_1 = (Atom) re1.getV1();
                Atom v1_2 = (Atom) re1.getV2();
                Atom v2_1 = (Atom) re2.getV1();
                Atom v2_2 = (Atom) re2.getV2();
                if(v1_1.getLabel().equals(v2_1.getLabel()) && v1_2.getLabel().equals(v2_2.getLabel())){
                    found = true;
                    break;
                }
            }
            
            if(found == false)
            {
                dg.addVertex(re2.getV1());
                dg.addVertex(re2.getV2());
                dg.addEdge(re2.getV1(), re2.getV2(), new RelationshipEdge(re2.getV1(), re2.getV2(), re2.getLabel()));
            }
        }
        
        return dg;
    }
}
