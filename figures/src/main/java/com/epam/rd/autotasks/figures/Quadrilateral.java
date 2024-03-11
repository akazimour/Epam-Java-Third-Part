package com.epam.rd.autotasks.figures;

class Quadrilateral extends Figure{
    private final Point a;
    private final Point b;
    private final Point c;
    private final Point d;

    public Quadrilateral(Point a, Point b, Point c, Point d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    @Override
    public double area() {
        double abcArea = new Triangle(a, b, c).area();
        double acdArea = new Triangle(a, c, d).area();
        return abcArea+acdArea;
    }

    @Override
    public String pointsToString() {
        return "("+a.getX()+","+a.getY()+")" + "("+b.getX()+","+b.getY()+")"+"("+c.getX()+","+c.getY()+")" + "("+d.getX()+","+d.getY()+")";
    }

    @Override
    public String toString() {
        return "Quadrilateral[" +
                pointsToString() +
                ']';
    }

    @Override
    public Point leftmostPoint() {
        Point[] points = new Point[4];
        points[0]=a;
        points[1]=b;
        points[2]=c;
        points[3]=d;
        Point min = points[0];

        for (int i = 0; i < points.length; i++) {
            if (points[i].getX()< min.getX()){
                min = points[i];
            }
        }
        return min;
    }
}
