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
public class Relation extends Atom{
    private Atom source;
    private Atom destination;
    private String type;
    
    public Relation()
    {
    }
    
    @Override
    public String getLabel() {
        return source.getLabel() + "|" + type + "|" + destination.getLabel();
    }
    
    public Relation(String name)
    {
        this.setLabel(name);
    }
    
    public void setSource(Atom source)
    {
        this.source = source;
    }
    
    public void setDestination(Atom destination)
    {
        this.destination = destination;
    }
    
    public Atom getSource()
    {
        return this.source;
    }
    
    public Atom getDestination()
    {
        return this.destination;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

}
