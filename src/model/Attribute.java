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
public class Attribute extends Atom {
    public Attribute()
    {
    }
    
    public Attribute(String attributeName)
    {
        this.setLabel(attributeName);
    }
}
