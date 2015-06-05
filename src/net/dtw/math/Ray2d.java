/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dtw.math;

import java.util.Objects;

/**
 *
 * @author 068616374
 */
public class Ray2d {
    public Vec2d a;
    public Vec2d b;
    
    public static Ray2d zeroRay(){
        return newRay(Vec2d.zeroVec(), Vec2d.zeroVec());
    }
    public static Ray2d newRay(Vec2d a, Vec2d b){
        return new Ray2d(a, b);
    }

    private Ray2d(Vec2d a, Vec2d b) {
        this.a = a;
        this.b = b;
    }
    
    public double distance(Vec2d p){
        Vec2d ab = b.diff(a);
        return p.diff(a).cross(ab)/ab.magnitude();
    }
    
    public Vec2d shortestPath(Vec2d p){
        Vec2d ab = b.diff(a);
        Vec2d ap = p.diff(a);
        return ab.orthoNorm().scale(ab.cross(ap)/ab.magnitude());
    }
    
    public Vec2d direction(){
        return b.diff(a);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.a);
        hash = 79 * hash + Objects.hashCode(this.b);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ray2d other = (Ray2d) obj;
        if (!Objects.equals(this.a, other.a)) {
            return false;
        }
        if (!Objects.equals(this.b, other.b)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return a + ", " + b;
    }
    
}
