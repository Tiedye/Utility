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

    public Ray2d() {
        this(new Vec2d(), new Vec2d());
    }

    public Ray2d(Vec2d a, Vec2d b) {
        this.a = a;
        this.b = b;
    }
    
    public double distance(Vec2d p){
        Vec2d ab = b.diff(a);
        return p.diff(a).cross(ab)/ab.magnitude();
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
    
    
}
