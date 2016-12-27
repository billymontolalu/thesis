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
    private ArrayList<Attribute> attribute = new ArrayList<Attribute>();
    private ArrayList<Method> method = new ArrayList<Method>();
    private ArrayList<Relation> relation = new ArrayList<Relation>();
    
    public Class(){
        
    }
    
    public void addRelation(Relation relation)
    {
        this.relation.add(relation);
    }
    
    public ArrayList<Method> getMethod()
    {
        return this.method;
    }
    
    public ArrayList<Relation> getRelation()
    {
        return this.relation;
    }
    
    public void addAttribute(Attribute attribute)
    {
        this.attribute.add(attribute);
    }
    
    public ArrayList<Attribute> getAttribute()
    {
        return this.attribute;
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
    
    public ArrayList<RelatedAtom> getRelatedMethod(Class v1)
    {
        ArrayList<RelatedAtom> related = new ArrayList<RelatedAtom>();
        ArrayList<Method> m1 = v1.getMethod();
        
        ArrayList<Method> mloop1;
        ArrayList<Method> mloop2;
        
        if(method.size() > m1.size())
        {
        }
        
        for (Method method0 : method) {
            for (Method method1 : m1) {
                if(method0.equals(method1))
                {
                    RelatedAtom ra = new RelatedAtom();
                    ra.setV0(method0);
                    ra.setV1(method1);
                    ra.setIsSemantic(false);
                    ra.setScore(1);
                }else
                {
                    RelatedAtom ra = new RelatedAtom();
                    ra.setV0(method0);
                    ra.setV1(method1);
                    ra.setIsSemantic(true);
                    double score = method0.getSemanticScore(method1);
                    ra.setScore(score);
                }
            }
        }
        return related;
    }
    
    public ArrayList<RelatedAtom> getRelatedAttribute(Class v1)
    {
        ArrayList<RelatedAtom> related = new ArrayList<RelatedAtom>();
        ArrayList<Attribute> a1 = v1.getAttribute();
        for (Attribute attribute0 : attribute) {
            for (Attribute attribute1 : a1) {
                if(attribute0.equals(attribute1))
                {
                    RelatedAtom ra = new RelatedAtom();
                    ra.setV0(attribute0);
                    ra.setV1(attribute1);
                    ra.setIsSemantic(false);
                    ra.setScore(1);
                }else
                {
                    RelatedAtom ra = new RelatedAtom();
                    ra.setV0(attribute0);
                    ra.setV1(attribute1);
                    ra.setIsSemantic(true);
                    double score = attribute0.getSemanticScore(attribute1);
                    ra.setScore(score);
                }
            }
        }
        return related;
    }
    
    public void getAttributeScore()
    {
        
    }
}
