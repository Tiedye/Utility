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
public class Vec3i {
    
    public int x;
    public int y;
    public int z;
    
    public Vec3i(Vec2i v, int z){
        x = v.x;
        y = v.y;
        this.z = z;
    }

    public Vec3i(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public Vec3i() {
        this(0, 0, 0);
    }
    
    public Vec3i sum(Vec3i v) {
        return new Vec3i(x + v.x, y + v.y, z + v.z);
    }
    
    public Vec3i sum(int x, int y, int z) {
        return new Vec3i(this.x + x, this.y + y, this.z + z);
    }
    
    public Vec3i diff(Vec3i v) {
        return new Vec3i(x - v.x, y - v.y, z - v.z);
    }
    
    public Vec3i scale(int n) {
        return new Vec3i(x*n, y*n, z*n);
    }
    
    public int magnitude() {
        return (int)Math.cbrt(x*x + y*y + z*z);
    }
    
    public int distance(int x, int y, int z) {
        int dx = this.x - x;
        int dy = this.y - y;
        int dz = this.z - z;
        return (int)Math.cbrt(dx*dx + dy*dy + dz*dz);
    }
    
    public int dot(Vec3i v) {
        return x*v.x + y*v.y;
    }
    
    public int cross(Vec3i v) {
        return x*v.y - y*v.x;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.x;
        hash = 83 * hash + this.y;
        hash = 83 * hash + this.z;
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
        final Vec3i other = (Vec3i) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        if (this.z != other.z) {
            return false;
        }
        return true;
    }
    
    
    
    public Vec3i copy(){
        return new Vec3i(x, y, z);
    }
    
    
}
