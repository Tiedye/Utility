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
public class SimpleConvexd extends Boundsd {
    
    private Ray2d[] sides;
    private Vec2d[] verticies;
    
    private AABBd aabb;

    public SimpleConvexd(Vec2d[] verticies) {
        this.verticies = verticies;
        sides = new Ray2d[verticies.length];
        double mX = Double.POSITIVE_INFINITY;
        double MX = Double.NEGATIVE_INFINITY;
        double mY = Double.POSITIVE_INFINITY;
        double MY = Double.NEGATIVE_INFINITY;
        for(int i = 0; i < verticies.length; i++){
            Vec2d v = verticies[i];
            mX = v.x < mX ? v.x : mX;
            MX = v.x > MX ? v.x : MX;
            mY = v.y < mY ? v.y : mY;
            MY = v.y > MY ? v.y : MY;
            sides[i] = Ray2d.newRay(v, verticies[(i+1)%verticies.length]);
        }
        aabb = new AABBd(MY, mY, MX, mX);
    }

    @Override
    public boolean inBounds(Vec2d p) {
        return calcSA(new Boundsd() {
            @Override
            public Ray2d[] getSides() {
                return new Ray2d[]{};
            }
            @Override
            public Vec2d[] getVerticies() {
                return new Vec2d[]{p};
            }
            @Override
            public AABBd getAABB() {return null;}
            @Override
            public boolean inBounds(Vec2d p) {return false;}
        }).magnitude() != 0.0;
    }

    @Override
    public Ray2d[] getSides() {
        return sides;
    }

    @Override
    public Vec2d[] getVerticies() {
        return verticies;
    }

    @Override
    public AABBd getAABB() {
        return aabb.copy();
    }
    
}
