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
public class Vec2f {
    
    public float x;
    public float y;

    public Vec2f(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    public Vec2f() {
        this(0, 0);
    }
    
    public Vec2f sum(Vec2f v) {
        return new Vec2f(x + v.x, y + v.y);
    }
    
    public Vec2f sum(float x, float y) {
        return new Vec2f(this.x + x, this.y + y);
    }
    
    public Vec2f diff(Vec2f v) {
        return new Vec2f(x - v.x, y - v.y);
    }
    
    public Vec2f scale(float n) {
        return new Vec2f(x*n, y*n);
    }
    
    public Vec2f rotate(float r) {
        throw new RuntimeException("Not implemented.");
    }
    
    public float magnitude() {
        return (float)Math.hypot(x, y);
    }
    
    public float distance(float x, float y) {
        return (float)Math.hypot(this.x - x, this.y - y);
    }
    
    public Vec2f project(Vec2f v) {
        throw new RuntimeException("Not implemented.");
    }
    
    public Vec2f projectX() {
        return new Vec2f(x, 0);
    }
    
    public Vec2f projectY() {
        return new Vec2f(0, y);
    }
    
    public Vec2f reflect(Vec2f v) {
        throw new RuntimeException("Not implemented.");
    }
    
    public Vec2f reflectX() {
        return new Vec2f(x, -y);
    }
    
    public Vec2f reflectY() {
        return new Vec2f(-x, y);
    }
    
    public float dot(Vec2f v) {
        return x*v.x + y*v.y;
    }
    
    public float cross(Vec2f v) {
        return x*v.y - y*v.x;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")f";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Float.floatToIntBits(this.x);
        hash = 97 * hash + Float.floatToIntBits(this.y);
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
        final Vec2f other = (Vec2f) obj;
        if (Float.floatToIntBits(this.x) != Float.floatToIntBits(other.x)) {
            return false;
        }
        if (Float.floatToIntBits(this.y) != Float.floatToIntBits(other.y)) {
            return false;
        }
        return true;
    }
    
    public Vec2f copy(){
        return new Vec2f(x, y);
    }
    
    
}
