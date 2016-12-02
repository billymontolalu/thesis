/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphmodel;

import edu.cmu.lti.lexical_db.ILexicalDatabase;
import edu.cmu.lti.lexical_db.NictWordNet;
import edu.cmu.lti.ws4j.impl.WuPalmer;
import edu.cmu.lti.ws4j.util.WS4JConfiguration;
import model.SyntaxString;

/**
 *
 * @author Momo
 */
public class Semantic {
    private static final ILexicalDatabase db = new NictWordNet();
    private static final double threshold = 0.7;
    
    /*private String splitCamelCase(String word)
    {
        String wordSplit = "";
        for (String w : word.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])"))
        {
            wordSplit = wordSplit + w + " ";
        }
        return wordSplit.trim();
    }*/
    
    private String[] splitCamelCase(String word)
    {
        return word.trim().split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])");
    }
    
    public double compute(String word1, String word2) {
        String[] words1 = splitCamelCase(word1);
        String[] words2 = splitCamelCase(word2);
        double max = 0.0;
        WS4JConfiguration.getInstance().setMFS(true);
        for(String w1 : words1)
        {
            for(String w2 : words2)
            {
                if(!SyntaxString.isSyntaxString(w1) && !SyntaxString.isSyntaxString(w2))
                {
                    double s = new WuPalmer(db).calcRelatednessOfWords(w1.trim(), w2.trim());
                    if(s > max)
                    {
                        max = s;
                    }
                }
                
            }
        }
        
        return max;
    }
    
    /*private double compute(String word1, String word2) {
        word1 = splitCamelcase(word1);
        word2 = splitCamelcase(word2);
        WS4JConfiguration.getInstance().setMFS(true);
        double s = new WuPalmer(db).calcRelatednessOfWords(word1, word2);
        return s;
    }*/
    
    public boolean isSimiliar(String word1, String word2)
    {
        double distance = compute(word1, word2);
        return distance >= threshold;
    }
    
    public void compareClass(Class v0, Class v1)
    {
        
    }
    
    public void compareMethod()
    {
        
    }
    
    public void compareAttribute()
    {
        
    }
}
