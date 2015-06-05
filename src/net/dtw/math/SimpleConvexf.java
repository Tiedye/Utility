
package net.dtw.math;

/**
 * Represents a simple, static convex polygon.
 * @author Daniel <tiedye1@hotmail.com>
 */
public class SimpleConvexf extends Boundf {
    
    private Ray2f[] sides;
    private Vec2f[] vertices;
    
    private AABBf aabb;

    /**
     * Initializes the polygon with given vertices.
     * <b>Important Note: If the points do not form a convex polygon (interpreted in a clockwise fashion), the separating axis calculation will not function!</b>
     * @param vertices The list of vertices.
     */
    public SimpleConvexf(Vec2f[] vertices) {
        this.vertices = vertices;
        sides = new Ray2f[vertices.length];
        float mX = Float.POSITIVE_INFINITY;
        float MX = Float.NEGATIVE_INFINITY;
        float mY = Float.POSITIVE_INFINITY;
        float MY = Float.NEGATIVE_INFINITY;
        for(int i = 0; i < vertices.length; i++){
            Vec2f v = vertices[i];
            mX = v.x < mX ? v.x : mX;
            MX = v.x > MX ? v.x : MX;
            mY = v.y < mY ? v.y : mY;
            MY = v.y > MY ? v.y : MY;
            sides[i] = Ray2f.newRay(v, vertices[(i+1)%vertices.length]);
        }
        aabb = AABBf.newAABB(MY, mY, MX, mX);
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
        return sides;
    }

    @Override
    public Vec2f[] getVerticies() {
        return vertices;
    }

    @Override
    public AABBf getAABB() {
        return aabb.copy();
    }
    
}
