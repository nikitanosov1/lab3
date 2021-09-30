package functions;

public class FunctionPoint {
    private double x;
    private double y;

    public double getX(){return x; }
    public double getY(){return y; }

    public void setX(double xTemp){ x = xTemp; }
    public void setY(double yTemp){ y = yTemp; }


    public FunctionPoint(double x, double y){
        this.x = x;
        this.y = y;
    }

    public FunctionPoint(FunctionPoint point){
        this.x = point.x;
        this.y = point.y;
    }

    public FunctionPoint(){
        this.x = 0;
        this.y = 0;
    }
}