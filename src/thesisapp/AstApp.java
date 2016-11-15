/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thesisapp;

import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.visitor.VoidVisitorAdapter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Momo
 */
public class AstApp {
    public static void parse(String str) {
        try {
            FileInputStream in = new FileInputStream("C:\\Users\\Momo\\Documents\\NetBeansProjects\\Thesis\\src\\thesis\\Thesis.java");
            CompilationUnit cu;
            cu = JavaParser.parse(in);
            //new MethodVisitor().visit(cu, null);
        } catch (FileNotFoundException | ParseException ex) {
            Logger.getLogger(GraphExample.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Simple visitor implementation for visiting MethodDeclaration nodes. 
     */
    private static class MethodVisitor extends VoidVisitorAdapter {

        @Override
        public void visit(MethodDeclaration n, Object arg) {
            // here you can access the attributes of the method.
            // this method will be called for all methods in this 
            // CompilationUnit, including inner class methods
            System.out.println(n.getName());
            super.visit(n, arg);
        }
    }
    
    public static void main(String args[]){
        AstApp.parse(null);
    }
}
