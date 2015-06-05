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
public class Circled extends Boundsd {
    public Vec2d center;
    public double radius;
    
    private AABBd bounds;

    public Circled() {
        this(Vec2d.zeroVec(), 1.0);
    }

    public Circled(Vec2d center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    @Override
    public boolean inBounds(Vec2d p) {
        return p.diff(center).magnitude() <= radius;
    }

    @Override
    public Ray2d[] getSides() {
        return new Ray2d[]{};
    }

    @Override
    public Vec2d[] getVerticies() {
        return new Vec2d[]{};
    }
    
    @Override
    public AABBd getAABB(){
        return new AABBd(center.y + radius, center.y - radius, center.x + radius, center.y + radius);
    }
    
}
