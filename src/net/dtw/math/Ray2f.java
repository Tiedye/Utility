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
public class Ray2f {
    public Vec2f a;
    public Vec2f b;

    public Ray2f() {
        this(new Vec2f(), new Vec2f());
    }

    public Ray2f(Vec2f a, Vec2f b) {
        this.a = a;
        this.b = b;
    }
    
    public double distance(Vec2f p){
        Vec2f ab = b.diff(a);
        return p.diff(a).cross(ab)/ab.magnitude();
    }
    
    public Vec2f shortestPath(Vec2f p){
        Vec2f ab = b.diff(a);
        Vec2f ap = p.diff(a);
        return ab.orthoNorm().scale(ap.cross(ab)/ab.magnitude());
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
        final Ray2f other = (Ray2f) obj;
        if (!Objects.equals(this.a, other.a)) {
            return false;
        }
        if (!Objects.equals(this.b, other.b)) {
            return false;
        }
        return true;
    }
    
    
}
