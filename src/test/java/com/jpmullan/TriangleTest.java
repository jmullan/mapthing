package com.jpmullan;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by jmull_000 on 12/31/2016.
 */
public class TriangleTest {
    @Test
    public void testContains() {
        Triangle triangle = new Triangle(
            new Point(0, 0),
            new Point(0, 10),
            new Point(10, 0)
        );
        assertTrue(triangle.contains(new Point(1, 1)));
        assertTrue(triangle.contains(new Point(0, 5)));
        assertTrue(triangle.contains(new Point(5, 0)));
        assertFalse(triangle.contains(new Point(20, 20)));
    }

    @Test
    public void testArea() {
        Triangle triangle = new Triangle(
            new Point(0, 0),
            new Point(0, 10),
            new Point(10, 0)
        );
        assertEquals(50.0, triangle.area());
    }
}
