/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import graphmodel.EdgeVis;
import graphmodel.RelationshipEdge;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.ClassBasedEdgeFactory;
import org.jgrapht.graph.DirectedMultigraph;

/**
 *
 * @author Momo
 */
public class Dataset {
    List<Class> classList = new ArrayList();
    private final DirectedGraph<Object, RelationshipEdge> graph;
    
    public Dataset()
    {
        graph = new DirectedMultigraph<>(
                    new ClassBasedEdgeFactory<Object, RelationshipEdge>(RelationshipEdge.class));
    }
    
    public DirectedGraph getGraph()
    {
        return graph;
    }
    
    void addClass(String newClass)
    {
        Class kelas = new Class(newClass);
        classList.add(kelas);
        graph.addVertex(kelas);
    }
    
    void addRelation(String label, String srcName, String dstName)
    {
        Class src = null, dst = null;
        for (Class classfind : classList)
        {
            if(classfind.getLabel().equals(srcName))
            {
                src = classfind;
            }else if(classfind.getLabel().equals(dstName))
            {
                dst = classfind;
            }
        }
        graph.addEdge(src, dst, new RelationshipEdge(src, dst, label));
    }
    
    void addMethod(String className, String methodName)
    {
        Class classMethod = find(className);
        Method method = new Method(methodName, classMethod);
        graph.addVertex(method);
        graph.addEdge(classMethod, method, new RelationshipEdge(classMethod, method, EdgeVis.METHOD));
    }
    
    void addAttribut(String className, String attributeName)
    {
        Class classAttribute = find(className);
        Attribute attribute = new Attribute(attributeName, classAttribute);
        graph.addVertex(attribute);
        graph.addEdge(classAttribute, attribute, new RelationshipEdge(classAttribute, attribute, EdgeVis.ATTRIBUTE));
    }
    
    Class find(String className)
    {
        for (Class find : classList) {
            if(find.getLabel().equals(className))
            {
                return find;
            }
        }
        return null;
    }
    
    public void readFile(String dataset, String fileName)
    {
        BufferedReader br = null;
        try {
            String sCurrentLine;
            File f = new File(getClass().getResource("/" + dataset + "/" + fileName).getFile());
            br = new BufferedReader(new FileReader(f));
            while ((sCurrentLine = br.readLine()) != null) {
                sCurrentLine = sCurrentLine.trim();
                switch (sCurrentLine) {
                    case "@startuml":
                        break;
                    case "@enduml":
                        break;
                    case "":
                        break;
                    default:
                        String split[] = sCurrentLine.split(":");
                        if(split.length >= 2)
                        {
                            String class_name = split[0].trim();
                            Class find = find(class_name);
                            if(find == null)
                            {
                                addClass(class_name);
                            }
                            
                            String properties[] = split[1].trim().split(" ");
                            if(properties.length > 0)
                            {
                                String properti = properties[properties.length - 1];
                                if(properti.contains("()"))
                                {
                                    String method = SyntaxString.removeSyntax(split[1].trim()).trim();
                                    addMethod(class_name, method);
                                }else
                                {
                                    String atribut = SyntaxString.removeSyntax(split[1].trim()).trim();
                                    addAttribut(class_name, atribut);
                                }
                            }
                        }
                        else
                        {
                            String split_space[] = sCurrentLine.split(" ");
                            String class_name = split_space[0].trim();
                            Class find = find(class_name);
                            if(find == null)
                            {
                                addClass(class_name);
                            }
                            String class_name2 = split_space[2].trim();
                            
                            Class find2 = find(class_name2);
                            if(find2 == null)
                            {
                                addClass(class_name2);
                            }
                            addRelation(split_space[1].trim(), class_name, class_name2);
                        }
                        //System.out.println(sCurrentLine);
                        break;
                }
            }
            //GraphVis.print(graph);
        } catch (IOException e) {
            System.out.println(e.toString());
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        }
    }
}
