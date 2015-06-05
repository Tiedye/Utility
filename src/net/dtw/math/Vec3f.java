 
package net.dtw.math;

/**
 * Represents a 3 dimensional vector.
 * @author Daniel <tiedye1@hotmail.com>
 */
public class Vec3f {
    
    /**
     * Represents the x component.
     */
    public float x;
    /**
     * Represents the y component.
     */
    public float y;
    /**
     * Represents the z component.
     */
    public float z;
    
    /**
     * Initializes the vector with the x and y components from a 2d vector.
     * @param v the 2d vector
     * @param z the z component
     */
    public Vec3f(Vec2f v, float z){
        x = v.x;
        y = v.y;
        this.z = z;
    }
    /**
     * Initializes vector with components.
     * @param x the x component
     * @param y the y component
     * @param z the z component
     */
    public Vec3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    /**
     * Initializes a zero vector.
     */
    public Vec3f() {
        this(0, 0, 0);
    }
    
    /**
     * Creates a new vector that is the sum of this vector and the given vector.
     * @param v the given vector
     * @return the resultant vector
     */
    public Vec3f sum(Vec3f v) {
        return new Vec3f(x + v.x, y + v.y, z + v.z);
    }
    /**
     * Creates a new vector that is the sum of this vector and the given vector components.
     * @param x the given x component
     * @param y the given y component
     * @param z the given z component
     * @return the resultant vector
     */
    public Vec3f sum(float x, float y, float z) {
        return new Vec3f(this.x + x, this.y + y, this.z + z);
    }
    /**
     * Creates a new vector that is this vector minus the given vector. 
     * @param v the given vector
     * @return the resultant vector
     */
    public Vec3f diff(Vec3f v) {
        return new Vec3f(x - v.x, y - v.y, z - v.z);
    }
    /**
     * Creates a new vector that is this vector minus the given vector composed of the given components.
     * @param x the given x component
     * @param y the given y component
     * @param z the given z component
     * @return the resultant distance
     */
    public Vec3f diff(float x, float y, float z) {
        return new Vec3f(this.x - x, this.y - y, this.z - z);
    }
    /**
     * Creates a new vector that is this vector scaled by a given scalar.
     * @param n the given scalar
     * @return the resultant vector
     */
    public Vec3f scale(float n) {
        return new Vec3f(x*n, y*n, z*n);
    }
    /**
     * Calculates the magnitude of this vector.
     * @return the resultant magnitude
     */
    public float magnitude() {
        return (float)Math.cbrt(x*x + y*y + z*z);
    }
    /**
     * Calculates the dot product of this vector and the given component.
     * @param v the given vector
     * @return the resultant vector
     */
    public float dot(Vec3f v) {
        return x*v.x + y*v.y + z*v.z;
    }
    /**
     * Creates a new vector that is the cross product of this vector and the given vector.
     * @param v the given vector
     * @return the resultant vector
     */
    public Vec3f cross(Vec3f v) {
        return new Vec3f(y*v.z - z*v.y, z*v.x - x*v.z, x*v.y - y*v.x);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Float.floatToIntBits(this.x);
        hash = 79 * hash + Float.floatToIntBits(this.y);
        hash = 79 * hash + Float.floatToIntBits(this.z);
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
    
    /**
     * Creates a copy of this vector.
     * @return the copy
     */
    public Vec3f copy(){
        return new Vec3f(x, y, z);
    }
    
}
