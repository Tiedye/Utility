
package net.dtw.math;

/**
 * Represents a circle.
 * @author Daniel <tiedye1@hotmail.com>
 */
public class Circlef extends Boundf {
    
    /**
     * The center of the circle.
     */
    public Vec2f center;
    /**
     * The radius of the circle.
     */
    public float radius;
    
    private AABBf bounds;

    /**
     * Creates a unit circle at the origin.
     */
    public Circlef() {
        this(Vec2f.zeroVec(), 1.0f);
    }

    /**
     * Creates a circle with  given center and radius.
     * @param center The center
     * @param radius The radius
     */
    public Circlef(Vec2f center, float radius) {
        this.center = center;
        this.radius = radius;
    }

    @Override
    public boolean inBounds(Vec2f p) {
        return p.diff(center).magnitude() <= radius;
    }

    @Override
    public Ray2f[] getSides() {
        return new Ray2f[]{};
    }

    @Override
    public Vec2f[] getVerticies() {
        return new Vec2f[]{};
    }
    
    @Override
    public AABBf getAABB(){
        return AABBf.newAABB(center.y + radius, center.y - radius, center.x + radius, center.y + radius);
    }
    
}