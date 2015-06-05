
package net.dtw.math;

/**
 * Represents an axis aligned bounding box.
 * @author Daniel <tiedye1@hotmail.com>
 */
public class AABBd extends Boundd{
    
    private double top;
    private double bottom;
    private double right;
    private double left;
    
    private Vec2d center;
    private Vec2d size;
    
    private boolean zero;
    
    // 9 bit field, bits represents the currency of repective fields in order of appearence
    // bit 0 top
    // bit 1 bottom
    // bit 2 right
    // bit 3 left
    // bit 4 center.x
    // bit 5 center.y
    // bit 6 size.x
    // bit 7 size.y
    // bit 8 zero
    private int updateMask;

    /**
     * Create an axis aligned bounding box (AABB).
     * @param top Top side y coordinate
     * @param bottom Bottom side y coordinate
     * @param right Right side x coordinate
     * @param left Left side x coordinate
     * @return 
     */
    public static AABBd newAABB(double top, double bottom, double right, double left){
        return new AABBd(top, bottom, right, left);
    }
    
    private AABBd(double top, double bottom, double right, double left) {
        if (top < bottom || right < left) {
            zero = true;
            this.top = bottom;
            this.bottom = bottom;
            this.right = left;
            this.left = left;
        }
        else {
            this.top = top;
            this.bottom = bottom;
            this.right = right;
            this.left = left;
        }
        updateMask = 0b111100001;
    }
    
    private void updateTop(){
        if ((0b100000000 & updateMask) == 0) {
            if ((0b000001010 & updateMask) == 0b000001010){
                top = center.y + size.y;
            } else if ((0b010000010 & updateMask) == 0b010000010){
                top = bottom + 2 * size.y;
            } else throw new RuntimeException("Invalid AABB state.");
            updateMask &= 0b100000000;
        }
    }
    private void updateBottom(){
        if ((0b010000000 & updateMask) == 0) {
            if ((0b000001010 & updateMask) == 0b000001010){
                bottom = center.y - size.y;
            } else if ((0b100000010 & updateMask) == 0b100000010){
                bottom = top - 2 * size.y;
            } else throw new RuntimeException("Invalid AABB state.");
            updateMask &= 0b010000000;
        }
    }
    private void updateRight(){
        if ((0b001000000 & updateMask) == 0) {
            if ((0b000010100 & updateMask) == 0b000010100){
                right = center.x + size.x;
            } else if ((0b000100100 & updateMask) == 0b000100100){
                right = left + 2 * size.x;
            } else throw new RuntimeException("Invalid AABB state.");
            updateMask &= 0b001000000;
        }
    }
    private void updateLeft(){
        if ((0b000100000 & updateMask) == 0) {
            if ((0b000010100 & updateMask) == 0b000010100){
                left = center.x - size.x;
            } else if ((0b001000100 & updateMask) == 0b001000100){
                left = right - 2 * size.x;
            } else throw new RuntimeException("Invalid AABB state.");
            updateMask &= 0b000100000;
        }
    }
    private void updateCenterX(){
        if ((0b000010000 & updateMask) == 0) {
            if ((0b000100100 & updateMask) == 0b000100100){
                center.x = left + size.x;            
            } else if ((0b001000100 & updateMask) == 0b001000100) {
                center.x = right - size.x;            
            } else if ((0b001100000 & updateMask) == 0b001100000) {
                center.x = (left + right) / 2;
            } else throw new RuntimeException("Invalid AABB state.");
            updateMask &= 0b000010000;
        }
    }
    private void updateCenterY(){
        if ((0b000001000 & updateMask) == 0) {
            if ((0b010000010 & updateMask) == 0b010000010){
                center.y = bottom + size.y;            
            } else if ((0b100000010 & updateMask) == 0b100000010) {
                center.y = top - size.y;            
            } else if ((0b110000000 & updateMask) == 0b110000000) {
                center.y = (bottom + top) / 2;
            } else throw new RuntimeException("Invalid AABB state.");
            updateMask &= 0b000001000;
        }
    }
    private void updateSizeX(){
        if ((0b000000100 & updateMask) == 0) {
            if ((0b001010000 & updateMask) == 0b001010000){
                size.x = center.x - left;
            } else if ((0b000110000 & updateMask) == 0b000110000){
                size.x = right - center.x;
            } else if ((0b001100000 & updateMask) == 0b001100000){
                size.x = (right - left) / 2;
            } else throw new RuntimeException("Invalid AABB state.");
            updateMask &= 0b000000100;
        }
    }
    private void updateSizeY(){
        if ((0b000000010 & updateMask) == 0) {
            if ((0b100001000 & updateMask) == 0b100001000){
                size.y = center.y - bottom;
            } else if ((0b010001000 & updateMask) == 0b010001000){
                size.y = top - center.y;
            } else if ((0b110000000 & updateMask) == 0b110000000){
                size.y = (top - bottom) / 2;
            } else throw new RuntimeException("Invalid AABB state.");
            updateMask &= 0b000000010;
        }
    }
    private void updateZero(){
        if ((0b000000001 & updateMask) == 0) {
            if ((0b000000100 & updateMask) == 0b000000100){
                zero = size.x == 0;
            } else if ((0b000000010 & updateMask) == 0b000000010){
                zero = size.y == 0;
            } else if ((0b110000000 & updateMask) == 0b110000000){
                zero = top == bottom;
            } else if ((0b001100000 & updateMask) == 0b001100000){
                zero = right == left;
            } else throw new RuntimeException("Invalid AABB state.");
            updateMask &= 0b000000001;
        }
    }
    
    private void updateBounds(){
        updateTop();
        updateBottom();
        updateRight();
        updateLeft();
    }
    private void updateCenter(){
        updateCenterX();
        updateCenterY();
    }
    private void updateSize(){
        updateSizeX();
        updateSizeY();
    }
    
    @Override
    public boolean inBounds(Vec2d p) {
        updateZero();
        if (zero) return false;
        updateBounds();
        return top > p.y && bottom <= p.y && right > p.x && left <= p.x;
    }
    @Override
    public Ray2d[] getSides() {
        return new Ray2d[]{Ray2d.newRay(Vec2d.newVec(left, top), Vec2d.newVec(right, top)),
            Ray2d.newRay(Vec2d.newVec(right, top), Vec2d.newVec(right, bottom)),
            Ray2d.newRay(Vec2d.newVec(right, bottom), Vec2d.newVec(left, bottom)),
            Ray2d.newRay(Vec2d.newVec(left, bottom), Vec2d.newVec(left, top))};
    }
    @Override
    public Vec2d[] getVerticies() {
        return new Vec2d[]{Vec2d.newVec(left, top), Vec2d.newVec(right, top), Vec2d.newVec(right, bottom), Vec2d.newVec(left, bottom)};
    }
    
    /**
     * Fast check of the intersection of two bounding boxes.
     * @param other The other box
     * @return Weather they intersect
     */
    public boolean checkInersection(AABBd other){
        updateBounds();
        return bottom < other.top && top > other.bottom && right > other.left && left < other.right;
    }
    
    /**
     * The intersection if this bounding box and the other.
     * @param ab The other box
     * @return The result of the union
     */
    public AABBd intersect (AABBd ab) {
        updateBounds();
        return AABBd.newAABB(top < ab.top ? top : ab.top, bottom > ab.bottom ? bottom : ab.bottom, right < ab.right ? right : ab.right, left > ab.left ? left : ab.left);
    }
    
    /**
     * Translates a box by a given amount.
     * Note: this modifies a box, and does not create a new one 
     * @param a The box to be translated
     * @param v The quantity to translate the box by
     * @return The first parameter, after translation
     */
    public static AABBd translate(AABBd a, Vec2d v){
        a.updateCenter();
        Vec2d c = a.getCenterReference();
        c.x += v.x;
        c.y += v.y;
        a.invalidateBounds();
        return a;
    }
    /**
     * Translates a box by a given vector.
     * @param v The vector translate it by
     * @return The new translated box
     */
    public AABBd translate(Vec2d v){
        updateBounds();
        return AABBd.newAABB(top + v.y, bottom + v.y, right + v.x, left + v.x);
    }
    
    /**
     * Expands a box by a given about, negative values gill expand the box in the other direction.
     * Note: this modifies a box, and does not create a new one 
     * @param a The box to be expanded
     * @param v The vector that represents the expansion
     * @return The first parameter, after expansion
     */
    public static AABBd expand(AABBd a, Vec2d v){
        a.updateBounds();
        a.top = v.y > 0 ? a.top + v.y : a.top;
        a.bottom = v.y < 0 ? a.bottom + v.y : a.bottom;
        a.right = v.x > 0 ? a.right + v.x : a.right;
        a.left = v.x < 0 ? a.left + v.x : a.left;
        return a;
    }
    /**
     * Expands a box by a given about, negative values gill expand the box in the other direction.
     * @param top The amount to expand the top
     * @param bottom The amount to expand the bottom
     * @param right The amount to expand the right
     * @param left The amount to expand the left
     * @return The new expanded box
     */
    public AABBd expand(double top, double bottom, double right, double left){
        updateBounds();
        return AABBd.newAABB(this.top + top, this.bottom + bottom, this.right + right, this.left + left);
    }
    
    /**
     * Gets the width of the box.
     * @return The width
     */
    public double getWidth(){
        updateRight();
        updateLeft();
        return right - left;
    }
    /**
     * Gets the height of the box.
     * @return The width
     */
    public double getHeight(){
        updateTop();
        updateBottom();
        return top - bottom;
    }
    /**
     * Gets the top of the box.
     * @return The width
     */
    public double getTop() {
        updateTop();
        return top;
    }
    /**
     * Gets the bottom of the box.
     * @return The width
     */
    public double getBottom() {
        updateBottom();
        return bottom;
    }
    /**
     * Gets the right of the box.
     * @return The width
     */
    public double getRight() {
        updateRight();
        return right;
    }
    /**
     * Gets the left of the box.
     * @return The width
     */
    public double getLeft() {
        updateLeft();
        return left;
    }

    /**
     * Get a vector that represents the center of the box.
     * @return The center position
     */
    public Vec2d getCenterReference() {
        updateCenter();
        return center;
    }
    /**
     * Get a vector that represents the size of the box.
     * @return The size
     */
    public Vec2d getSizeReference() {
        updateSize();
        return size;
    }
    
    /** 
     * Invalidates the bounds, for use when the coordinates or size have been changed manually.
     */
    public void invalidateBounds(){
        updateMask &= 000011111;
    }
    /** 
     * Invalidates the coordinates and size, for use when the bounds have been changed manually.
     */
    public void invalidateCoords(){
        updateMask &= 111100001;
    }

    /**
     * Get the center coordinates.
     * @return The center coordinate
     */
    public Vec2d getCenter() {
        updateCenter();
        return center.copy();
    }
    /**
     * Get the size of the box.
     * @return The size
     */
    public Vec2d getSize() {
        updateSize();
        return size.copy();
    }

    /**
     * Sets the top side of the box.
     * @param top The top side
     */
    public void setTop(double top) {
        this.top = top;
        updateMask &= 0b111100000;
        if (top < bottom) {
            bottom = top;
            zero = true;
            updateMask |= 0b110000001;
        } else {
            updateMask |= 0b100000000;
        }
    }
    /**
     * Sets the bottom side of the box.
     * @param bottom The bottom side
     */
    public void setBottom(double bottom) {
        this.bottom = bottom;
        updateMask &= 0b111100000;
        if (bottom > top) {
            top = bottom;
            zero = true;
            updateMask |= 0b110000001;
        } else {
            updateMask |= 0b010000000;
        }
    }
    /**
     * Sets the right side of the box.
     * @param right The right side
     */
    public void setRight(double right) {
        this.right = right;
        updateMask &= 0b111100000;
        if (right < left) {
            left = right;
            zero = true;
            updateMask |= 0b001100001;
        } else {
            updateMask |= 0b001000000;
        }
    }
    /**
     * Sets the left side of the box.
     * @param left The left side
     */
    public void setLeft(double left) {
        this.left = left;
        updateMask &= 0b111100000;
        if (left > right) {
            right = left;
            zero = true;
            updateMask |= 0b001100001;
        } else {
            updateMask |= 0b000100000;
        }
    }
    
    /**
     * Sets the center coordinate of the box.
     * @param center The center coordinate
     */
    public void setCenter(Vec2d center) {
        this.center = center.copy();
        updateMask &= 0b000011111;
    }
    /**
     * Sets the size vector of the box.
     * @param size sThe size vector
     */
    public void setSize(Vec2d size) {
        this.size = size.copy();
        updateMask &= 0b000011111;
    }

    @Override
    public int hashCode() {
        updateBounds();
        int hash = 5;
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.top) ^ (Double.doubleToLongBits(this.top) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.bottom) ^ (Double.doubleToLongBits(this.bottom) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.right) ^ (Double.doubleToLongBits(this.right) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.left) ^ (Double.doubleToLongBits(this.left) >>> 32));
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
        updateBounds();
        final AABBd other = (AABBd) obj;
        if (Double.doubleToLongBits(this.top) != Double.doubleToLongBits(other.top)) {
            return false;
        }
        if (Double.doubleToLongBits(this.bottom) != Double.doubleToLongBits(other.bottom)) {
            return false;
        }
        if (Double.doubleToLongBits(this.right) != Double.doubleToLongBits(other.right)) {
            return false;
        }
        if (Double.doubleToLongBits(this.left) != Double.doubleToLongBits(other.left)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        updateBounds();
        return "AABBd:{" + "top=" + top + ", bottom=" + bottom + ", right=" + right + ", left=" + left + '}';
    }
    
    /**
     * Creates a copy of the AABB, based off of bounds of the box.
     * @return 
     */
    public AABBd copy(){
        updateBounds();
        return AABBd.newAABB(top, bottom, right, left);
    }
    
    @Override
    public AABBd getAABB(){
        return this;
    }
    
}
