/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dtw.math;

/**
 *
 * @author 068616374
 */
public abstract class Boundsd {
    public abstract boolean inBounds(Vec2d p);
    
    public Vec2d calcSA(Boundsd b){
        Ray2d[] o1s = getSides();
        Ray2d[] o2s = b.getSides();
        Vec2d[] o1v = getPoints();
        Vec2d[] o2v = b.getPoints();
        
        
        
        return new Vec2d();
    }
    
    public abstract Ray2d[] getSides();
    public abstract Vec2d[] getPoints();
    public abstract double[] getRadi();
    
    public abstract AABBd getAABB();
    
}
