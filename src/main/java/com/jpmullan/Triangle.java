package com.jpmullan;

/**
 * Created by jmull_000 on 12/31/2016.
 */
public class Triangle {
    static private Double EPSILON = 0.0001;

    Edge[] edges;
    Point[] points;
    Double minX = Double.MAX_VALUE;
    Double maxX = Double.MIN_VALUE;
    Double minY = Double.MAX_VALUE;
    Double maxY = Double.MIN_VALUE;

    public Triangle(Point a, Point b, Point c) {
        edges = new Edge[3];

        points = new Point[3];
        points[0] = a;

        if (inside(a, b, c)) {
            points[1] = b;
            points[2] = c;
            edges[0] = new Edge(a, b);
            edges[1] = new Edge(b, c);
            edges[2] = new Edge(c, a);
        } else {
            points[1] = c;
            points[2] = b;
            edges[0] = new Edge(a, c);
            edges[1] = new Edge(c, b);
            edges[2] = new Edge(b, a);
        }

        int i;
        for (i = 0; i < 3; i++) {
            if (points[i].x < minX) {
                minX = points[i].x;
            }
            if (points[i].x > maxX) {
                maxX = points[i].x;
            }
            if (points[i].y < minY) {
                minY = points[i].y;
            }
            if (points[i].y > maxY) {
                maxY = points[i].y;
            }
        }

        Point center = new Point((minX + maxX) / 2, (minY + maxY) / 2);
        assert contains(center);
    }

    boolean contains(Point p) {
        if (!inBoundingBox(p)) {
            return false;
        }
        if (definitelyContains(p)) {
            return true;
        }
        return onFuzzyEdge(p);
    }

    /**
     * Avoid complicated maths
     * @param p
     * @return
     */
    private boolean inBoundingBox(Point p) {
        if (p.x < minX || p.x > maxX || p.y < minY || p.y > maxY) {
            return false;
        }
        return true;
    }

    boolean inside(Point start, Point end, Point c) {
        //  (y2 - y1) * (x - x1) + (-x2 + x1) * (y - y1);
        double side = ((end.y - start.y) * (c.x - start.x) + (start.x - end.x) * (c.y - start.y));
        return side >= 0;
    }

    private boolean definitelyContains(Point p) {
        return (
            inside(points[0], points[1], p) &&
            inside(points[1], points[2], p) &&
            inside(points[2], points[0], p)
        );
    }

    private double distanceToSegment(Point start, Point end, Point p) {
        double dx = end.x - start.x;
        double dy = end.y - start.y;

        double psx = p.x - start.x;
        double psy = p.y - start.y;

        Double p1_p2_squareLength = (double) (dx * dx + dy * dy);
        Double dotProduct = (psx * dx + psy * dy) / p1_p2_squareLength;
        if (dotProduct < 0) {
            return psx * psx + psy * psy;
        } else if (dotProduct <= 1) {
            Double p_p1_squareLength = (double) (psx * psx + psy * psy);
            return p_p1_squareLength - dotProduct * dotProduct * p1_p2_squareLength;
        } else {

            double pex = p.x - end.x;
            double pey = p.y - end.y;
            return (double) (pex * pex + pey * pey);
        }
    }

    private boolean onFuzzyEdge(Point p) {
        int i;
        Edge edge;
        for (i = 0; i < edges.length; i++) {
            edge = edges[i];
            assert edge != null;
            if (distanceToSegment(edge.start, edge.end, p) < EPSILON) {
                return true;
            }
        }
        return false;
    }

    Double area() {
        Double a = edges[0].distance();
        Double b = edges[1].distance();
        Double c = edges[2].distance();
        Double heron = (a + b + c) * (b + c - a) * (c + a - b) * (a + b - c);
        return Math.sqrt(heron) / 4;
    }
}
