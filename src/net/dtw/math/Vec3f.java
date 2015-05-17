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
public class Vec3f {
    
    public float x;
    public float y;
    public float z;
    
    public Vec3f(Vec2f v, float z){
        x = v.x;
        y = v.y;
        this.z = z;
    }

    public Vec3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public Vec3f() {
        this(0, 0, 0);
    }
    
    public Vec3f sum(Vec3f v) {
        return new Vec3f(x + v.x, y + v.y, z + v.z);
    }
    
    public Vec3f sum(float x, float y, float z) {
        return new Vec3f(this.x + x, this.y + y, this.z + z);
    }
    
    public Vec3f diff(Vec3f v) {
        return new Vec3f(x - v.x, y - v.y, z - v.z);
    }
    
    public Vec3f scale(float n) {
        return new Vec3f(x*n, y*n, z*n);
    }
    
    public float magnitude() {
        return (float)Math.cbrt(x*x + y*y + z*z);
    }
    
    public float distance(float x, float y, float z) {
        float dx = this.x - x;
        float dy = this.y - y;
        float dz = this.z - z;
        return (float)Math.cbrt(dx*dx + dy*dy + dz*dz);
    }
    
    public float dot(Vec3f v) {
        return x*v.x + y*v.y;
    }
    
    public float cross(Vec3f v) {
        return x*v.y - y*v.x;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")f";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Float.floatToIntBits(this.x);
        hash = 11 * hash + Float.floatToIntBits(this.y);
        hash = 11 * hash + Float.floatToIntBits(this.z);
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
        final Vec3f other = (Vec3f) obj;
        if (Float.floatToIntBits(this.x) != Float.floatToIntBits(other.x)) {
            return false;
        }
        if (Float.floatToIntBits(this.y) != Float.floatToIntBits(other.y)) {
            return false;
        }
        if (Float.floatToIntBits(this.z) != Float.floatToIntBits(other.z)) {
            return false;
        }
        return true;
    }
    
    
    
    public Vec3f copy(){
        return new Vec3f(x, y, z);
    }
    
    
}
