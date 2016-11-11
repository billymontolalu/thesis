/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Momo
 */
public class Class extends Atom {    
    public Class(){
        
    }
    
    public Class(String className){
        this.setLabel(className);
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

}
