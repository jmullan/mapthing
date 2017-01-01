package com.jpmullan;

/**
 * Created by jmull_000 on 12/31/2016.
 */
public class Diagram {
    Integer pointCount;
    Point[] points;
    Edge[] edges;
    Triangle[] triangles;

    void Diagram(int pointCount) {
        this.pointCount = pointCount;
        this.points = new Point[pointCount];
    }

}
