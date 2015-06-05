
package net.dtw.math;

/**
 * Represents a convex bound.
 * @author Daniel <tiedye1@hotmail.com>
 */
public abstract class Boundf {
    
    /**
     * Calculates the separating axis between two bounding convex bounding areas.
     * @param o Other bounding area.
     * @return 
     */
    public Vec2f calcSA(Boundf o){

        if (this instanceof Circlef) {
            Circlef c1 = (Circlef)this;
            if (o instanceof Circlef) {
                Circlef c2 = (Circlef)o;
                Vec2f d = c1.center.diff(c2.center);
                d = d.retract(c1.radius + c2.radius);
                if (d.magnitude() < 0) return d;
                else return Vec2f.zeroVec();
            } else {
                Ray2f[] o2s = o.getSides();
                Vec2f[] o2v = o.getVerticies();
                // fisrt check ditnce from center to each side ray
                
                Vec2f lIntersection = Vec2f.nInfVec();

                for (Ray2f cR : o2s) {
                    Vec2f intersection = cR.shortestPath(c1.center);
                    if (intersection.magnitude() > lIntersection.magnitude()) {
                        lIntersection = intersection;
                    }
                }
                
                // if the center is in out of the bounds check for distances to vertices
                // find shorest didsnce and keep track of the two sides connected to that points
                // check if both angles obtuse using dot product
                if (lIntersection.magnitude() > 0) {
                    Vec2f minLength = Vec2f.infVec();
                    int index = 0;
                    for (int i = 0; i < o2v.length; i++) {
                        Vec2f d = c1.center.diff(o2v[i]);
                        if (d.magnitude() < minLength.magnitude()) {
                            minLength = d;
                            index = i;
                        }
                    }
                    Vec2f rs = o2s[index].direction();
                    Vec2f ls = o2s[index - 1 < 0 ? o2v.length - 1 : index - 1].direction().scale(-1.0f);
                    if (minLength.dot(rs) < 0 && minLength.dot(rs) < 0) {
                        lIntersection = minLength;
                    }
                }
                lIntersection = lIntersection.retract(c1.radius);
                if (lIntersection.magnitude() < 0) {
                    return lIntersection;
                }else{
                    return Vec2f.zeroVec();
                }
            }
        } else {
            Ray2f[] o1s = getSides();
            Vec2f[] o1v = getVerticies();
            if (o instanceof Circlef) {
                Circlef c2 = (Circlef)o;
                
                // fisrt check ditnce from center to each side ray
                
                Vec2f lIntersection = Vec2f.nInfVec();

                for (Ray2f cR : o1s) {
                    Vec2f intersection = cR.shortestPath(c2.center);
                    if (intersection.magnitude() > lIntersection.magnitude()) {
                        lIntersection = intersection;
                    }
                }
                
                // if the center is in out of the bounds check for distances to vertices
                // find shorest didsnce and keep track of the two sides connected to that points
                // check if both angles obtuse using dot product
                if (lIntersection.magnitude() > 0) {
                    Vec2f minLength = Vec2f.infVec();
                    int index = 0;
                    for (int i = 0; i < o1v.length; i++) {
                        Vec2f d = c2.center.diff(o1v[i]);
                        if (d.magnitude() < minLength.magnitude()) {
                            minLength = d;
                            index = i;
                        }
                    }
                    Vec2f rs = o1s[index].direction();
                    Vec2f ls = o1s[index - 1 < 0 ? o1v.length - 1 : index - 1].direction().scale(-1.0f);
                    if (minLength.dot(rs) < 0 && minLength.dot(rs) < 0) {
                        lIntersection = minLength;
                    }
                }
                lIntersection = lIntersection.retract(c2.radius);
                if (lIntersection.magnitude() < 0) {
                    return lIntersection;
                }else{
                    return Vec2f.zeroVec();
                }
            } else {
                Ray2f[] o2s = o.getSides();
                Vec2f[] o2v = o.getVerticies();
                Vec2f lIntersection = Vec2f.nInfVec();

                for (Ray2f cR : o1s) {
                    Vec2f mIntersection = Vec2f.infVec();
                    for (Vec2f cV : o2v) {
                        Vec2f cIntersection;
                        cIntersection = cR.shortestPath(cV);
                        if (cIntersection.magnitude() > 0) {
                            continue;
                        }
                        if (cIntersection.magnitude() < mIntersection.magnitude()) {
                            mIntersection = cIntersection;
                        }
                    }
                    if (mIntersection.magnitude() > 0) {
                        return Vec2f.zeroVec();
                    }
                    if (mIntersection.magnitude() > lIntersection.magnitude()) {
                        lIntersection = mIntersection;
                    }
                }
                for (Ray2f cR : o2s) {
                    Vec2f mIntersection = Vec2f.infVec();
                    for (Vec2f cV : o1v) {
                        Vec2f cIntersection;
                        cIntersection = cR.shortestPath(cV);
                        if (cIntersection.magnitude() < mIntersection.magnitude()) {
                            mIntersection = cIntersection;
                        }
                    }
                    if (mIntersection.magnitude() > 0) {
                        return Vec2f.zeroVec();
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
    public abstract Ray2f[] getSides();
    /**
     * Gives the vertices of this bound.
     * @return The vertices of this bound.
     */
    public abstract Vec2f[] getVerticies();
    
    /**
     * Returns if a point is contained by this bound.
     * @param p The point
     * @return Whether it is contained
     */
    public abstract boolean inBounds(Vec2f p);
    
    /**
     * Get an AABB that surrounds this bound.
     * @return The AABB
     */
    public abstract AABBf getAABB();
    
}
