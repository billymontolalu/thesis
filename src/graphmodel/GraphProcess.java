/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphmodel;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;

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
        DirectedGraph c1 = getC1();
        Graphs.addGraph(c1, getC2());
        return c1;
    }
}
