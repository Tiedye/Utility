/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dtw.render.j2d;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import net.dtw.math.Ray2d;
import net.dtw.math.Vec2d;

/**
 *
 * @author Tiedye <tiedye1@hotmail.com>
 */
public class RenderHelper {
    
    private RenderHelper(){}
    
    public static void DrawArrow(Ray2d ray, Graphics2D g) {
        Graphics2D gl = (Graphics2D)g.create();
        //gl.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1.0f, new float[]{3.0f, 3.0f}, 0.0f));
        Vec2d d = ray.direction();
        gl.translate(ray.a.x, ray.a.y);
        gl.rotate(Math.atan2(d.y, d.x));
        int m = (int)d.magnitude();
        gl.drawLine(0, 0, m, 0);
        if (ray.a.equals(ray.b)) return;
        //gl.setStroke(new BasicStroke(2.0f));
        gl.drawLine(m, 0, m-5, 5);
        gl.drawLine(m, 0, m-5, -5);
    }
    
}
