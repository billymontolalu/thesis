/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphmodel;

import edu.cmu.lti.jawjaw.pobj.POS;
import edu.cmu.lti.lexical_db.ILexicalDatabase;
import edu.cmu.lti.lexical_db.NictWordNet;
import edu.cmu.lti.ws4j.RelatednessCalculator;
import edu.cmu.lti.ws4j.impl.Path;
import edu.cmu.lti.ws4j.impl.WuPalmer;
import edu.cmu.lti.ws4j.util.WS4JConfiguration;
import edu.washington.cs.knowitall.morpha.MorphaStemmer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    public static final double threshold = 0.3;
    public static final double classThreshold = 0.6;
    public static final double methodThreshold = 0.2;
    public static final double attributeThreshold = 0.2;
    private OpenNLPSingleton nlp = null;
    private final Set<String> stopWords = new HashSet<String>();
    private RelatednessCalculator rc;
    private RelatednessCalculator path;

    public Semantic() {
        rc = new WuPalmer(db);
        path = new Path(db);
    }

    public String splitSyntax(String words) {
        String[] wordSplit = words.split(" ");
        String word = wordSplit[wordSplit.length - 1];

        return word;
    }

    public double computeSentenceSimilarity(String sentence1, String sentence2) {
        sentence1 = splitSyntax(sentence1);
        sentence2 = splitSyntax(sentence2);
        String[] split1 = splitCamelCase(sentence1);
        String[] split2 = splitCamelCase(sentence2);

        sentence1 = "";
        sentence2 = "";
        for (String ws1 : split1) {
            sentence1 = sentence1 + ws1 + " ";
        }

        for (String ws2 : split2) {
            sentence2 = sentence2 + ws2 + " ";
        }

        sentence1 = sentence1.trim();
        sentence2 = sentence2.trim();

        WS4JConfiguration conf = WS4JConfiguration.getInstance();
        conf.setMFS(true);

        if (nlp == null) {
            nlp = OpenNLPSingleton.INSTANCE;
        }
        String[] words1 = nlp.tokenize(sentence1);
        String[] words2 = nlp.tokenize(sentence2);

        String[] postag1 = nlp.postag(words1);
        String[] postag2 = nlp.postag(words2);

        List<Double> simScores = new ArrayList<Double>();
        for (int i = 0; i < words1.length; i++) {
            if (!stopWords.contains(words1[i])) {
                String pt1 = postag1[i];
                String w1 = MorphaStemmer.stemToken(words1[i].trim().toLowerCase(), pt1);
                POS p1 = mapPOS(pt1);

                Double maxSim = -1.0;
                if (p1 != null) {
                    for (int j = 0; j < words2.length; j++) {
                        if (!stopWords.contains(words2[j])) {
                            String pt2 = postag2[j];
                            String w2 = MorphaStemmer.stemToken(words2[j].toLowerCase(), pt2);
                            POS p2 = mapPOS(pt2);

                            if (p2 != null) {
                                double sim = 0;
                                double pathscore = 0;
                                double wupalmerscore = 0;
                                if ((w1.equals(w2) && w1.equals("set")) || (w1.equals(w2) && w1.equals("get"))) {
                                    sim = threshold - 0.1;
                                } else {
                                    wupalmerscore = rc.calcRelatednessOfWords(w1 + "#" + p1, w2 + "#" + p1);
                                    //pathscore = path.calcRelatednessOfWords(w1 + "#" + p1, w2 + "#" + p1);
                                    //sim = (wupalmerscore+pathscore)/2;
                                    sim = wupalmerscore;
                                }

                                //double pathscore = path.calcRelatednessOfWords(w1 + "#" + p1, w2 + "#" + p1);
                                //double sim = (wupalmerscore+pathscore)/2;
                                if (sim > 1) {
                                    sim = 1;
                                }
                                if (sim > maxSim) {
                                    maxSim = sim;
                                }
                                //System.out.println(w1 + ", " + w2 + ": " + sim);
                            }
                        }
                    }
                }

                if (maxSim > -1.0) {
                    //maxSim =  new Long(Math.round(maxSim)).doubleValue();
                    simScores.add(maxSim);
                }

            }
        }
        //System.out.println(simScores);
        return calculateMean(simScores);
    }

    public double computeSentenceSimilarityx(String sentence1, String sentence2) {
        sentence1 = splitSyntax(sentence1);
        sentence2 = splitSyntax(sentence2);
        String[] split1 = splitCamelCase(sentence1);
        String[] split2 = splitCamelCase(sentence2);

        sentence1 = "";
        sentence2 = "";
        for (String ws1 : split1) {
            sentence1 = sentence1 + ws1 + " ";
        }

        for (String ws2 : split2) {
            sentence2 = sentence2 + ws2 + " ";
        }

        sentence1 = sentence1.trim();
        sentence2 = sentence2.trim();

        WS4JConfiguration conf = WS4JConfiguration.getInstance();
        conf.setMFS(true);

        if (nlp == null) {
            nlp = OpenNLPSingleton.INSTANCE;
        }
        String[] words1 = nlp.tokenize(sentence1);
        String[] words2 = nlp.tokenize(sentence2);

        List<Double> simScores = new ArrayList<Double>();
        String[] words_compare1;
        String[] words_compare2;
        if (words1.length <= words2.length) {
            words_compare1 = words1;
            words_compare2 = words2;
        } else {
            words_compare2 = words1;
            words_compare1 = words2;
        }

        String[] postag1 = nlp.postag(words_compare1);
        String[] postag2 = nlp.postag(words_compare2);

        ArrayList<RelatedAtom> raList = new ArrayList<>();

        for (int i = 0; i < words_compare1.length; i++) {
            if (!stopWords.contains(words_compare1[i])) {
                String pt1 = postag1[i];
                String w1 = MorphaStemmer.stemToken(words_compare1[i].toLowerCase(), pt1);
                POS p1 = mapPOS(pt1);

                if (p1 != null) {
                    for (int j = 0; j < words_compare2.length; j++) {
                        if (!stopWords.contains(words_compare2[j])) {
                            String pt2 = postag2[j];
                            String w2 = MorphaStemmer.stemToken(words_compare2[j].toLowerCase(), pt2);
                            POS p2 = mapPOS(pt2);

                            if (p2 != null) {
                                double sim = rc.calcRelatednessOfWords(w1 + "#" + p1, w2 + "#" + p1);
                                if(sim > 1)
                                {
                                    sim = 1;
                                }
                                RelatedAtom ra = new RelatedAtom(new Atom(w1), new Atom(w2));
                                ra.setScore(sim);
                                raList.add(ra);
                            }
                        }
                    }
                }
            }
        }
        if (words_compare1.length == 1 || words_compare2.length == 1) {
            double total = 0;
            for (RelatedAtom ra : raList) {
                total = total + ra.getScore();
            }
            return total / raList.size();
        } else {
            Collections.sort(raList, (RelatedAtom o1, RelatedAtom o2) -> Double.compare(o2.getScore(), o1.getScore()));
            ArrayList<Atom> comparedx = new ArrayList<Atom>();
            ArrayList<Atom> comparedy = new ArrayList<Atom>();
            //todo : cari nilai rata2 score
            double total = 0;
            int count = 0;
            for (RelatedAtom a : raList) {
                if (!comparedx.contains(a.getV0())) {
                    if (!comparedy.contains(a.getV1())) {
                        total = total + a.getScore();
                        comparedy.add(a.getV1());
                        comparedx.add(a.getV0());
                        count++;
                    }
                }
            }
            
            double rata = 0;
            rata = total / count;
            return rata;
        }

        //System.out.println(simScores);
        //return calculateMean(simScores);
    }

    private static POS mapPOS(String pennTreePosTag) {
        if (pennTreePosTag.indexOf("NN") == 0) {
            return POS.n;
        }
        if (pennTreePosTag.indexOf("VB") == 0) {
            return POS.v;
        }
        if (pennTreePosTag.indexOf("JJ") == 0) {
            return POS.a;
        }
        if (pennTreePosTag.indexOf("RB") == 0) {
            return POS.r;
        }
        return null;
    }

    private double calculateMean(List<Double> scores) {
        Double sum = 0.0;
        if (!scores.isEmpty()) {
            for (Double mark : scores) {
                sum += mark;
            }
            return sum / scores.size();
        }
        return sum;
    }
    
    private void splitCamelCase()
    {
        
    }

    private String[] splitCamelCase(String word) {
        return word.trim().split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])");
    }

    public double compute(String word1, String word2) {
        String[] words1 = splitCamelCase(word1);
        String[] words2 = splitCamelCase(word2);
        double max = 0.0;
        WS4JConfiguration.getInstance().setMFS(true);
        for (String w1 : words1) {
            for (String w2 : words2) {
                if (!SyntaxString.isSyntaxString(w1) && !SyntaxString.isSyntaxString(w2)) {
                    double s = new WuPalmer(db).calcRelatednessOfWords(w1.trim(), w2.trim());
                    if (s > max) {
                        max = s;
                    }
                }

            }
        }

        return max;
    }

    public double calculateWuPath(String word1, String word2) {
        return computeSentenceSimilarity(word1, word2);
        /*word1 = splitSyntax(word1);
         word2 = splitSyntax(word2);
         String[] words1 = splitCamelCase(word1);
         String[] words2 = splitCamelCase(word2);
         double max = 0.0;

         WS4JConfiguration.getInstance().setMFS(true);
         for (String w1 : words1) {
         for (String w2 : words2) {
         if (!SyntaxString.isSyntaxString(w1) && !SyntaxString.isSyntaxString(w2)) {
         RelatednessCalculator path = new Path(db);
         RelatednessCalculator wup = new WuPalmer(db);
         double wupScore = wup.calcRelatednessOfWords(w1, w2);
         double pathScore = path.calcRelatednessOfWords(w1, w2);
         double s = (wupScore + pathScore) / 2;
         if (s > max) {
         max = s;
         }
         }

         }
         }
         return max;*/
    }

    public boolean isSimiliar(String word1, String word2) {
        double distance = calculateWuPath(word1, word2);
        return distance >= threshold;
    }

    public void stringMetric() {

    }

    //bikin fungsi membandingkan kemiripan simantic greedy based match
    public double similarityMetric(ArrayList v0, ArrayList v1) {
        ArrayList<RelatedAtom> raList = new ArrayList<>();
        ArrayList<Atom> v00;
        ArrayList<Atom> v01;
        if (v0.size() < v1.size()) {
            v00 = v0;
            v01 = v1;
        } else {
            v00 = v1;
            v01 = v0;
        }
        for (Atom a : v00) {
            for (Atom b : v01) {
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
        for (RelatedAtom a : raList) {
            if (!comparedx.contains(a.getV0())) {
                if (!comparedy.contains(a.getV1())) {
                    total = total + a.getScore();
                    comparedy.add(a.getV1());
                    comparedx.add(a.getV0());
                }
            }
        }

        double rata = 0;
        rata = total / v00.size();
        return rata;
    }

    public static void main(String[] args) {
        /*ArrayList<Atom> al = new ArrayList<>();
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
         System.out.println(s.similarityMetric(al, bl));*/
        Semantic s = new Semantic();
        double x = s.computeSentenceSimilarityx("gateID", "currentGateId");
        System.out.println(x);
    }
}
