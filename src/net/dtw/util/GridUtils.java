
package net.dtw.util;

import net.dtw.math.*;

/**
 *
 * @author Daniel
 */
public class GridUtils {
    
    private GridUtils(){}
    
    public static <T> T getItem(T[][] grid, Vec2i p) {
        return grid[p.x][p.y];
    }
    
    public static <T> void setItem(T[][] grid, Vec2i p, T item) {
        grid[p.x][p.y] = item;
    }
    
    public static int getItem(int[][] grid, Vec2i p) {
        return grid[p.x][p.y];
    }
    
    public static void setItem(int[][] grid, Vec2i p, int item) {
        grid[p.x][p.y] = item;
    }
    
    public static void orItem(int[][] grid, Vec2i p, int item) {
        grid[p.x][p.y] |= item;
    }
    
}
