/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphmodel;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Momo
 */
public class PlantToData {
    private void readData(){
        InputStream in = getClass().getResourceAsStream("/parkiran/case0.puml");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf-8"));
            String line = null;
            
            try {
                while((line = br.readLine()) != null) {
                    Pattern class_pattern = Pattern.compile("class (.*?) \\{");
                    Matcher class_m = class_pattern.matcher(line);

                    if (class_m.find()) {
                        System.out.println(class_m.group(1));
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(PlantToData.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(PlantToData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void main(String args[]){
        PlantToData ptd = new PlantToData();
        ptd.readData();
    }
}
