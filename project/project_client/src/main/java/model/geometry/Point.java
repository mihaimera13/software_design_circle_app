package model.geometry;

public class Point implements GeometryObject{
    private float x;
    private float y;


    public Point(float x, float y){
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public double computeDistance(Point a){
        return Math.sqrt(Math.pow((this.x - a.x),2)+Math.pow((this.y - a.y),2));
    }

    @Override
    public String toString(){
        return "("+ this.x + ", " + this.y + ")";
    }
}