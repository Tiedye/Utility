
package net.dtw.math;

/**
 * Represents a 2 dimensional vector.
 * @author Daniel <tiedye1@hotmail.com>
 */
public class Vec2f {
    
    /**
     * The x component.
     */
    public float x;
    
    /**
     * The y component.
     */
    public float y;
    
    private float mx;
    private float my;
    private float mag;
    
    /**
     * A vector with magnitude POSITIVE_INFINITY.
     * @return The vector
     */
    public static Vec2f infVec() {
        Vec2f v = Vec2f.zeroVec();
        v.mx = v.x;
        v.my = v.y;
        v.mag = Float.POSITIVE_INFINITY;
        return v;
    }
    /**
     * A vector with magnitude NEGATIVE_INFINITY.
     * @return The vector
     */
    public static Vec2f nInfVec() {
        Vec2f v = Vec2f.zeroVec();
        v.mx = v.x;
        v.my = v.y;
        v.mag = Float.NEGATIVE_INFINITY;
        return v;
    }
    /**
     * A vector with magnitude 0.
     * @return The vector
     */
    public static Vec2f zeroVec() {
        return new Vec2f();
    }
    /**
     * A vector with given components.
     * @param x The x component
     * @param y The y component
     * @return The vector
     */
    public static Vec2f newVec(float x, float y) {
        return new Vec2f(x, y);
    }
    
    private Vec2f(float x, float y) {
        this.x = x;
        this.y = y;
    }
    private Vec2f() {
        this(0, 0);
        mx = x;
        my = y;
        mag = 0.0f;
    }
    
    /**
     * Sums this vector and the given vector.
     * @param v The given vector
     * @return The sum of these two vectors
     */
    public Vec2f sum(Vec2f v) {
        return Vec2f.newVec(x + v.x, y + v.y);
    }
    /**
     * Sums this vector with the vector composed of the given components.
     * @param x The x component of the given vector
     * @param y The y component of the given vector
     * @return The sum of these two vectors
     */
    public Vec2f sum(float x, float y) {
        return Vec2f.newVec(this.x + x, this.y + y);
    }
    /**
     * Subtract the given vector from this vector to give the vector from this vector to the given vector.
     * @param v The given vector
     * @return The difference between these two vectors
     */
    public Vec2f diff(Vec2f v) {
        return Vec2f.newVec(x - v.x, y - v.y);
    }
    /**
     * Subtract the vector composed of the given components from this vector to give the vector from this vector to the given vector.
     * @param x The x component of the given vector
     * @param y The y component of the given vector
     * @return The difference between these two vectors
     */
    public Vec2f diff(float x, float y) {
        return Vec2f.newVec(this.x - x, this.y - y);
    }
    /**
     * Scales this vector by the given scalar.
     * @param n The given scalar
     * @return This vector scaled by the scalar
     */
    public Vec2f scale(float n) {
        Vec2f nv = Vec2f.newVec(x*n, y*n);
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
    public Vec2f rotate(float r) {
        float sinr = (float)java.lang.Math.sin(r);
        float cosr = (float)java.lang.Math.cos(r);
        return Vec2f.newVec(cosr * x + sinr * y, sinr * x - cosr * y);
    }
    /**
     * The magnitude of this vector.  Stores result for faster lookup.
     * @return The magnitude of this vector.
     */
    public float magnitude() {
        if (mx != x || my != y) mag = (float)Math.hypot(x, y);
        mx = x;
        my = y;
        return mag;        
    }
    /**
     * Projects this vector along the given vector.
     * @param v The given vector
     * @return The projected vector
     */
    public Vec2f project(Vec2f v) {
        return v.norm().scale(dot(v)/v.magnitude());
    }
    /**
     * Projects this vector along the x axis.
     * @return The projected vector
     */
    public Vec2f projectX() {
        return Vec2f.newVec(x, 0);
    }
    /**
     * Projects this vector along the y axis.
     * @return The projected vector
     */
    public Vec2f projectY() {
        return Vec2f.newVec(0, y);
    }
    /**
     * Reflects this vector over the given vector treated as a ray going through the origin.
     * @param v The given vector
     * @return The reflected vector
     */
    public Vec2f reflect(Vec2f v) {
        return sum(v.orthoNorm().scale(-2.0f * cross(v) / v.magnitude()));
    }
    /**
     * Reflects this vector over the given ray.
     * @param r The given ray
     * @return The reflected vector
     */
    public Vec2f reflect(Ray2f r) {
        Vec2f d = r.direction();
        Vec2f td = diff(r.a);
        return td.sum(d.orthoNorm().scale(-2.0f * td.cross(d) / d.magnitude())).sum(r.a);
    }
    /**
     * Reflects this vector over the x axis.
     * @return The reflected vector
     */
    public Vec2f reflectX() {
        return Vec2f.newVec(x, -y);
    }
    /**
     * Reflects this vector over the y axis.
     * @return The reflected vector
     */
    public Vec2f reflectY() {
        return Vec2f.newVec(-x, y);
    }
    /**
     * Dots this vector and the given vector.
     * @param v The given vector
     * @return The dot of these two vectors
     */
    public float dot(Vec2f v) {
        return x*v.x + y*v.y;
    }
    /**
     * Crosses this vector and the given vector and calculates the magnitude of the product.
     * @param v The given vector
     * @return The magnitude of the cross product
     */
    public float cross(Vec2f v) {
        return x*v.y - y*v.x;
    }
    /**
     * Finds the normal vector orthogonal to this vector for which |AxB| > 0.
     * A is this vector
     * B is the given vector
     * @return The orthogonal normal
     */
    public Vec2f orthoNorm(){
        Vec2f nv = Vec2f.newVec(-y/magnitude(), x/magnitude());
        nv.mx = nv.x;
        nv.my = nv.y;
        nv.mag = 1.0f;
        return nv;
    }
    /**
     * Finds the normalization of this vector.
     * @return The normalized vector
     */
    public Vec2f norm() {
        Vec2f nv = Vec2f.newVec(x/magnitude(), y/magnitude());
        nv.mx = nv.x;
        nv.my = nv.y;
        nv.mag = 1.0f;
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
    public Vec2f retract(float d) {
        Vec2f n = norm();
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
    public Vec2f extend(float d) {
        Vec2f n = norm();
        n = n.scale(magnitude() + d);
        return n;
    }
    /**
     * Fixes the magnitude of the vector.
     */
    public void fix(){
        mx = Float.NaN;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ')';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Float.floatToIntBits(this.x);
        hash = 53 * hash + Float.floatToIntBits(this.y);
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
    
    /**
     * Creates an exact copy of this vector.  Magnitude irregularities are conserved.
     * @return 
     */
    public Vec2f copy(){
        Vec2f v = Vec2f.newVec(x, y);
        v.mx = mx;
        v.my = my;
        v.mag = mag;
        return v;
    }
    
}
