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

    public SimpleConvexd(Vec2d[] verticies) {
        this.verticies = verticies;
        sides = new Ray2d[verticies.length];
        for(int i = 0; i < verticies.length; i++){
            sides[i] = new Ray2d(verticies[i], verticies[(i+1)%verticies.length]);
        }
    }

    @Override
    public boolean inBounds(Vec2d p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
