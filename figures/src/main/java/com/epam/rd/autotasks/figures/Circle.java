package com.epam.rd.autotasks.figures;

class Circle extends Figure{
    private Point center;
    private double radius;

    public Circle(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.pow(this.radius,2) * Math.PI;
    }

    @Override
    public String pointsToString() {
        return "("+center.getX()+","+center.getY()+")";
    }

    @Override
    public String toString() {
        return "Circle[" + pointsToString() +radius+ ']';
    }

    @Override
    public Point leftmostPoint() {
        double v = center.getX() - radius;
        Point point = new Point(v,center.getY());
        return point;
    }
}
