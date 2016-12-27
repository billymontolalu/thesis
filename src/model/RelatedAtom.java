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
public class RelatedAtom {
    private Atom v0;
    private Atom v1;
    private double score;
    private boolean isSemantic = false;
    
    public RelatedAtom()
    {
    }
    
    public RelatedAtom(Atom v0, Atom v1)
    {
        this.v0 = v0;
        this.v1 = v1;
    }

    /**
     * @return the v0
     */
    public Atom getV0() {
        return v0;
    }

    /**
     * @param v0 the v0 to set
     */
    public void setV0(Atom v0) {
        this.v0 = v0;
    }

    /**
     * @return the v1
     */
    public Atom getV1() {
        return v1;
    }

    /**
     * @param v1 the v1 to set
     */
    public void setV1(Atom v1) {
        this.v1 = v1;
    }

    /**
     * @return the score
     */
    public double getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(double score) {
        this.score = score;
    }

    /**
     * @return the isSemantic
     */
    public boolean isIsSemantic() {
        return isSemantic;
    }

    /**
     * @param isSemantic the isSemantic to set
     */
    public void setIsSemantic(boolean isSemantic) {
        this.isSemantic = isSemantic;
    }
    
    
}
