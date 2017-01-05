/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphmodel;

import java.util.ArrayList;
import java.util.List;
import model.Atom;
import model.RelatedAtom;
import model.Relation;
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
    
    public boolean isConflict()
    {
        return countInsertInsert() > 0 || countInsertDelete() > 0 || countDeleteInsert() > 0 || countDeleteDelete() > 0;
    }
    
    public List<RelatedAtom> getSemanticNode()
    {
        //jika ada relasi yang sama maka, itu akan jadi kunci lokasi, 
        //sedangkan relasi yang tidak sama akan dipindahkan
        GraphCompare gc1 = new GraphCompare(v0, v1);
        GraphCompare gc2 = new GraphCompare(v0, v2);
        DirectedGraph newNode1 = gc1.getNewNode();
        DirectedGraph newNode2 = gc2.getNewNode();
        List<RelatedAtom> related = new ArrayList();
        for(Object node1 : newNode1.vertexSet())
        {
            Atom c1 = (Atom) node1;
            for(Object node2 : newNode2.vertexSet())
            {
                Atom c2 = (Atom) node2;
                if(c1.equalSemantic(c2))
                {
                    RelatedAtom ra = new RelatedAtom(c1, c2);
                    related.add(ra);
                }
            }
        }
        
        return related;
    }
    
    public DirectedGraph getInsertInsertSemantic()
    {
        DirectedGraph dg = new DirectedMultigraph<>(
                    new ClassBasedEdgeFactory<Object, RelationshipEdge>(RelationshipEdge.class));
        GraphCompare gc1 = new GraphCompare(v0, v1);
        GraphCompare gc2 = new GraphCompare(v0, v2);
        DirectedGraph newNode1 = gc1.getNewNode();
        DirectedGraph newNode2 = gc2.getNewNode();
        
        for(Object node1 : newNode1.vertexSet())
        {
            Atom n1 = (Atom) node1;
            for(Object node2 : newNode2.vertexSet())
            {
                Atom n2 = (Atom) node2;
                if(n1.equals(n2))
                {
                    if(!dg.containsVertex(n1))
                        dg.addVertex(n1);
                    if(!dg.containsVertex(n2))
                        dg.addVertex(n2);
                }
                else if(n1.equalSemantic(n2))
                {
                    if(!dg.containsVertex(n1))
                        dg.addVertex(n1);
                    if(!dg.containsVertex(n2))
                        dg.addVertex(n2);
                    if(!dg.containsEdge(n1, n2))
                        dg.addEdge(n1, n2, new RelationshipEdge(n1, n2, "--|>"));
                }
            }
        }
        
        return dg;
    }
    
    public DirectedGraph getInsertInsert()
    {
        DirectedGraph dg = new DirectedMultigraph<>(
                    new ClassBasedEdgeFactory<Object, RelationshipEdge>(RelationshipEdge.class));
        GraphCompare gc1 = new GraphCompare(v0, v1);
        GraphCompare gc2 = new GraphCompare(v0, v2);
        DirectedGraph newNode1 = gc1.getNewNode();
        DirectedGraph newNode2 = gc2.getNewNode();
        for(Object node1 : newNode1.vertexSet())
        {
            Atom n1 = (Atom) node1;
            for(Object node2 : newNode2.vertexSet())
            {
                Atom n2 = (Atom) node2;
                if(n1.equals(n2))
                {
                    dg.addVertex(n1);
                }
            }
        }
        
        return dg;
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
    
    public int countDeleteDelete()
    {
        DirectedGraph dg = getDeleteDelete();
        int count = 0;
        for(Object x : dg.vertexSet())
        {
            count ++;
        }
        return count;
    }
    
    public int countInsertInsert()
    {
        DirectedGraph dg = getInsertInsert();
        int count = 0;
        for(Object x : dg.vertexSet())
        {
            count ++;
        }
        return count;
    }
    
    public int countInsertSemantic()
    {
        DirectedGraph dg = getInsertInsertSemantic();
        int count = 0;
        for(Object x : dg.vertexSet())
        {
            count ++;
        }
        return count;
    }
    
    public int countInsertDelete()
    {
        DirectedGraph dg = getInsertDelete();
        int count = 0;
        for(Object x : dg.vertexSet())
        {
            count ++;
        }
        return count;
    }
    
    public int countDeleteInsert()
    {
        DirectedGraph dg = getDeleteInsert();
        int count = 0;
        for(Object x : dg.vertexSet())
        {
            count ++;
        }
        return count;
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
        
        return compact(dg);
    }
    
    public DirectedGraph mergeSemantic()
    {
        DirectedGraph dg = new DirectedMultigraph<>(
            new ClassBasedEdgeFactory<Object, RelationshipEdge>(RelationshipEdge.class));
        List<Object> visited = new ArrayList<Object>();
        DirectedGraph x = getX();
        setSameNeighbor(x, visited);
        List<RelatedAtom> ra = getSemanticNode();
        
        Object startTravers = null;
        for (RelatedAtom ra1 : ra)
        {
            startTravers = ra1.getV0();
            treeVisit(x, startTravers, visited, dg);
            for(Object mv : dg.vertexSet())
            {
                x.removeVertex(mv);
            }
            Graphs.addGraph(x, dg);
            Atom moveFrom = null, moveTo = null;
            for(Object i : x.vertexSet())
            {
                if(i.equals(startTravers))
                {
                    moveFrom = (Atom) i;
                }
                else if(i.equals(ra1.getV1()))
                {
                    moveTo = (Atom) i;
                }
            }
            
            if(moveFrom == null || moveTo == null)
            {
                continue;
            }
            
            moveTo.setLabel(moveFrom.getLabel() + "/" + moveTo.getLabel());
            for(Object edge : x.edgesOf(moveFrom))
            {
                RelationshipEdge rel = (RelationshipEdge) edge;
                if(!moveFrom.equals(rel.getV1()))
                {
                    x.addEdge(rel.getV1(), moveTo, new RelationshipEdge(rel.getV1(), moveTo, rel.getLabel()));
                }
                else if(!moveFrom.equals(rel.getV2()))
                {
                    x.addEdge(moveTo, rel.getV2(), new RelationshipEdge(moveTo, rel.getV2(), rel.getLabel()));
                }
            }
            x.removeVertex(moveFrom);
            dg.removeVertex(moveFrom);
        }

        return x;
    }
    
    //todo: bikin fungsi untuk mencari node tetangga yang sama
    void setSameNeighbor(DirectedGraph x, List<Object> visitedList)
    {
        List<RelatedAtom> ra = getSemanticNode();
        for (RelatedAtom ra1 : ra)
        {
            for(Object object0 : x.vertexSet())
            {
                if(object0.equals(ra1.getV0()))
                {
                    for(Object edge : x.edgesOf(object0))
                    {
                        RelationshipEdge re = (RelationshipEdge) edge;
                        Object neighbor = null;
                        if(!object0.equals(re.getV1()))
                        {
                            neighbor = re.getV1();
                        }else
                        {
                            neighbor = re.getV2();
                        }
                        
                        for(Object object1 : x.vertexSet())
                        {
                            if(object1.equals(ra1.getV1()))
                            {
                                for(Object edge1 : x.edgesOf(object1))
                                {
                                    RelationshipEdge re1 = (RelationshipEdge) edge1;
                                    Object neighbor1 = null;
                                    if(!object1.equals(re1.getV1()))
                                    {
                                        neighbor1 = re1.getV1();
                                    }else
                                    {
                                        neighbor1 = re1.getV2();
                                    }
                                    
                                    if(neighbor.equals(neighbor1))
                                    {
                                        visitedList.add(neighbor);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    //todo: bikin fungsi untuk mecari node itu pernah dikunjungi apa tidak
    boolean isVisited(Object current, List<Object> visitedList)
    {
        for (Object visited1 : visitedList) {
            Atom v = (Atom) visited1;
            if(v.equals(current))
            {
                return true;
            }
        }
        return false;
    }
    
    public void treeVisit(DirectedGraph x, Object current, List<Object> visited, DirectedGraph dg)
    {
        for(Object object : x.vertexSet())
        {
            if(object.equals(current))
            {
                //cek apakah pernah dikunjungi
                boolean isVisited = isVisited(current, visited);
                if(!isVisited){
                    visited.add(object);
                    dg.addVertex(object);
                    for(Object edge : x.edgesOf(object))
                    {
                        RelationshipEdge re = (RelationshipEdge) edge;
                        isVisited = isVisited(re.getV1(), visited);
                        if(!isVisited)
                        {
                            dg.addVertex(re.getV1());
                            dg.addEdge(re.getV1(), object, new RelationshipEdge(re.getV1(), object, re.getLabel()));
                            treeVisit(x, re.getV1(), visited, dg);
                        }
                        
                        isVisited = isVisited(re.getV2(), visited);
                        if(!isVisited)
                        {
                            dg.addVertex(re.getV2());
                            dg.addEdge(object, re.getV2(), new RelationshipEdge(object, re.getV2(), re.getLabel()));
                            treeVisit(x, re.getV2(), visited, dg);
                        }
                        
                    }
                }
            }
        }
    }
    
    DirectedGraph compact(DirectedGraph dg)
    {
        DirectedGraph normalisasi = new DirectedMultigraph<>(
            new ClassBasedEdgeFactory<Object, RelationshipEdge>(RelationshipEdge.class));
        
        for(Object v : dg.vertexSet())
        {
            if(v instanceof Relation)
            {
                Relation r = (Relation) v;
                Object src = searchOnVertex(normalisasi, r.getSource());
                if(src == null)
                {
                    src = r.getSource();
                    normalisasi.addVertex(src);
                }
                
                Object dst = searchOnVertex(normalisasi, r.getDestination());
                if(dst == null)
                {
                    dst = r.getDestination();
                    normalisasi.addVertex(dst);
                }
                
                normalisasi.addEdge(src, dst, new RelationshipEdge(src, dst, r.getType()));
            }
            else
            {
                if(searchOnVertex(normalisasi, v) == null)
                {
                    normalisasi.addVertex(v);
                }
            }
        }
        
        for(Object e: dg.edgeSet())
        {
            RelationshipEdge re = (RelationshipEdge) e;
            if(searchOnVertex(normalisasi, re.getV1()) == null)
            {
                normalisasi.addVertex(re.getV1());
            }
            
            if(searchOnVertex(normalisasi, re.getV2()) == null)
            {
                normalisasi.addVertex(re.getV2());
            }
            normalisasi.addEdge(re.getV1(), re.getV2(), new RelationshipEdge(re.getV1(), re.getV2(), re.getLabel()));
        }
        return normalisasi;
    }
    
    Object searchOnVertex(DirectedGraph dg, Object keyword)
    {
        for(Object v:dg.vertexSet())
        {
            Atom a = (Atom) v;
            if(a.equals(keyword))
            {
                return v;
            }
        }
        return null;
    }
}
