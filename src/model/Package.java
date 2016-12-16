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
public class Package extends Atom{
    public Package()
    {
    }
    
    public Package(String packageName)
    {
        this.setLabel(packageName);
    }
}
