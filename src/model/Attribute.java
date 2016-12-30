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
public class Attribute extends Atom {
    private Class parent;
    
    public Attribute()
    {
    }
    
    public Attribute(String attributeName, Class parent)
    {
        this.setLabel(attributeName);
        this.setParent(parent);
    }
    
    public void setParent(Class parent)
    {
        this.parent = parent;
    }
    
    public Class getParent()
    {
        return this.parent;
    }
    
    @Override
    public boolean equals(Object otherObject){
        if(otherObject instanceof Attribute)
        {
            Attribute c = (Attribute) otherObject;
            if(this.getLabel().equals(c.getLabel()) && c.getParent().equals(this.parent))
            {
                return true;
            }
        }
        return false; 
    }
    
    @Override
    public boolean equalSemantic(Object otherObject){
        if(otherObject instanceof Attribute)
        {
            Attribute c = (Attribute) otherObject;
            if(this.getLabel().equals(c.getLabel()))
            {
                return false;
            }
            Semantic s = new Semantic();
            if(s.isSimiliar(this.getLabel(), c.getLabel()) && s.isSimiliar(this.getParent().getLabel(), c.getParent().getLabel()))
            {
                return true;
            }
        }
        return false; 
    }
    
    @Override
    public double getSemanticScore(Object otherObject){
        if(otherObject instanceof Attribute)
        {
            Attribute c = (Attribute) otherObject;
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
