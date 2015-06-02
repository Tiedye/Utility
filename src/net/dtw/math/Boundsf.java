/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dtw.math;

import java.util.ArrayList;

/**
 *
 * @author 068616374
 */
public abstract class Boundsf {
    public abstract boolean inBounds(Vec2f p);
    
    public Vec2f[] calcSA(Boundsf o){
        Ray2f[] o1s = getSides();
        Ray2f[] o2s = o.getSides();
        Vec2f[] o1v = getPoints();
        Vec2f[] o2v = o.getPoints();
        float[] o1r = getRadi();
        float[] o2r = o.getRadi();
        
        ArrayList<Vec2f> intersectingAxis = new ArrayList<>();
        
        VertexLoop:
        for (int v = 0; v < o2v.length; v++) {
            Vec2f cv = o2v[v]; 
            Vec2f lIntersection = Vec2f.infVec();
            for (Ray2f cr : o1s) {
                Vec2f cIntersection;
                cIntersection = cr.shortestPath(cv).retract(o2r[v]);
                if (cIntersection.magnitude() > 0) {
                    continue VertexLoop;
                }
                if (cIntersection.magnitude() < lIntersection.magnitude()) {
                    lIntersection = cIntersection;
                }
            }
            intersectingAxis.add(lIntersection);
        }
        VertexLoop:
        for (int v = 0; v < o1v.length; v++) {
            Vec2f cv = o1v[v]; 
            Vec2f lIntersection = Vec2f.infVec();
            for (Ray2f cr : o2s) {
                Vec2f cIntersection;
                cIntersection = cr.shortestPath(cv).retract(o1r[v]);
                if (cIntersection.magnitude() > 0) {
                    continue VertexLoop;
                }
                if (cIntersection.magnitude() < lIntersection.magnitude()) {
                    lIntersection = cIntersection;
                }
            }
            intersectingAxis.add(lIntersection);
        }
        Vec2f[] sa = new Vec2f[intersectingAxis.size()];
        return intersectingAxis.toArray(sa);
    }
    
    public abstract Ray2f[] getSides();
    public abstract Vec2f[] getPoints();
    public abstract float[] getRadi();
    
    public abstract AABBf getAABB();
    
}
