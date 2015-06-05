
package net.dtw.math;

/**
 * Represents a circle.
 * @author Daniel <tiedye1@hotmail.com>
 */
public class Circled extends Boundd {
    
    /**
     * The center of the circle.
     */
    public Vec2d center;
    /**
     * The radius of the circle.
     */
    public double radius;
    
    private AABBd bounds;

    /**
     * Creates a unit circle at the origin.
     */
    public Circled() {
        this(Vec2d.zeroVec(), 1.0);
    }

    /**
     * Creates a circle with  given center and radius.
     * @param center The center
     * @param radius The radius
     */
    public Circled(Vec2d center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    @Override
    public boolean inBounds(Vec2d p) {
        return p.diff(center).magnitude() <= radius;
    }

    @Override
    public Ray2d[] getSides() {
        return new Ray2d[]{};
    }

    @Override
    public Vec2d[] getVerticies() {
        return new Vec2d[]{};
    }
    
    @Override
    public AABBd getAABB(){
        return AABBd.newAABB(center.y + radius, center.y - radius, center.x + radius, center.y + radius);
    }
    
}