
package net.dtw.math;

/**
 * Represents a 2 dimensional vector.
 * @author Daniel <tiedye1@hotmail.com>
 */
public class Vec2d {
    
    /**
     * The x component.
     */
    public double x;
    
    /**
     * The y component.
     */
    public double y;
    
    private double mx;
    private double my;
    private double mag;
    
    /**
     * A vector with magnitude POSITIVE_INFINITY.
     * @return The vector
     */
    public static Vec2d infVec() {
        Vec2d v = Vec2d.zeroVec();
        v.mx = v.x;
        v.my = v.y;
        v.mag = Double.POSITIVE_INFINITY;
        return v;
    }
    /**
     * A vector with magnitude NEGATIVE_INFINITY.
     * @return The vector
     */
    public static Vec2d nInfVec() {
        Vec2d v = Vec2d.zeroVec();
        v.mx = v.x;
        v.my = v.y;
        v.mag = Double.NEGATIVE_INFINITY;
        return v;
    }
    /**
     * A vector with magnitude 0.
     * @return The vector
     */
    public static Vec2d zeroVec() {
        return new Vec2d();
    }
    /**
     * A vector with given components.
     * @param x The x component
     * @param y The y component
     * @return The vector
     */
    public static Vec2d newVec(double x, double y) {
        return new Vec2d(x, y);
    }
    
    private Vec2d(double x, double y) {
        this.x = x;
        this.y = y;
    }
    private Vec2d() {
        this(0, 0);
        mx = x;
        my = y;
        mag = 0.0;
    }
    
    /**
     * Sums this vector and the given vector.
     * @param v The given vector
     * @return The sum of these two vectors
     */
    public Vec2d sum(Vec2d v) {
        return Vec2d.newVec(x + v.x, y + v.y);
    }
    /**
     * Sums this vector with the vector composed of the given components.
     * @param x The x component of the given vector
     * @param y The y component of the given vector
     * @return The sum of these two vectors
     */
    public Vec2d sum(double x, double y) {
        return Vec2d.newVec(this.x + x, this.y + y);
    }
    /**
     * Subtract the given vector from this vector to give the vector from this vector to the given vector.
     * @param v The given vector
     * @return The difference between these two vectors
     */
    public Vec2d diff(Vec2d v) {
        return Vec2d.newVec(x - v.x, y - v.y);
    }
    /**
     * Subtract the vector composed of the given components from this vector to give the vector from this vector to the given vector.
     * @param x The x component of the given vector
     * @param y The y component of the given vector
     * @return The difference between these two vectors
     */
    public Vec2d diff(double x, double y) {
        return Vec2d.newVec(this.x - x, this.y - y);
    }
    /**
     * Scales this vector by the given scalar.
     * @param n The given scalar
     * @return This vector scaled by the scalar
     */
    public Vec2d scale(double n) {
        Vec2d nv = Vec2d.newVec(x*n, y*n);
        if (x == mx && y == my) {
            nv.mx = nv.x;
            nv.my = nv.y;
            nv.mag = mag*n;
        }
        return nv;
    }
    /**
     * Rotates this vector about the origin by the quantity of radians signified by the given scalar.
     * @param r The given scalar
     * @return This vector rotated about the origin
     */
    public Vec2d rotate(double r) {
        double sinr = java.lang.Math.sin(r);
        double cosr = java.lang.Math.cos(r);
        return Vec2d.newVec(cosr * x + sinr * y, sinr * x - cosr * y);
    }
    /**
     * The magnitude of this vector.  Stores result for faster lookup.
     * @return The magnitude of this vector.
     */
    public double magnitude() {
        if (mx != x || my != y) mag = Math.hypot(x, y);
        mx = x;
        my = y;
        return mag;        
    }
    /**
     * Projects this vector along the given vector.
     * @param v The given vector
     * @return The projected vector
     */
    public Vec2d project(Vec2d v) {
        return v.norm().scale(dot(v)/v.magnitude());
    }
    /**
     * Projects this vector along the x axis.
     * @return The projected vector
     */
    public Vec2d projectX() {
        return Vec2d.newVec(x, 0);
    }
    /**
     * Projects this vector along the y axis.
     * @return The projected vector
     */
    public Vec2d projectY() {
        return Vec2d.newVec(0, y);
    }
    /**
     * Reflects this vector over the given vector treated as a ray going through the origin.
     * @param v The given vector
     * @return The reflected vector
     */
    public Vec2d reflect(Vec2d v) {
        return sum(v.orthoNorm().scale(-2.0 * cross(v) / v.magnitude()));
    }
    /**
     * Reflects this vector over the given ray.
     * @param r The given ray
     * @return The reflected vector
     */
    public Vec2d reflect(Ray2d r) {
        Vec2d d = r.direction();
        Vec2d td = diff(r.a);
        return td.sum(d.orthoNorm().scale(-2.0 * td.cross(d) / d.magnitude())).sum(r.a);
    }
    /**
     * Reflects this vector over the x axis.
     * @return The reflected vector
     */
    public Vec2d reflectX() {
        return Vec2d.newVec(x, -y);
    }
    /**
     * Reflects this vector over the y axis.
     * @return The reflected vector
     */
    public Vec2d reflectY() {
        return Vec2d.newVec(-x, y);
    }
    /**
     * Dots this vector and the given vector.
     * @param v The given vector
     * @return The dot of these two vectors
     */
    public double dot(Vec2d v) {
        return x*v.x + y*v.y;
    }
    /**
     * Crosses this vector and the given vector and calculates the magnitude of the product.
     * @param v The given vector
     * @return The magnitude of the cross product
     */
    public double cross(Vec2d v) {
        return x*v.y - y*v.x;
    }
    /**
     * Finds the normal vector orthogonal to this vector for which |AxB| > 0.
     * A is this vector
     * B is the given vector
     * @return The orthogonal normal
     */
    public Vec2d orthoNorm(){
        Vec2d nv = Vec2d.newVec(-y/magnitude(), x/magnitude());
        nv.mx = nv.x;
        nv.my = nv.y;
        nv.mag = 1.0;
        return nv;
    }
    /**
     * Finds the normalization of this vector.
     * @return The normalized vector
     */
    public Vec2d norm() {
        Vec2d nv = Vec2d.newVec(x/magnitude(), y/magnitude());
        nv.mx = nv.x;
        nv.my = nv.y;
        nv.mag = 1.0;
        return nv;
    }
    /**
     * Retracts this vector by a quantity signified by a given scalar.
     * Special behavior:
     * If this vector is retracted by a quantity such that the result would have an opposite direction, the returned magnitude of that vector would be negative.
     * This magnitude will be preserved through any successive retract, extend, and scale operations.
     * The magnitude will be reset by any sum operation on any of its components (unless it being summed with zero).
     * The magnitude will cause strange behavior with the normal, dot, and cross operations.
     * The magnitude can be fixed with the <code>fix()</code> method.
     * @param d The given scalar
     * @return The retracted vector
     */
    public Vec2d retract(double d) {
        Vec2d n = norm();
        n = n.scale(magnitude() - d);
        return n;
    }
    /**
     * Extends this vector by a quantity signified by a given scalar.
     * Special behavior:
     * If this vector is extended by a quantity such that the result would have an opposite direction, the returned magnitude of that vector would be negative.
     * This magnitude will be preserved through any successive retract, extend, and scale operations.
     * The magnitude will be reset by any sum operation on any of its components (unless it being summed with zero).
     * The magnitude will cause strange behavior with the normal, dot, and cross operations.
     * The magnitude can be fixed with the <code>fix()</code> method.
     * @param d The given scalar
     * @return The extended vector
     */
    public Vec2d extend(double d) {
        Vec2d n = norm();
        n = n.scale(magnitude() + d);
        return n;
    }
    /**
     * Fixes the magnitude of the vector.
     */
    public void fix(){
        mx = Double.NaN;
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
    
    /**
     * Creates an exact copy of this vector.  Magnitude irregularities are conserved.
     * @return 
     */
    public Vec2d copy(){
        Vec2d v = Vec2d.newVec(x, y);
        v.mx = mx;
        v.my = my;
        v.mag = mag;
        return v;
    }
    
}
