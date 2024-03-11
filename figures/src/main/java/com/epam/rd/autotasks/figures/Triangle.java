package com.epam.rd.autotasks.figures;

class Triangle extends Figure{
    private Point a;
    private Point b;
    private Point c;

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double area() {
        double v = (a.getX() * b.getY()) + (b.getX() * c.getY()) + (c.getX() * a.getY());
        double v1 = (b.getX() * a.getY()) + (c.getX() * b.getY()) + (a.getX() * c.getY());
        double result = Math.abs((v - v1) / 2);
        return result;
    }

    @Override
    public String pointsToString() {
        return "("+a.getX()+","+a.getY()+")" + "("+b.getX()+","+b.getY()+")"+"("+c.getX()+","+c.getY()+")";
    }

    @Override
    public String toString() {
        return "Triangle[" +
                pointsToString() +
                ']';
    }

    @Override
    public Point leftmostPoint() {
        Point[] points = new Point[3];
        points[0]=a;
        points[1]=b;
        points[2]=c;
        Point min = points[0];

        for (int i = 0; i < points.length; i++) {
            if (points[i].getX()< min.getX()){
                min = points[i];
            }
        }
        return min;
    }
}
