package com.jpmullan;

/**
 * Created by jmull_000 on 2/12/2017.
 */
public class Event {
    static final int POINT = 0;
    static final int CIRCLE = 1;
    public Point site;
    public Parabola parabola;
    public int type;
    Event(Point site) {
        this.site = site;
    }
}
