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
public abstract class Boundsd {
    public abstract boolean inBounds(Vec2d p);
    
    public Vec2d[] calcSA(Boundsd o){
        Ray2d[] o1s = getSides();
        Ray2d[] o2s = o.getSides();
        Vec2d[] o1v = getPoints();
        Vec2d[] o2v = o.getPoints();
        double[] o1r = getRadi();
        double[] o2r = o.getRadi();
        
        ArrayList<Vec2d> intersectingAxis = new ArrayList<>();
        
        VertexLoop:
        for (int v = 0; v < o2v.length; v++) {
            Vec2d cv = o2v[v]; 
            Vec2d lIntersection = Vec2d.infVec();
            for (Ray2d cr : o1s) {
                Vec2d cIntersection;
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
            Vec2d cv = o1v[v]; 
            Vec2d lIntersection = Vec2d.infVec();
            for (Ray2d cr : o2s) {
                Vec2d cIntersection;
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
        Vec2d[] sa = new Vec2d[intersectingAxis.size()];
        return intersectingAxis.toArray(sa);
    }
    
    public abstract Ray2d[] getSides();
    public abstract Vec2d[] getPoints();
    public abstract double[] getRadi();
    
    public abstract AABBd getAABB();
    
}
