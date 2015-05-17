/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dtw.math;

/**
 *
 * @author Daniel
 */
public class AABBf {
    
    private float top;
    private float bottom;
    private float right;
    private float left;
    
    private boolean zero;

    public AABBf(float top, float bottom, float right, float left) {
        if (top < bottom || right < left) zero = true;
        else {
            this.top = top;
            this.bottom = bottom;
            this.right = right;
            this.left = left;
        }
    }
    
    public boolean inBounds(Vec2f p) {
        if (zero) return false;
        return top > p.y && bottom <= p.y && right > p.x && left <= p.x;
    }
    
    public AABBf intersect (AABBf ab) {
        return new AABBf(top < ab.top ? top : ab.top, bottom > ab.bottom ? bottom : ab.bottom, right < ab.right ? right : ab.right, left > ab.left ? left : ab.left);
    }
    
    public AABBf translate(Vec2f v){
        return new AABBf(top + v.y, bottom + v.y, right + v.x, left + v.x);
    }
    
    public AABBf expand(Vec2f v){
        return new AABBf(v.y > 0 ? top + v.y : top, v.y < 0 ? bottom + v.y : bottom, v.x > 0 ? right + v.x : right, v.x < 0 ? left + v.x : left);
    }
    
    public AABBf expand(float top, float bottom, float right, float left){
        return new AABBf(this.top + top, this.bottom + bottom, this.right + right, this.left + left);
    }
    
    public float getWidth(){
        return right - left;
    }
    
    public float getHeight(){
        return top - bottom;
    }
    
    public AABBf copy(){
        return new AABBf(top, bottom, right, left);
    }
    
}
