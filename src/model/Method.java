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
public class Method extends Atom{
    public Method(String methodName)
    {
        this.setLabel(methodName);
    }
    
    @Override
    public boolean equals(Object otherObject){
        if(otherObject instanceof Method)
        {
            Method c = (Method) otherObject;
            if(this.getLabel().equals(c.getLabel()))
            {
                return true;
            }
        }
        return false; 
    }
}