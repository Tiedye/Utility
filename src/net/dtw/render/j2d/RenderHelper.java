/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dtw.render.j2d;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import net.dtw.math.Ray2d;
import net.dtw.math.Vec2d;

/**
 *
 * @author Tiedye <tiedye1@hotmail.com>
 */
public class RenderHelper {
    
    private RenderHelper(){}
    
    public static void DrawArrow(Ray2d ray, Graphics2D g){
        DrawArrow(ray, g, 5.0, 0.0f);
    }
    public static void DrawArrow(Ray2d ray, Graphics2D g, double arrowSize){
        DrawArrow(ray, g, arrowSize, 0.0f);
    }
    public static void DrawArrow(Ray2d ray, Graphics2D g, double arrowSize, float strokeWidth) {
        Graphics2D gl = (Graphics2D)g.create();
        Path2D path = new Path2D.Double();
        Vec2d d = ray.direction();
        gl.translate(ray.a.x, ray.a.y);
        gl.rotate(Math.atan2(d.y, d.x));
        double m = d.magnitude();
        double s = 0;
        if (strokeWidth != 0.0f) {
            gl.setStroke(new BasicStroke(strokeWidth));
            s = strokeWidth;
        }
        path.moveTo(0.0, 0.0);
        path.lineTo(Math.max(m-s, 0.0), 0.0);
        if (!ray.a.equals(ray.b)) {
            path.moveTo(m-arrowSize, arrowSize);
            path.lineTo(m, 0.0);
            path.lineTo(m-arrowSize, -arrowSize);
        }
        gl.draw(path);
    }
    
    public static void DrawLine(Ray2d ray, Graphics2D g) {
        g.draw(new Line2D.Double(ray.a.x, ray.a.y, ray.b.x, ray.b.y));
    }
    
    public static void DrawPolygon(Vec2d[] vertices, Graphics2D g) {
        if(vertices.length==0)return;
        Path2D path = new Path2D.Double();
        path.moveTo(vertices[0].x, vertices[0].y);
        for(int i = 1; i < vertices.length; i++){
            path.lineTo(vertices[i].x, vertices[i].y);
        }
        path.closePath();
        g.draw(path);
    }

}
