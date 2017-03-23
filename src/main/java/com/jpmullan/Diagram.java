package com.jpmullan;

import java.util.*;

/**
 * Created by jmull_000 on 12/31/2016.
 */
public class Diagram {
    Random random = new Random();

    Integer pointCount;
    List<Point> points;
    List<Edge> edges;
    Triangle[] triangles;

    Diagram(int pointCount, double width, double height) {
        this.pointCount = pointCount;
        int i;
        double x;
        double y;

        // S is a set of n >= 1 points with unique bottommost point
        points = new ArrayList<>();
        for (i = 0; i < pointCount; i++) {
            x = random.nextDouble() * width;
            y = random.nextDouble() * height;
            points.add(new Point(x, y));
        }
        Collections.sort(points, Point::compareTo);

        // Q: a priority queue of points in the plane, ordered lexicographically.
        // Each point is labeled as a site, or labeled as the intersection
        // of a pair of boundaries of a single region. Q may contain duplicate
        // instances of the same point with distinct labels; the ordering of
        // duplicates is irrelevant.

        // L: a sequence (r1, c1, r2, ... , rk) of regions (labeled by site) and
        // boundaries (labeled by a pair of sites). Note that a region can
        // appear many times on L

        Queue<Event> events = new PriorityQueue<>();

        // 1. initialize Q with all sites
        for (Point site: points) {
            Event event = new Event(site);
            events.add(event);
        }

        // 2. p <- extract_min(Q)
        Event event = events.remove();

        // 3. L <- the list containing R of p
        List<Region> regions = new LinkedList<>();
        Region region = new Region(event.site);
        regions.add(region);

        // 4. white Q is not empty begin
        while (events.size() > 0) {
            // 5. p <- extract_min(Q)
            event = events.remove();

            // 6. case
            if (event.type == Event.POINT) {
                // 7. p is a site:
                Point p = event.site;
                Point q;
                // 8. Find an occurrence of a region R*q on L containing p
                for (Region testRegion: regions) {
                    if (region.contains(p)) {
                        q = region.site;
                        break;
                    }
                }
                // 9. Create bisector B*pq
                Edge bisector = Edge.bisect(p, q);
                // 10. Update list L so that it contains ... , R*q, C-pq, R*p, C+pq, R*q, ... in
                // place of R*q
                // 11. Delete from Q the intersection between the left and right boundary
                // of R*q, if any
                // 12. Insert into Q the intersection between C-pq and it s neighbor to the
                // left on L, if any, and the intersection between C+pw and its neighbor
                // to the right, if any.
            } else {
                // 13. p is an intersection:
                // 14. Let p be the intersection of boundaries Cqr and Crs.
                Point p = event.site;
                // 15. Create the bisector B*qs
                Edge bisector = Edge.bisect(q, s);
                // 16. Update list L so it contains Cqa = C-qa or C+qa, as appropriate, instead
                // of Cqa, R*r, Crs.
                // 17. Delete from Q any intersection between Cqr and its neighbor to the
                // left and between Crs and its neighbor to the right.
                // 18. Insert any intersections between Cqa and its neighbors to the left or
                // right into Q.
                // 19. Mark p as a vertex and as an endpoint of B*qa, B*ra, and B*qa.
            }
        }
        boxDiagram();
    }
    void boxDiagram() {

    }
}
