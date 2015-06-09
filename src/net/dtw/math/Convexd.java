
package net.dtw.math;

/**
 * Represents a convex polygon.
 * @author Daniel <tiedye1@hotmail.com>
 */
public class Convexd extends Boundd {
    
    private final Vec2d center;
    private double rotation;
    private double scale;
    private boolean recent;
    private final Vec2d[] vertices;
    private final Vec2d[] cVerticies;
    private final Ray2d[] sides;
    
    private AABBd aabb;
    
    /**
     * Creates a convex polygon with a center and vertices offset by given amounts from the center.
     * @param center The center
     * @param vertices The vertex offset list
     */
    public Convexd(Vec2d center, Vec2d[] vertices){
        this.center = center;
        this.vertices = vertices;
        cVerticies = new Vec2d[vertices.length];
        sides = new Ray2d[vertices.length];
        rotation = 0.0;
        scale = 1.0;
    }

    private void recalculateSides(){
        if (recent) return;
        double mX = Double.POSITIVE_INFINITY;
        double MX = Double.NEGATIVE_INFINITY;
        double mY = Double.POSITIVE_INFINITY;
        double MY = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < vertices.length; i++) {
            cVerticies[i] = vertices[i].rotate(rotation).scale(scale).sum(center);
            mX = cVerticies[i].x < mX ? cVerticies[i].x : mX;
            MX = cVerticies[i].x > MX ? cVerticies[i].x : MX;
            mY = cVerticies[i].y < mY ? cVerticies[i].y : mY;
            MY = cVerticies[i].y > MY ? cVerticies[i].y : MY;
        }
        aabb = AABBd.newAABB(MY, mY, MX, mX);
        for (int i = 0; i < cVerticies.length; i++) {
            sides[i] = Ray2d.newRay(cVerticies[i], cVerticies[(i+1)%cVerticies.length]);
        }
        recent = true;
    }
    
    /**
     * Get the rotation transformation of the polygon.
     * @return The rotation scalar
     */
    public double getRotation(){
        return rotation;
    }
    
    /**
     * Set the rotation transformation of the polygon.
     * @param rotation The rotation scalar
     */
    public void setRotation(double rotation){
        this.rotation = rotation;
        recent = false;
    }

    /**
     * Get the scale transformation of the polygon.
     * @return The scale
     */
    public double getScale() {
        return scale;
    }

    /**
     * Get the scale transformation of the polygon.
     * @param scale The scale
     */
    public void setScale(double scale) {
        this.scale = scale;
        recent = false;
    }
    
    @Override
    public boolean inBounds(Vec2d p) {
        return calcSA(new Boundd() {
            @Override
            public Ray2d[] getSides() {
                return new Ray2d[]{};
            }
            @Override
            public Vec2d[] getVerticies() {
                return new Vec2d[]{p};
            }
            @Override
            public AABBd getAABB() {return null;}
            @Override
            public boolean inBounds(Vec2d p) {return false;}
        }).magnitude() != 0.0;
    }

    @Override
    public Ray2d[] getSides() {
        recalculateSides();
        return sides;
    }

    @Override
    public Vec2d[] getVerticies() {
        recalculateSides();
        return cVerticies;
    }

    @Override
    public AABBd getAABB() {
        recalculateSides();
        return aabb;
    }
    
}
