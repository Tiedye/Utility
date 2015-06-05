
package net.dtw.math;

import java.util.Objects;

/**
 * Represents a ray in 2d space.
 * @author Daniel <tiedye1@hotmail.com>
 */
public class Ray2d {
    /**
     * Represents the starting point of the ray.
     */
    public Vec2d a;
    /**
     * Represents the ending point of the ray.
     */
    public Vec2d b;
    
    /**
     * Get a undefined ray that intersects the origin.
     * @return The ray
     */
    public static Ray2d zeroRay(){
        return newRay(Vec2d.zeroVec(), Vec2d.zeroVec());
    }
    /**
     * Get a ray that goes from one point to another.
     * @param a The first point
     * @param b The second point
     * @return The new ray
     */
    public static Ray2d newRay(Vec2d a, Vec2d b){
        return new Ray2d(a, b);
    }

    private Ray2d(Vec2d a, Vec2d b) {
        this.a = a;
        this.b = b;
    }
    
    /**
     * Find the distance from the ray to a point.
     * @param p The point
     * @return The distance
     */
    public double distance(Vec2d p){
        Vec2d ab = b.diff(a);
        return p.diff(a).cross(ab)/ab.magnitude();
    }
    
    /**
     * Find the shortest vector from the ray to a point.
     * @param p The point
     * @return The displacement vector
     */
    public Vec2d shortestPath(Vec2d p){
        Vec2d ab = b.diff(a);
        Vec2d ap = p.diff(a);
        return ab.orthoNorm().scale(ab.cross(ap)/ab.magnitude());
    }
    
    /**
     * Returns the vector that is B-A.
     * @return The resultant vector
     */
    public Vec2d direction(){
        return b.diff(a);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.a);
        hash = 79 * hash + Objects.hashCode(this.b);
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
        final Ray2d other = (Ray2d) obj;
        if (!Objects.equals(this.a, other.a)) {
            return false;
        }
        if (!Objects.equals(this.b, other.b)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return a + ", " + b;
    }
    
}
