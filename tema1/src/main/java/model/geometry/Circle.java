package model.geometry;

public class Circle implements GeometryObject{
    private Point origin;
    private float radius;

    public Circle(Point origin, float radius){
        this.origin = origin;
        this.radius = radius;
    }

    public float computeArea(){
        return (float) (Math.PI*radius*radius);
    }

    public float computePerimeter(){
        return (float) (2*Math.PI*radius);
    }
}
