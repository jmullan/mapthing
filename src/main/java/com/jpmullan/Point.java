package com.jpmullan;

/**
 * Created by jmull_000 on 12/31/2016.
 */
public class Point {
    public Double x;
    public Double y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    double distance(Point other) {
        double dx = Math.pow(x - other.x, 2);
        double dy = Math.pow(y - other.y, 2);
        return Math.sqrt(dx + dy);
    }
}
