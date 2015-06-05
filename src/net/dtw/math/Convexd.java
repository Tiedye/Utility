/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dtw.math;

/**
 *
 * @author Tiedye <tiedye1@hotmail.com>
 */
public class Convexd extends Boundsd {
    
    private final Vec2d center;
    private double rotation;
    private boolean rotRecent;
    private final Vec2d[] verticies;
    private final Vec2d[] cVerticies;
    private final Ray2d[] sides;
    
    public Convexd(Vec2d center, Vec2d[] verticies){
        this.center = center;
        this.verticies = verticies;
        cVerticies = new Vec2d[verticies.length];
        sides = new Ray2d[verticies.length];
        recalculateSides();
    }

    private void recalculateSides(){
        if (rotRecent) return;
        for (int i = 0; i < verticies.length; i++) {
            cVerticies[i] = verticies[i].rotate(rotation).sum(center);
        }
        for (int i = 0; i < cVerticies.length; i++) {
            sides[i] = Ray2d.newRay(cVerticies[i], cVerticies[(i+1)%cVerticies.length]);
        }
        rotRecent = true;
    }
    
    public double getRotation(){
        return rotation;
    }
    
    public void setRotation(double r){
        rotation = r;
        rotRecent = false;
    }
    
    @Override
    public boolean inBounds(Vec2d p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Ray2d[] getSides() {
        recalculateSides();
        return sides;
    }

    @Override
    public Vec2d[] getVerticies() {
        recalculateSides();
        return cVerticies;
    }

    @Override
    public AABBd getAABB() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
