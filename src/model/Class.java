/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import graphmodel.Semantic;
import java.util.ArrayList;

/**
 *
 * @author Momo
 */
public class Class extends Atom {    
    private Package parent;
    private ArrayList<Attribute> attribute = new ArrayList<>();
    private ArrayList<Method> method = new ArrayList<>();
    
    public Class(){
        
    }
    
    public void addAttribute(Attribute attribute)
    {
        this.attribute.add(attribute);
    }
    
    public void addMethod(Method method)
    {
        this.method.add(method);
    }
    
    public Class(String className){
        this.setLabel(className);
    }
    
    public void setParent(Package parent)
    {
        this.parent = parent;
    }
    
    public Package getParent()
    {
        return this.parent;
    }
    
    @Override
    public boolean equals(Object otherObject){
        if(otherObject instanceof Class)
        {
            Class c = (Class) otherObject;
            if(this.getLabel().equals(c.getLabel()))
            {
                return true;
            }
        }
        return false; 
    }
    
    @Override
    public boolean equalSemantic(Object otherObject)
    {
        if(otherObject instanceof Class)
        {
            Class c = (Class) otherObject;
            Semantic s = new Semantic();
            if(this.getLabel().equals(c.getLabel()))
            {
                return false;
            }
            
            if(s.isSimiliar(this.getLabel(), c.getLabel()))
            {
                return true;
            }
        }
        return false; 
    }
}
