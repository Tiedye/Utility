
package net.dtw.math;

/**
 * Represents a 2 dimensional vector.
 * @author Daniel <tiedye1@hotmail.com>
 */
public class Vec2f {
    
    public float x;
    public float y;
    
    private float mx;
    private float my;
    private float mag;
    
    public static Vec2f infVec() {
        Vec2f v = new Vec2f();
        v.mx = v.x;
        v.my = v.y;
        v.mag = Float.POSITIVE_INFINITY;
        return v;
    }
    
    public Vec2f(float x, float y) {
        this.x = x;
        this.y = y;
    }
    public Vec2f() {
        this(0, 0);
        mx = x;
        my = y;
        mag = 0.0f;
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
        Vec2f nv = new Vec2f(x*n, y*n);
        if (x == mx && y == my) {
            nv.mx = nv.x;
            nv.my = nv.y;
            nv.mag = mag*n;
        }
        return nv;
    }
    public Vec2f rotate(float r) {
        float sinr = (float)java.lang.Math.sin(r);
        float cosr = (float)java.lang.Math.cos(r);
        return new Vec2f(cosr * x - sinr * y, sinr * x + cosr * y);
    }
    public float magnitude() {
        if (mx != x || my != y) mag = (float)Math.hypot(x, y);
        mx = x;
        my = y;
        return mag;        
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
    public Vec2f orthoNorm(){
        Vec2f nv = new Vec2f(-y/magnitude(), x/magnitude());
        nv.mx = nv.x;
        nv.my = nv.y;
        nv.mag = 1.0f;
        return nv;
    }
    public Vec2f norm() {
        Vec2f nv = new Vec2f(x/magnitude(), y/magnitude());
        nv.mx = nv.x;
        nv.my = nv.y;
        nv.mag = 1.0f;
        return nv;
    }
    public Vec2f retract(float d) {
        Vec2f n = norm();
        n.scale(magnitude() - d);
        return n;
    }
    public Vec2f extend(float d) {
        Vec2f n = norm();
        n.scale(magnitude() + d);
        return n;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ')';
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        Vec2f v = new Vec2f(x, y);
        v.mx = mx;
        v.my = my;
        v.mag = mag;
        return v;
    }
    
}
