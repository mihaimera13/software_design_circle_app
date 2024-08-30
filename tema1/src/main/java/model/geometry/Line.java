package model.geometry;

public class Line implements GeometryObject{
    private Point start;
    private Point end;

    public Line(Point start,Point end){
        this.start = start;
        this.end = end;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public double computeSlope(){
        if(end.getX() - start.getX() == 0){
            return Double.POSITIVE_INFINITY;
        }
        else return (end.getY()- start.getY())/(end.getX() - start.getX());
    }

    public double computeLength(){
        return start.computeDistance(end);
    }

    public double computeAngle(Line line){
        double slope1 = this.computeSlope();
        double slope2 = line.computeSlope();

        if(Double.isInfinite(slope1) && Double.isInfinite(slope2)){
            return 0.0;
        }
        else if (Double.isInfinite(slope1)) {
            double angle = Math.atan(slope2);
            return Math.toDegrees(slope2 > 0 ? angle : (angle + Math.PI/2)) ;
        } else if (Double.isInfinite(slope2)) {
            double angle = Math.atan(slope1);
            return Math.toDegrees(slope1 > 0 ? angle : (angle + Math.PI/2));
        } else {
            return Math.abs(Math.toDegrees(Math.atan((slope2 - slope1) / (1 + slope1 * slope2))));
        }
    }

    public Point computeMiddlePoint(){
        return new Point((this.start.getX()+this.end.getX())/2,(this.start.getY()+this.end.getY())/2);
    }

    @Override
    public String toString(){
        return this.start.toString() + "->" + this.end.toString();
    }
}
