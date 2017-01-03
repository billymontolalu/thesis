/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import graphmodel.Semantic;

/**
 *
 * @author Momo
 */
public class Atom {
    private String label;
    
    public Atom(){
        
    }
    
    public Atom(String label){
        this.label = label;
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
    
    public boolean equalSemantic(Object otherObject)
    {
        return false;
    }
    
    public double getSemanticScore(Object otherObject)
    {
        if(otherObject instanceof Atom)
        {
            Atom c = (Atom) otherObject;
            if(this.getLabel().equals(c.getLabel()))
            {
                return 1;
            }
            
            Semantic s = new Semantic();
            return s.calculateWuPath(this.getLabel(), c.getLabel());
        }
        return 0;
    }
}
