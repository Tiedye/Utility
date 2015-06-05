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
    
    /**
     * Calculates the separating axis between two bounding convex bounding areas.
     * @param o Other bounding area.
     * @return 
     */
    // Change return type to Vec2d
    public Vec2d calcSA(Boundsd o){

        if (this instanceof Circled) {
            Circled c1 = (Circled)this;
            if (o instanceof Circled) {
                Circled c2 = (Circled)o;
                Vec2d d = c1.center.diff(c2.center);
                d = d.retract(c1.radius + c2.radius);
                if (d.magnitude() < 0) return d;
                else return new Vec2d();
            } else {
                Ray2d[] o2s = o.getSides();
                Vec2d[] o2v = o.getVerticies();
                // fisrt check ditnce from center to each side ray
                
                Vec2d lIntersection = Vec2d.nInfVec();

                for (Ray2d cR : o2s) {
                    Vec2d intersection = cR.shortestPath(c1.center);
                    if (intersection.magnitude() > lIntersection.magnitude()) {
                        lIntersection = intersection;
                    }
                }
                
                // if the center is in out of the bounds check for distances to verticies
                // find shorest didsnce and keep track of the two sides connected to that points
                // check if both angles obtuse using dot product
                if (lIntersection.magnitude() > 0) {
                    Vec2d minLength = Vec2d.infVec();
                    int index = 0;
                    for (int i = 0; i < o2v.length; i++) {
                        Vec2d d = c1.center.diff(o2v[i]);
                        if (d.magnitude() < minLength.magnitude()) {
                            minLength = d;
                            index = i;
                        }
                    }
                    Vec2d rs = o2s[index].getDirection();
                    Vec2d ls = o2s[index - 1 < 0 ? o2v.length - 1 : index - 1].getDirection().scale(-1.0);
                    if (minLength.dot(rs) < 0 && minLength.dot(rs) < 0) {
                        lIntersection = minLength;
                    }
                }
                lIntersection = lIntersection.retract(c1.radius);
                if (lIntersection.magnitude() < 0) {
                    return lIntersection;
                }else{
                    return new Vec2d();
                }
            }
        } else {
            Ray2d[] o1s = getSides();
            Vec2d[] o1v = getVerticies();
            if (o instanceof Circled) {
                Circled c2 = (Circled)o;
                
                // fisrt check ditnce from center to each side ray
                
                Vec2d lIntersection = Vec2d.nInfVec();

                for (Ray2d cR : o1s) {
                    Vec2d intersection = cR.shortestPath(c2.center);
                    if (intersection.magnitude() > lIntersection.magnitude()) {
                        lIntersection = intersection;
                    }
                }
                
                // if the center is in out of the bounds check for distances to verticies
                // find shorest didsnce and keep track of the two sides connected to that points
                // check if both angles obtuse using dot product
                if (lIntersection.magnitude() > 0) {
                    Vec2d minLength = Vec2d.infVec();
                    int index = 0;
                    for (int i = 0; i < o1v.length; i++) {
                        Vec2d d = c2.center.diff(o1v[i]);
                        if (d.magnitude() < minLength.magnitude()) {
                            minLength = d;
                            index = i;
                        }
                    }
                    Vec2d rs = o1s[index].getDirection();
                    Vec2d ls = o1s[index - 1 < 0 ? o1v.length - 1 : index - 1].getDirection().scale(-1.0);
                    if (minLength.dot(rs) < 0 && minLength.dot(rs) < 0) {
                        lIntersection = minLength;
                    }
                }
                lIntersection = lIntersection.retract(c2.radius);
                if (lIntersection.magnitude() < 0) {
                    return lIntersection;
                }else{
                    return new Vec2d();
                }
            } else {
                Ray2d[] o2s = o.getSides();
                Vec2d[] o2v = o.getVerticies();
                Vec2d lIntersection = Vec2d.nInfVec();

                for (Ray2d cR : o1s) {
                    Vec2d mIntersection = Vec2d.infVec();
                    for (Vec2d cV : o2v) {
                        Vec2d cIntersection;
                        cIntersection = cR.shortestPath(cV);
                        if (cIntersection.magnitude() > 0) {
                            continue;
                        }
                        if (cIntersection.magnitude() < mIntersection.magnitude()) {
                            mIntersection = cIntersection;
                        }
                    }
                    if (mIntersection.magnitude() > 0) {
                        return new Vec2d();
                    }
                    if (mIntersection.magnitude() > lIntersection.magnitude()) {
                        lIntersection = mIntersection;
                    }
                }
                for (Ray2d cR : o2s) {
                    Vec2d mIntersection = Vec2d.infVec();
                    for (Vec2d cV : o1v) {
                        Vec2d cIntersection;
                        cIntersection = cR.shortestPath(cV);
                        if (cIntersection.magnitude() < mIntersection.magnitude()) {
                            mIntersection = cIntersection;
                        }
                    }
                    if (mIntersection.magnitude() > 0) {
                        return new Vec2d();
                    }
                    if (mIntersection.magnitude() > lIntersection.magnitude()) {
                        lIntersection = mIntersection;
                    }
                }
                return lIntersection;
            }
        }
    }
    
    /*
    
    0 index vetex
        | 0 index side
        ↓ ↓
        *---*
        |   |
        *---*
    
    */
    
    
    public abstract Ray2d[] getSides();
    public abstract Vec2d[] getVerticies();
    
    public abstract AABBd getAABB();
    
}
