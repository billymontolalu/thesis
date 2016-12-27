/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphmodel;

import edu.cmu.lti.lexical_db.ILexicalDatabase;
import edu.cmu.lti.lexical_db.NictWordNet;
import edu.cmu.lti.ws4j.RelatednessCalculator;
import edu.cmu.lti.ws4j.impl.Path;
import edu.cmu.lti.ws4j.impl.WuPalmer;
import edu.cmu.lti.ws4j.util.WS4JConfiguration;
import java.util.ArrayList;
import model.Method;
import model.Class;
import model.RelatedAtom;
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
    
    public double calculateWuPath(String word1, String word2)
    {
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
                    RelatednessCalculator path = new Path(db);
                    RelatednessCalculator wup = new WuPalmer(db);
                    double wupScore = wup.calcRelatednessOfWords(word1, word2);
                    double pathScore = path.calcRelatednessOfWords(word1, word2);
                    double s = (wupScore + pathScore)/2;
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
    
    public void compareMethod(Class v0, Class v1)
    {
        ArrayList<RelatedAtom> related = new ArrayList<RelatedAtom>();
        ArrayList<Method> m0 = v0.getMethod();
        ArrayList<Method> m1 = v1.getMethod();
        if(m0.size() > m1.size())
        {
            for(int x=0; x<m0.size(); x++)
            {
                String sv0 = m0.get(x).getLabel();
                for(int y=0; y<m1.size(); y++)
                {
                    double score = 0.0;
                    String sv1 = m1.get(y).getLabel();
                    if(sv0.equals(sv1))
                    {
                        score = 1;
                        RelatedAtom ra = new RelatedAtom();
                        ra.setV0(v0);
                        ra.setV1(v0);
                    }
                    else
                    {
                        score = calculateWuPath(sv0, sv1);
                        if(score >= threshold)
                        {
                        }else
                        {
                            
                        }
                    }
                }
            }
        }
        
    }
    
    public void compareAttribute(Class v0, Class v1)
    {
     
        
    }
}
