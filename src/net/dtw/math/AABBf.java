
package net.dtw.math;

/**
 * Represents an axis aligned bounding box.
 * @author Daniel <tiedye1@hotmail.com>
 */
public class AABBf extends Boundsf{
    
    private float top;
    private float bottom;
    private float right;
    private float left;
    
    private Vec2f center;
    private Vec2f size;
    
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

    public AABBf(float top, float bottom, float right, float left) {
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
    public boolean inBounds(Vec2f p) {
        updateZero();
        if (zero) return false;
        updateBounds();
        return top > p.y && bottom <= p.y && right > p.x && left <= p.x;
    }
    @Override
    public Ray2f[] getSides() {
        return new Ray2f[]{new Ray2f(new Vec2f(top, left), new Vec2f(top, right)),
            new Ray2f(new Vec2f(top, right), new Vec2f(bottom, right)),
            new Ray2f(new Vec2f(bottom, right), new Vec2f(bottom, left)),
            new Ray2f(new Vec2f(bottom, left), new Vec2f(top, left))};
    }
    @Override
    public Vec2f[] getPoints() {
        return new Vec2f[]{new Vec2f(top, left), new Vec2f(top, right), new Vec2f(bottom, right), new Vec2f(bottom, left)};
    }
    @Override
    public float[] getRadi() {
        return new float[]{0, 0, 0, 0};
    }
    
    public boolean checkInersection(AABBf other){
        return bottom < other.top && top > other.bottom && right > other.left && left < other.right;
    }
    
    public AABBf intersect (AABBf ab) {
        updateBounds();
        return new AABBf(top < ab.top ? top : ab.top, bottom > ab.bottom ? bottom : ab.bottom, right < ab.right ? right : ab.right, left > ab.left ? left : ab.left);
    }
    
    public static AABBf translate(AABBf a, Vec2f v){
        a.updateCenter();
        Vec2f c = a.getCenterReference();
        c.x += v.x;
        c.y += v.y;
        a.invalidateBounds();
        return a;
    }
    public AABBf translate(Vec2f v){
        updateBounds();
        return new AABBf(top + v.y, bottom + v.y, right + v.x, left + v.x);
    }
    
    public static AABBf expand(AABBf a, Vec2f v){
        a.updateBounds();
        a.top = v.y > 0 ? a.top + v.y : a.top;
        a.bottom = v.y < 0 ? a.bottom + v.y : a.bottom;
        a.right = v.x > 0 ? a.right + v.x : a.right;
        a.left = v.x < 0 ? a.left + v.x : a.left;
        return a;
    }
    public AABBf expand(float top, float bottom, float right, float left){
        updateBounds();
        return new AABBf(this.top + top, this.bottom + bottom, this.right + right, this.left + left);
    }
    
    public float getWidth(){
        updateRight();
        updateLeft();
        return right - left;
    }
    public float getHeight(){
        updateTop();
        updateBottom();
        return top - bottom;
    }
    public float getTop() {
        updateTop();
        return top;
    }
    public float getBottom() {
        updateBottom();
        return bottom;
    }
    public float getRight() {
        updateRight();
        return right;
    }
    public float getLeft() {
        updateLeft();
        return left;
    }

    public Vec2f getCenterReference() {
        updateCenter();
        return center;
    }
    public Vec2f getSizeReference() {
        updateSize();
        return size;
    }
    
    public void invalidateBounds(){
        updateMask &= 000011111;
    }
    public void invalidateCoords(){
        updateMask &= 111100001;
    }

    public Vec2f getCenter() {
        updateCenter();
        return center.copy();
    }
    public Vec2f getSize() {
        updateSize();
        return size.copy();
    }

    public void setTop(float top) {
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
    public void setBottom(float bottom) {
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
    public void setRight(float right) {
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
    public void setLeft(float left) {
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
    
    public void setCenter(Vec2f center) {
        this.center = center.copy();
        updateMask &= 0b000011111;
    }
    public void setSize(Vec2f size) {
        this.size = size.copy();
        updateMask &= 0b000011111;
    }

    @Override
    public int hashCode() {
        updateBounds();
        int hash = 5;
        hash = 53 * hash + Float.floatToIntBits(this.top);
        hash = 53 * hash + Float.floatToIntBits(this.bottom);
        hash = 53 * hash + Float.floatToIntBits(this.right);
        hash = 53 * hash + Float.floatToIntBits(this.left);
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
        final AABBf other = (AABBf) obj;
        if (Float.floatToIntBits(this.top) != Float.floatToIntBits(other.top)) {
            return false;
        }
        if (Float.floatToIntBits(this.bottom) != Float.floatToIntBits(other.bottom)) {
            return false;
        }
        if (Float.floatToIntBits(this.right) != Float.floatToIntBits(other.right)) {
            return false;
        }
        if (Float.floatToIntBits(this.left) != Float.floatToIntBits(other.left)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        updateBounds();
        return "AABBf:{" + "top=" + top + ", bottom=" + bottom + ", right=" + right + ", left=" + left + '}';
    }
    
    public AABBf copy(){
        return new AABBf(top, bottom, right, left);
    }

    @Override
    public AABBf getAABB() {
        return this;
    }
    
}
