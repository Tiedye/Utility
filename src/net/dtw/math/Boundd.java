
package net.dtw.math;

/**
 * Represents a convex bound.
 * @author Daniel <tiedye1@hotmail.com>
 */
public abstract class Boundd {
    
    /**
     * Calculates the separating axis between two bounding convex bounding areas.
     * @param o Other bounding area.
     * @return 
     */
    public Vec2d calcSA(Boundd o){

        if (this instanceof Circled) {
            Circled c1 = (Circled)this;
            if (o instanceof Circled) {
                Circled c2 = (Circled)o;
                Vec2d d = c1.center.diff(c2.center);
                d = d.retract(c1.radius + c2.radius);
                if (d.magnitude() < 0) return d;
                else return Vec2d.zeroVec();
            } else {
                Ray2d[] o2s = o.getSides();
                Vec2d[] o2v = o.getVerticies();
                // fisrt check ditnce from center to each side ray
                
                Vec2d lIntersection = Vec2d.nInfVec();

                for (Ray2d cR : o2s) {
                    Vec2d intersection = cR.shortestPath(c1.center);
                    if (intersection.magnitude() > lIntersection.magnitude()) {
                        lIntersection = intersection;
                    }
                }
                
                // if the center is in out of the bounds check for distances to vertices
                // find shorest didsnce and keep track of the two sides connected to that points
                // check if both angles obtuse using dot product
                if (lIntersection.magnitude() > 0) {
                    Vec2d minLength = Vec2d.infVec();
                    int index = 0;
                    for (int i = 0; i < o2v.length; i++) {
                        Vec2d d = c1.center.diff(o2v[i]);
                        if (d.magnitude() < minLength.magnitude()) {
                            minLength = d;
                            index = i;
                        }
                    }
                    Vec2d rs = o2s[index].direction();
                    Vec2d ls = o2s[index - 1 < 0 ? o2v.length - 1 : index - 1].direction().scale(-1.0);
                    if (minLength.dot(rs) < 0 && minLength.dot(ls) < 0) {
                        lIntersection = minLength;
                    }
                }
                lIntersection = lIntersection.retract(c1.radius);
                if (lIntersection.magnitude() < 0) {
                    return lIntersection;
                }else{
                    return Vec2d.zeroVec();
                }
            }
        } else {
            Ray2d[] o1s = getSides();
            Vec2d[] o1v = getVerticies();
            if (o instanceof Circled) {
                Circled c2 = (Circled)o;
                
                // fisrt check ditnce from center to each side ray
                
                Vec2d lIntersection = Vec2d.nInfVec();

                for (Ray2d cR : o1s) {
                    Vec2d intersection = cR.shortestPath(c2.center);
                    if (intersection.magnitude() > lIntersection.magnitude()) {
                        lIntersection = intersection;
                    }
                }
                
                // if the center is in out of the bounds check for distances to vertices
                // find shorest didsnce and keep track of the two sides connected to that points
                // check if both angles obtuse using dot product
                if (lIntersection.magnitude() > 0) {
                    Vec2d minLength = Vec2d.infVec();
                    int index = 0;
                    for (int i = 0; i < o1v.length; i++) {
                        Vec2d d = c2.center.diff(o1v[i]);
                        if (d.magnitude() < minLength.magnitude()) {
                            minLength = d;
                            index = i;
                        }
                    }
                    Vec2d rs = o1s[index].direction();
                    Vec2d ls = o1s[index - 1 < 0 ? o1v.length - 1 : index - 1].direction().scale(-1.0);
                    if (minLength.dot(rs) < 0 && minLength.dot(ls) < 0) {
                        lIntersection = minLength;
                    }
                }
                lIntersection = lIntersection.retract(c2.radius);
                if (lIntersection.magnitude() < 0) {
                    return lIntersection;
                }else{
                    return Vec2d.zeroVec();
                }
            } else {
                Ray2d[] o2s = o.getSides();
                Vec2d[] o2v = o.getVerticies();
                Vec2d lIntersection = Vec2d.nInfVec();

                for (Ray2d cR : o1s) {
                    Vec2d mIntersection = Vec2d.infVec();
                    for (Vec2d cV : o2v) {
                        Vec2d cIntersection;
                        cIntersection = cR.shortestPath(cV);
                        if (cIntersection.magnitude() > 0) {
                            continue;
                        }
                        if (cIntersection.magnitude() < mIntersection.magnitude()) {
                            mIntersection = cIntersection;
                        }
                    }
                    if (mIntersection.magnitude() > 0) {
                        return Vec2d.zeroVec();
                    }
                    if (mIntersection.magnitude() > lIntersection.magnitude()) {
                        lIntersection = mIntersection;
                    }
                }
                for (Ray2d cR : o2s) {
                    Vec2d mIntersection = Vec2d.infVec();
                    for (Vec2d cV : o1v) {
                        Vec2d cIntersection;
                        cIntersection = cR.shortestPath(cV);
                        if (cIntersection.magnitude() < mIntersection.magnitude()) {
                            mIntersection = cIntersection;
                        }
                    }
                    if (mIntersection.magnitude() > 0) {
                        return Vec2d.zeroVec();
                    }
                    if (mIntersection.magnitude() > lIntersection.magnitude()) {
                        lIntersection = mIntersection;
                    }
                }
                return lIntersection;
            }
        }
    }
    
    /*
    
    0 index vetex
        | 0 index side
        ↓ ↓
        *---*
        |   |
        *---*
    
    Continues ↻
    
    */
    
    /**
     * Gives the sides of this bound.
     * @return The sides of this bounds.
     */
    public abstract Ray2d[] getSides();
    /**
     * Gives the vertices of this bound.
     * @return The vertices of this bound.
     */
    public abstract Vec2d[] getVerticies();
    
    /**
     * Returns if a point is contained by this bound.
     * @param p The point
     * @return Whether it is contained
     */
    public abstract boolean inBounds(Vec2d p);
    
    /**
     * Get an AABB that surrounds this bound.
     * @return The AABB
     */
    public abstract AABBd getAABB();
    
}
