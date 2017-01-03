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
import java.util.Collections;
import model.Atom;
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
    public static final double threshold = 0.5;
    public static final double classThreshold = 0.5;
    public static final double methodThreshold = 0.3;
    public static final double attributeThreshold = 0.2;
    
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
                    double wupScore = wup.calcRelatednessOfWords(w1, w2);
                    double pathScore = path.calcRelatednessOfWords(w1, w2);
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
        double distance = calculateWuPath(word1, word2);
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
    
    //bikin fungsi membandingkan kemiripan simantic greedy based match
    public double similarityMetric(ArrayList v0, ArrayList v1)
    {
        ArrayList<RelatedAtom> raList = new ArrayList<>();
        ArrayList<Atom> v00;
        ArrayList<Atom> v01;
        if(v0.size() < v1.size())
        {
            v00 = v0;
            v01 = v1;
        }else
        {
            v00 = v1;
            v01 = v0;
        }
        for(Atom a : v00)
        {
            for(Atom b : v01)
            {
                double score = a.getSemanticScore(b);
                RelatedAtom ra = new RelatedAtom(a, b);
                ra.setScore(score);
                raList.add(ra);
            }
        }
        
        Collections.sort(raList, (RelatedAtom o1, RelatedAtom o2) -> Double.compare(o2.getScore(), o1.getScore()));
        
        ArrayList<Atom> comparedx = new ArrayList<Atom>();
        ArrayList<Atom> comparedy = new ArrayList<Atom>();
        //todo : cari nilai rata2 score
        double total = 0;
        for(RelatedAtom a : raList)
        {
            if(!comparedx.contains(a.getV0()))
            {
                if(!comparedy.contains(a.getV1()))
                {
                    total = total + a.getScore();
                    comparedy.add(a.getV1());
                    comparedx.add(a.getV0());
                }
            }
        }

        double rata = 0;
        rata = total/v00.size();
        return rata;
    }
    
    public static void main(String[] args){
        ArrayList<Atom> al = new ArrayList<>();
        ArrayList<Atom> bl = new ArrayList<>();
        Atom a = new Atom("Human");
        Atom e = new Atom("man");
        Atom b = new Atom("People");
        Atom c = new Atom("Girl");
        Atom d = new Atom("Woman");
        al.add(c);
        al.add(e);
        bl.add(d);
        bl.add(a);
        bl.add(b);
        Semantic s = new Semantic();
        System.out.println(s.similarityMetric(al, bl));
    }
}
