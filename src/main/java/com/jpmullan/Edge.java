package com.jpmullan;

/**
 * Created by jmull_000 on 12/31/2016.
 */
public class Edge {
    Point start;
    Point end;

    Edge(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    Double distance() {
        return Math.sqrt(
            (start.x - end.x) * (start.x - end.x) + (start.y - end.y) * (start.y - end.y)
        );
    }
}
