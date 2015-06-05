
package net.dtw.math;

/**
 * Represents a simple, static convex polygon.
 * @author Daniel <tiedye1@hotmail.com>
 */
public class SimpleConvexd extends Boundd {
    
    private Ray2d[] sides;
    private Vec2d[] vertices;
    
    private AABBd aabb;

    /**
     * Initializes the polygon with given vertices.
     * <b>Important Note: If the points do not form a convex polygon (interpreted in a clockwise fashion), the separating axis calculation will not function!</b>
     * @param vertices The list of vertices.
     */
    public SimpleConvexd(Vec2d[] vertices) {
        this.vertices = vertices;
        sides = new Ray2d[vertices.length];
        double mX = Double.POSITIVE_INFINITY;
        double MX = Double.NEGATIVE_INFINITY;
        double mY = Double.POSITIVE_INFINITY;
        double MY = Double.NEGATIVE_INFINITY;
        for(int i = 0; i < vertices.length; i++){
            Vec2d v = vertices[i];
            mX = v.x < mX ? v.x : mX;
            MX = v.x > MX ? v.x : MX;
            mY = v.y < mY ? v.y : mY;
            MY = v.y > MY ? v.y : MY;
            sides[i] = Ray2d.newRay(v, vertices[(i+1)%vertices.length]);
        }
        aabb = AABBd.newAABB(MY, mY, MX, mX);
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
        return sides;
    }

    @Override
    public Vec2d[] getVerticies() {
        return vertices;
    }

    @Override
    public AABBd getAABB() {
        return aabb.copy();
    }
    
}
