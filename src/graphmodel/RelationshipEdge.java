/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphmodel;

import org.jgrapht.graph.DefaultEdge;

/**
 *
 * @author Momo
 * @param <V>
 */
public class RelationshipEdge <V> extends DefaultEdge{
    private V v1;
    private V v2;
    private String label;

    public RelationshipEdge(V v1, V v2, String label) {
        this.v1 = v1;
        this.v2 = v2;
        this.label = label;
    }

    public RelationshipEdge() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void setV1(V v1){
        this.v1 = v1;
    }

    public V getV1() {
        return v1;
    }
    
    public void setV2(V v2){
        this.v2 = v2;
    }

    public V getV2() {
        return v2;
    }

    @Override
    public String toString() {
        return getLabel();
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }
}
