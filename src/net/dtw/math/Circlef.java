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
public class Circlef extends Boundsf {
    private Vec2f center;
    private float radius;
    
    private AABBd bounds;

    public Circlef() {
        this(new Vec2f(), 1.0f);
    }

    public Circlef(Vec2f center, float radius) {
        this.center = center;
        this.radius = radius;
    }

    @Override
    public boolean inBounds(Vec2f p) {
        return p.diff(center).magnitude() <= radius;
    }

    @Override
    public Ray2f[] getSides() {
        return new Ray2f[]{};
    }

    @Override
    public Vec2f[] getPoints() {
        return new Vec2f[]{center};
    }

    @Override
    public float[] getRadi() {
        return new float[]{radius};
    }
    
    @Override
    public AABBf getAABB(){
        return new AABBf(center.y + radius, center.y - radius, center.x + radius, center.y + radius);
    }
    
}
