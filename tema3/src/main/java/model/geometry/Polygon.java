package model.geometry;

import java.util.ArrayList;

public class Polygon implements GeometryObject {
    private ArrayList<Point> vertices;

    public Polygon(ArrayList<Point> vertices){
        this.vertices = vertices;
    }

    public ArrayList<Point> getVertices() {
        return vertices;
    }

    public boolean isInscribed(){
        if(this.vertices.size()==3)
            return true;
        else{
            ArrayList<Line> lines = this.transformToLines();
            float angle = (float) lines.get(0).computeAngle(lines.get(1));
            float length = (float) lines.get(0).computeLength();
            for(int i=0;i<lines.size();i++){
                if((int) lines.get(0).computeLength()!=(int)length)
                    return false;
                if(i==lines.size()-1){
                    if((int) lines.get(i).computeAngle(lines.get(0))!=(int)angle)
                        return false;
                }
                else{
                    if ((int) lines.get(i).computeAngle(lines.get(i+1))!=(int)angle)
                        return false;
                }
            }
            return true;
        }
    }

    public boolean isCircumscribed(){
        Point center = this.getCircumcenter();
        float distance = (float) center.computeDistance(vertices.get(0));
        for(Point vertex : vertices.subList(1, vertices.size())){
            if((int)center.computeDistance(vertex)!=(int)distance)
                return false;
        }
        return true;
    }

    public ArrayList<Line> transformToLines(){
        ArrayList<Line> lines = new ArrayList<>();
        for(int i=0;i<vertices.size();i++){
            Line line;
            if(i == vertices.size()-1){
                line = new Line(this.vertices.get(i),this.vertices.get(0));
            }
            else line = new Line(this.vertices.get(i),this.vertices.get(i+1));
            lines.add(line);
        }
        return lines;
    }

    public Point getCircumcenter(){
        Point point;

        ArrayList<Line> lines = this.transformToLines();
        Line line1;
        double slope1;
        int index = 0;
        while(lines.get(index).computeSlope()==0){
            index++;
        }
        line1 = lines.get(index);
        slope1 = line1.computeSlope();
        index++;

        Line line2;
        double slope2;
        while(lines.get(index).computeSlope()==0){
            index++;
        }
        line2 = lines.get(index);
        slope2 = line2.computeSlope();

        Point middle1 = line1.computeMiddlePoint();
        Point middle2 = line2.computeMiddlePoint();

        float x = (float) ((middle2.getX()/slope2 - middle1.getX()/slope1 + middle2.getY() - middle1.getY())/(1/slope2 - 1/slope1));
        float y = (float) (-1/slope1*(x - middle1.getX()) + middle1.getY());

        point = new Point(x,y);

        return point;
    }

    public Point getIncenter(){
        Point incenter;
        if(this.vertices.size()==3){
            ArrayList<Line> lines = this.transformToLines();
            float x = (float) ((this.vertices.get(0).getX()*lines.get(1).computeLength() +
                                this.vertices.get(1).getX()*lines.get(2).computeLength() +
                                this.vertices.get(2).getX()*lines.get(0).computeLength())/
                                (lines.get(0).computeLength() + lines.get(1).computeLength() + lines.get(2).computeLength()));
            float y = (float) ((this.vertices.get(0).getY()*lines.get(1).computeLength() +
                    this.vertices.get(1).getY()*lines.get(2).computeLength() +
                    this.vertices.get(2).getY()*lines.get(0).computeLength())/
                    (lines.get(0).computeLength() + lines.get(1).computeLength() + lines.get(2).computeLength()));
            incenter = new Point(x,y);
        }
        else{
            incenter = this.getCircumcenter();
        }

        return incenter;
    }

    public float computePerimeter(){
        float perimeter = 0;
        ArrayList<Line> lines = this.transformToLines();
        for(Line line : lines){
            perimeter += line.computeLength();
        }
        return perimeter;
    }

    public double computeAreaOfTriangle(){

        float area = 1;
        ArrayList<Line> lines = this.transformToLines();
        float perimeter = this.computePerimeter();
        for(Line line : lines){
            area *= (perimeter/2 - line.computeLength());
        }

        return Math.sqrt(perimeter/2*area);

    }
}