
package net.dtw.math;

import static java.lang.Math.*;

/**
 * Represents a 3 by 3 matrix.
 * @author Daniel <tiedye1@hotmail.com>
 */
public class Mat33f {

    public float a1;
    public float a2;
    public float a3;
    public float b1;
    public float b2;
    public float b3;
    public float c1;
    public float c2;
    public float c3;

    public Mat33f(float a1, float a2, float a3, float b1, float b2, float b3, float c1, float c2, float c3) {
        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
        this.b1 = b1;
        this.b2 = b2;
        this.b3 = b3;
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
    }
    public Mat33f(Vec3f a, float b1, float b2, float b3, float c1, float c2, float c3) {
        this(a.x, a.y, a.z, b1, b2, b3, c1, c2, c3);
    }
    public Mat33f(float a1, float a2, float a3, Vec3f b, float c1, float c2, float c3) {
        this(a1, a2, a3, b.x, b.y, b.z, c1, c2, c3);
    }
    public Mat33f(float a1, float a2, float a3, float b1, float b2, float b3, Vec3f c) {
        this(a1, a2, a3, b1, b2, b3, c.x, c.y, c.z);
    }
    public Mat33f(Vec3f a, Vec3f b, float c1, float c2, float c3) {
        this(a.x, a.y, a.z, b.x, b.y, b.z, c1, c2, c3);
    }
    public Mat33f(Vec3f a, float b1, float b2, float b3, Vec3f c) {
        this(a.x, a.y, a.z, b1, b2, b3, c.x, c.y, c.z);
    }
    public Mat33f(float a1, float a2, float a3, Vec3f b, Vec3f c) {
        this(a1, a2, a3, b.x, b.y, b.z, c.x, c.y, c.z);
    }
    public Mat33f(Vec3f a, Vec3f b, Vec3f c) {
        this(a.x, a.y, a.z, b.x, b.y, b.z, c.x, c.y, c.z);
    }

    public Vec3f getRowA() {
        return new Vec3f(a1, a2, a3);
    }
    public Vec3f getRowB() {
        return new Vec3f(b1, b2, b3);
    }
    public Vec3f getRowC() {
        return new Vec3f(c1, c2, c3);
    }
    public Vec3f getCol1() {
        return new Vec3f(a1, b1, c1);
    }
    public Vec3f getCol2() {
        return new Vec3f(a2, b2, c2);
    }
    public Vec3f getCol3() {
        return new Vec3f(a3, b3, c3);
    }

    public Mat33f multiply(Mat33f m) {
        Vec3f r_A = m.getRowA();
        Vec3f r_B = m.getRowB();
        Vec3f r_C = m.getRowC();
        Vec3f c_1 = getCol1();
        Vec3f c_2 = getCol2();
        Vec3f c_3 = getCol3();
        return new Mat33f(r_A.dot(c_1), r_A.dot(c_2), r_A.dot(c_3), r_B.dot(c_1), r_B.dot(c_2), r_B.dot(c_3), r_C.dot(c_1), r_C.dot(c_2), r_C.dot(c_3));
    }
    public Vec3f multiply(Vec3f v) {
        Vec3f r_A = getRowA();
        Vec3f r_B = getRowB();
        Vec3f r_C = getRowC();
        return new Vec3f(r_A.dot(v), r_B.dot(v), r_C.dot(v));
    }
    
    public Mat33f add(Mat33f m) {
        return new Mat33f(a1 + m.a1, a2 + m.a2, a3 + m.a3, b1 + m.b1, b2 + m.b2, b3 + m.b3, c1 + m.c1, c2 + m.c2, c3 + m.c3);
    }
   
    public Mat33f transpose(){
        return new Mat33f(a1, b1, c1, a2, b2, c2, a3, b3, c3);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Float.floatToIntBits(this.a1);
        hash = 23 * hash + Float.floatToIntBits(this.a2);
        hash = 23 * hash + Float.floatToIntBits(this.a3);
        hash = 23 * hash + Float.floatToIntBits(this.b1);
        hash = 23 * hash + Float.floatToIntBits(this.b2);
        hash = 23 * hash + Float.floatToIntBits(this.b3);
        hash = 23 * hash + Float.floatToIntBits(this.c1);
        hash = 23 * hash + Float.floatToIntBits(this.c2);
        hash = 23 * hash + Float.floatToIntBits(this.c3);
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
        final Mat33f other = (Mat33f) obj;
        if (Float.floatToIntBits(this.a1) != Float.floatToIntBits(other.a1)) {
            return false;
        }
        if (Float.floatToIntBits(this.a2) != Float.floatToIntBits(other.a2)) {
            return false;
        }
        if (Float.floatToIntBits(this.a3) != Float.floatToIntBits(other.a3)) {
            return false;
        }
        if (Float.floatToIntBits(this.b1) != Float.floatToIntBits(other.b1)) {
            return false;
        }
        if (Float.floatToIntBits(this.b2) != Float.floatToIntBits(other.b2)) {
            return false;
        }
        if (Float.floatToIntBits(this.b3) != Float.floatToIntBits(other.b3)) {
            return false;
        }
        if (Float.floatToIntBits(this.c1) != Float.floatToIntBits(other.c1)) {
            return false;
        }
        if (Float.floatToIntBits(this.c2) != Float.floatToIntBits(other.c2)) {
            return false;
        }
        if (Float.floatToIntBits(this.c3) != Float.floatToIntBits(other.c3)) {
            return false;
        }
        return true;
    }


    
    @Override
    public String toString() {
        return String.format("------------------------------\n|%+8.3f, %+8.3f, %+8.3f|\n|%+8.3f, %+8.3f, %+8.3f|\n|%+8.3f, %+8.3f, %+8.3f|\n------------------------------", a1, a2, a3, b1, b2, b3, c1, c2, c3);
    }
    
    public Mat33f copy(){
        return new Mat33f(a1, a2, a3, b1, b2, b3, c1, c2, c3);
    }
    
    
    public static Mat33f identity() {
        return new Mat33f(1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f);
    }
    public static Mat33f rotation(float r){
        float sinr = (float)sin(r);
        float cosr = (float)cos(r);
        return new Mat33f(cosr, -sinr, 0.0f, sinr, cosr, 0.0f, 0.0f, 0.0f, 1.0f);
    }
    public static Mat33f translation(float x, float y){
        return new Mat33f(1.0f, 0.0f, x, 0.0f, 1.0f, y, 0.0f, 0.0f, 1.0f);
    }
    public static Mat33f translation(Vec2f v){
        return new Mat33f(1.0f, 0.0f, v.x, 0.0f, 1.0f, v.y, 0.0f, 0.0f, 1.0f);
    }
    public static Mat33f scale(float s){
        return new Mat33f(s, 0.0f, 0.0f, 0.0f, s, 0.0f, 0.0f, 0.0f, 1.0f);
    }
    public static Mat33f scale(float x, float y){
        return new Mat33f(x, 0.0f, 0.0f, 0.0f, y, 0.0f, 0.0f, 0.0f, 1.0f);
    }
    public static Mat33f scale(Vec2f s){
        return new Mat33f(s.x, 0.0f, 0.0f, 0.0f, s.y, 0.0f, 0.0f, 0.0f, 1.0f);
    }
    
}
