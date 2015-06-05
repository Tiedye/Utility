
package net.dtw.math;

/**
 * Represents a convex polygon.
 * @author Daniel <tiedye1@hotmail.com>
 */
public class Convexf extends Boundf {
    
    private final Vec2f center;
    private float rotation;
    private float scale;
    private boolean recent;
    private final Vec2f[] vertices;
    private final Vec2f[] cVerticies;
    private final Ray2f[] sides;
    
    private AABBf aabb;
    
    /**
     * Creates a convex polygon with a center and vertices offset by given amounts from the center.
     * @param center The center
     * @param vertices The vertex offset list
     */
    public Convexf(Vec2f center, Vec2f[] vertices){
        this.center = center;
        this.vertices = vertices;
        cVerticies = new Vec2f[vertices.length];
        sides = new Ray2f[vertices.length];
        rotation = 0.0f;
        scale = 1.0f;
    }

    private void recalculateSides(){
        if (recent) return;
        float mX = Float.POSITIVE_INFINITY;
        float MX = Float.NEGATIVE_INFINITY;
        float mY = Float.POSITIVE_INFINITY;
        float MY = Float.NEGATIVE_INFINITY;
        for (int i = 0; i < vertices.length; i++) {
            cVerticies[i] = vertices[i].rotate(rotation).scale(scale).sum(center);
            mX = cVerticies[i].x < mX ? cVerticies[i].x : mX;
            MX = cVerticies[i].x > MX ? cVerticies[i].x : MX;
            mY = cVerticies[i].y < mY ? cVerticies[i].y : mY;
            MY = cVerticies[i].y > MY ? cVerticies[i].y : MY;
        }
        aabb = AABBf.newAABB(MY, mY, MX, mX);
        for (int i = 0; i < cVerticies.length; i++) {
            sides[i] = Ray2f.newRay(cVerticies[i], cVerticies[(i+1)%cVerticies.length]);
        }
        recent = true;
    }
    
    /**
     * Get the rotation transformation of the polygon.
     * @return The rotation scalar
     */
    public float getRotation(){
        return rotation;
    }
    
    /**
     * Set the rotation transformation of the polygon.
     * @param rotation The rotation scalar
     */
    public void setRotation(float rotation){
        this.rotation = rotation;
        recent = false;
    }

    /**
     * Get the scale transformation of the polygon.
     * @return The scale
     */
    public float getScale() {
        return scale;
    }

    /**
     * Get the scale transformation of the polygon.
     * @param scale The scale
     */
    public void setScale(float scale) {
        this.scale = scale;
        recent = false;
    }
    
    @Override
    public boolean inBounds(Vec2f p) {
        return calcSA(new Boundf() {
            @Override
            public Ray2f[] getSides() {
                return new Ray2f[]{};
            }
            @Override
            public Vec2f[] getVerticies() {
                return new Vec2f[]{p};
            }
            @Override
            public AABBf getAABB() {return null;}
            @Override
            public boolean inBounds(Vec2f p) {return false;}
        }).magnitude() != 0.0;
    }

    @Override
    public Ray2f[] getSides() {
        recalculateSides();
        return sides;
    }

    @Override
    public Vec2f[] getVerticies() {
        recalculateSides();
        return cVerticies;
    }

    @Override
    public AABBf getAABB() {
        return aabb;
    }
    
}
