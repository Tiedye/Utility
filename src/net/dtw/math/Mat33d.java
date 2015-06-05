
package net.dtw.math;

import static java.lang.Math.*;

/**
 * Represents a 3 by 3 matrix.
 * @author Daniel <tiedye1@hotmail.com>
 */
public class Mat33d {

    public double a1;
    public double a2;
    public double a3;
    public double b1;
    public double b2;
    public double b3;
    public double c1;
    public double c2;
    public double c3;

    public Mat33d(double a1, double a2, double a3, double b1, double b2, double b3, double c1, double c2, double c3) {
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
    public Mat33d(Vec3d a, double b1, double b2, double b3, double c1, double c2, double c3) {
        this(a.x, a.y, a.z, b1, b2, b3, c1, c2, c3);
    }
    public Mat33d(double a1, double a2, double a3, Vec3d b, double c1, double c2, double c3) {
        this(a1, a2, a3, b.x, b.y, b.z, c1, c2, c3);
    }
    public Mat33d(double a1, double a2, double a3, double b1, double b2, double b3, Vec3d c) {
        this(a1, a2, a3, b1, b2, b3, c.x, c.y, c.z);
    }
    public Mat33d(Vec3d a, Vec3d b, double c1, double c2, double c3) {
        this(a.x, a.y, a.z, b.x, b.y, b.z, c1, c2, c3);
    }
    public Mat33d(Vec3d a, double b1, double b2, double b3, Vec3d c) {
        this(a.x, a.y, a.z, b1, b2, b3, c.x, c.y, c.z);
    }
    public Mat33d(double a1, double a2, double a3, Vec3d b, Vec3d c) {
        this(a1, a2, a3, b.x, b.y, b.z, c.x, c.y, c.z);
    }
    public Mat33d(Vec3d a, Vec3d b, Vec3d c) {
        this(a.x, a.y, a.z, b.x, b.y, b.z, c.x, c.y, c.z);
    }

    public Vec3d getRowA() {
        return new Vec3d(a1, a2, a3);
    }
    public Vec3d getRowB() {
        return new Vec3d(b1, b2, b3);
    }
    public Vec3d getRowC() {
        return new Vec3d(c1, c2, c3);
    }
    public Vec3d getCol1() {
        return new Vec3d(a1, b1, c1);
    }
    public Vec3d getCol2() {
        return new Vec3d(a2, b2, c2);
    }
    public Vec3d getCol3() {
        return new Vec3d(a3, b3, c3);
    }

    public Mat33d multiply(Mat33d m) {
        Vec3d r_A = m.getRowA();
        Vec3d r_B = m.getRowB();
        Vec3d r_C = m.getRowC();
        Vec3d c_1 = getCol1();
        Vec3d c_2 = getCol2();
        Vec3d c_3 = getCol3();
        return new Mat33d(r_A.dot(c_1), r_A.dot(c_2), r_A.dot(c_3), r_B.dot(c_1), r_B.dot(c_2), r_B.dot(c_3), r_C.dot(c_1), r_C.dot(c_2), r_C.dot(c_3));
    }
    public Vec3d multiply(Vec3d v) {
        Vec3d r_A = getRowA();
        Vec3d r_B = getRowB();
        Vec3d r_C = getRowC();
        return new Vec3d(r_A.dot(v), r_B.dot(v), r_C.dot(v));
    }
    
    public Mat33d add(Mat33d m) {
        return new Mat33d(a1 + m.a1, a2 + m.a2, a3 + m.a3, b1 + m.b1, b2 + m.b2, b3 + m.b3, c1 + m.c1, c2 + m.c2, c3 + m.c3);
    }
   
    public Mat33d transpose(){
        return new Mat33d(a1, b1, c1, a2, b2, c2, a3, b3, c3);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.a1) ^ (Double.doubleToLongBits(this.a1) >>> 32));
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.a2) ^ (Double.doubleToLongBits(this.a2) >>> 32));
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.a3) ^ (Double.doubleToLongBits(this.a3) >>> 32));
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.b1) ^ (Double.doubleToLongBits(this.b1) >>> 32));
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.b2) ^ (Double.doubleToLongBits(this.b2) >>> 32));
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.b3) ^ (Double.doubleToLongBits(this.b3) >>> 32));
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.c1) ^ (Double.doubleToLongBits(this.c1) >>> 32));
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.c2) ^ (Double.doubleToLongBits(this.c2) >>> 32));
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.c3) ^ (Double.doubleToLongBits(this.c3) >>> 32));
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
        final Mat33d other = (Mat33d) obj;
        if (Double.doubleToLongBits(this.a1) != Double.doubleToLongBits(other.a1)) {
            return false;
        }
        if (Double.doubleToLongBits(this.a2) != Double.doubleToLongBits(other.a2)) {
            return false;
        }
        if (Double.doubleToLongBits(this.a3) != Double.doubleToLongBits(other.a3)) {
            return false;
        }
        if (Double.doubleToLongBits(this.b1) != Double.doubleToLongBits(other.b1)) {
            return false;
        }
        if (Double.doubleToLongBits(this.b2) != Double.doubleToLongBits(other.b2)) {
            return false;
        }
        if (Double.doubleToLongBits(this.b3) != Double.doubleToLongBits(other.b3)) {
            return false;
        }
        if (Double.doubleToLongBits(this.c1) != Double.doubleToLongBits(other.c1)) {
            return false;
        }
        if (Double.doubleToLongBits(this.c2) != Double.doubleToLongBits(other.c2)) {
            return false;
        }
        if (Double.doubleToLongBits(this.c3) != Double.doubleToLongBits(other.c3)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return String.format("------------------------------\n|%+8.3f, %+8.3f, %+8.3f|\n|%+8.3f, %+8.3f, %+8.3f|\n|%+8.3f, %+8.3f, %+8.3f|\n------------------------------", a1, a2, a3, b1, b2, b3, c1, c2, c3);
    }
    
    public Mat33d copy(){
        return new Mat33d(a1, a2, a3, b1, b2, b3, c1, c2, c3);
    }
    
    
    public static Mat33d identity() {
        return new Mat33d(1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0);
    }
    public static Mat33d rotation(double r){
        double sinr = sin(r);
        double cosr = cos(r);
        return new Mat33d(cosr, -sinr, 0.0, sinr, cosr, 0.0, 0.0, 0.0, 1.0);
    }
    public static Mat33d translation(double x, double y){
        return new Mat33d(1.0, 0.0, x, 0.0, 1.0, y, 0.0, 0.0, 1.0);
    }
    public static Mat33d translation(Vec2d v){
        return new Mat33d(1.0, 0.0, v.x, 0.0, 1.0, v.y, 0.0, 0.0, 1.0);
    }
    public static Mat33d scale(double s){
        return new Mat33d(s, 0.0, 0.0, 0.0, s, 0.0, 0.0, 0.0, 1.0);
    }
    public static Mat33d scale(double x, double y){
        return new Mat33d(x, 0.0, 0.0, 0.0, y, 0.0, 0.0, 0.0, 1.0);
    }
    public static Mat33d scale(Vec2d s){
        return new Mat33d(s.x, 0.0, 0.0, 0.0, s.y, 0.0, 0.0, 0.0, 1.0);
    }
    
}
