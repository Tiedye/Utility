
package net.dtw.math;

/**
 * Represents a 2 dimensional vector.
 * @author Daniel <tiedye1@hotmail.com>
 */
public class Vec2d {
    
    public double x;
    public double y;

    public Vec2d(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public Vec2d() {
        this(0, 0);
    }
    
    public Vec2d sum(Vec2d v) {
        return new Vec2d(x + v.x, y + v.y);
    }
    public Vec2d sum(double x, double y) {
        return new Vec2d(this.x + x, this.y + y);
    }
    public Vec2d diff(Vec2d v) {
        return new Vec2d(x - v.x, y - v.y);
    }
    public Vec2d scale(double n) {
        return new Vec2d(x*n, y*n);
    }
    public Vec2d rotate(double r) {
        double sinr = java.lang.Math.sin(r);
        double cosr = java.lang.Math.cos(r);
        return new Vec2d(cosr * x - sinr * y, sinr * x + cosr * y);
    }
    public double magnitude() {
        return Math.hypot(x, y);
    }
    public double distance(double x, double y) {
        return Math.hypot(this.x - x, this.y - y);
    }
    public Vec2d project(Vec2d v) {
        throw new RuntimeException("Not implemented.");
    }
    public Vec2d projectX() {
        return new Vec2d(x, 0);
    }
    public Vec2d projectY() {
        return new Vec2d(0, y);
    }
    public Vec2d reflect(Vec2d v) {
        throw new RuntimeException("Not implemented.");
    }
    public Vec2d reflectX() {
        return new Vec2d(x, -y);
    }
    public Vec2d reflectY() {
        return new Vec2d(-x, y);
    }
    public double dot(Vec2d v) {
        return x*v.x + y*v.y;
    }
    public double cross(Vec2d v) {
        return x*v.y - y*v.x;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ')';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
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
        final Vec2d other = (Vec2d) obj;
        if (Double.doubleToLongBits(this.x) != Double.doubleToLongBits(other.x)) {
            return false;
        }
        return Double.doubleToLongBits(this.y) == Double.doubleToLongBits(other.y);
    }
    
    public Vec2d copy(){
        return new Vec2d(x, y);
    }
    
}
