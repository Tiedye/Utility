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
public class Vec2i {
    
    public int x;
    public int y;

    public Vec2i(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public Vec2i() {
        this(0, 0);
    }
    
    public Vec2i sum(Vec2i v) {
        return new Vec2i(x + v.x, y + v.y);
    }
    
    public Vec2i sum(int x, int y) {
        return new Vec2i(this.x + x, this.y + y);
    }
    
    public Vec2i diff(Vec2i v) {
        return new Vec2i(x - v.x, y - v.y);
    }
    
    public Vec2i scale(int n) {
        return new Vec2i(x*n, y*n);
    }
    
    public Vec2i nextQuadrant() {
        if (x > 0 ^ y > 0) return reflectX();
        else return reflectY();
    }
    
    private static int magnitude(int x, int y){
        return (int)java.lang.Math.sqrt(x*x+y*y);
    }
    
    public int magnitude() {
        return magnitude(x, y);
    }
    
    public int distance(int x, int y) {
        return magnitude(this.x - x, this.y - y);
    }
    
    public Vec2i projectX() {
        return new Vec2i(x, 0);
    }
    
    public Vec2i projectY() {
        return new Vec2i(0, y);
    }
    
    public Vec2i reflectX() {
        return new Vec2i(x, -y);
    }
    
    public Vec2i reflectY() {
        return new Vec2i(-x, y);
    }
    
    public int dot(Vec2i v) {
        return x*v.x + y*v.y;
    }
    
    public int cross(Vec2i v) {
        return x*v.y - y*v.x;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ')';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.x;
        hash = 79 * hash + this.y;
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
        final Vec2i other = (Vec2i) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }
    
    public Vec2i copy(){
        return new Vec2i(x, y);
    }
    
    
}
