 
package net.dtw.math;

/**
 * Represents a 3 dimensional vector.
 * @author Daniel <tiedye1@hotmail.com>
 */
public class Vec3d {
    
    /**
     * Represents the x component.
     */
    public double x;
    /**
     * Represents the y component.
     */
    public double y;
    /**
     * Represents the z component.
     */
    public double z;
    
    /**
     * Initializes the vector with the x and y components from a 2d vector.
     * @param v the 2d vector
     * @param z the z component
     */
    public Vec3d(Vec2d v, double z){
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
    public Vec3d(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    /**
     * Initializes a zero vector.
     */
    public Vec3d() {
        this(0, 0, 0);
    }
    
    /**
     * Creates a new vector that is the sum of this vector and the given vector.
     * @param v the given vector
     * @return the resultant vector
     */
    public Vec3d sum(Vec3d v) {
        return new Vec3d(x + v.x, y + v.y, z + v.z);
    }
    /**
     * Creates a new vector that is the sum of this vector and the given vector components.
     * @param x the given x component
     * @param y the given y component
     * @param z the given z component
     * @return the resultant vector
     */
    public Vec3d sum(double x, double y, double z) {
        return new Vec3d(this.x + x, this.y + y, this.z + z);
    }
    /**
     * Creates a new vector that is this vector minus the given vector. 
     * @param v the given vector
     * @return the resultant vector
     */
    public Vec3d diff(Vec3d v) {
        return new Vec3d(x - v.x, y - v.y, z - v.z);
    }
    /**
     * Creates a new vector that is this vector scaled by a given scalar.
     * @param n the given scalar
     * @return the resultant vector
     */
    public Vec3d scale(double n) {
        return new Vec3d(x*n, y*n, z*n);
    }
    /**
     * Calculates the magnitude of this vector.
     * @return the resultant magnitude
     */
    public double magnitude() {
        return Math.cbrt(x*x + y*y + z*z);
    }
    /**
     * Calculates the distance of the vector that is this vector minus the vector composed of the given components.
     * @param x the given x component
     * @param y the given y component
     * @param z the given z component
     * @return the resultant distance
     */
    public double distance(double x, double y, double z) {
        double dx = this.x - x;
        double dy = this.y - y;
        double dz = this.z - z;
        return Math.cbrt(dx*dx + dy*dy + dz*dz);
    }
    /**
     * Calculates the dot product of this vector and the given component.
     * @param v the given vector
     * @return the resultant vector
     */
    public double dot(Vec3d v) {
        return x*v.x + y*v.y + z*v.z;
    }
    /**
     * Creates a new vector that is the cross product of this vector and the given vector.
     * @param v the given vector
     * @return the resultant vector
     */
    public Vec3d cross(Vec3d v) {
        return new Vec3d(y*v.z - z*v.y, z*v.x - x*v.z, x*v.y - y*v.x);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.z) ^ (Double.doubleToLongBits(this.z) >>> 32));
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
        final Vec3d other = (Vec3d) obj;
        if (Double.doubleToLongBits(this.x) != Double.doubleToLongBits(other.x)) {
            return false;
        }
        if (Double.doubleToLongBits(this.y) != Double.doubleToLongBits(other.y)) {
            return false;
        }
        if (Double.doubleToLongBits(this.z) != Double.doubleToLongBits(other.z)) {
            return false;
        }
        return true;
    }
    
    /**
     * Creates a copy of this vector.
     * @return the copy
     */
    public Vec3d copy(){
        return new Vec3d(x, y, z);
    }
    
}
