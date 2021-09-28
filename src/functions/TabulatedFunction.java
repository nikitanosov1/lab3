package functions;

public class TabulatedFunction {
    private FunctionPoint[] points;
    private int countPoints;

    public int maxCountElem(){return points.length;}; //Сколько с запасом влезет элементов в данный массив

    public TabulatedFunction(double leftX, double rightX, int pointsCount){
        countPoints = pointsCount;
        points = new FunctionPoint[pointsCount];
        double interval = (rightX - leftX)/(pointsCount - 1);
        for (int i = 0; i < pointsCount; ++i) {
            points[i] = new FunctionPoint(leftX + i * interval, 0);
        }
    }

    public TabulatedFunction(double leftX, double rightX, double[] values){
        countPoints = values.length;
        points = new FunctionPoint[values.length];
        double interval = (rightX - leftX)/(values.length - 1);
        for (int i = 0; i < values.length; ++i) {
            points[i] = new FunctionPoint(leftX + i * interval, values[i]);
        }
    }

    public double getLeftDomainBorder(){ return this.points[0].x;}
    public double getRightDomainBorder(){ return this.points[countPoints - 1].x;}
    public double getFunctionValue(double x){
        if ((x < points[0].x) || (x > points[countPoints - 1].x)){ return Double.NaN;}
        int index = -1;
        for (int i = 0; i < countPoints - 1; ++i){
            if ((x >= points[i].x) && (x <= points[i + 1].x)){
                index = i;
                break;
            }
        }
        return points[index].y + (points[index + 1].y - points[index].y)*(x - points[index].x)/(points[index + 1].x - points[index].x);
    }


    public int getPointsCount(){ return countPoints;}
    public FunctionPoint getPoint(int index){return points[index]; }
    public void setPoint(int index, FunctionPoint point){
        if ( !((index == 0) || (index == countPoints - 1)) ){
            if ( (point.x > points[index - 1].x) && (point.x < points[index + 1].x) ){
                points[index] = point;
            }
        }
    }

    public double getPointX(int index){ return points[index].x;}
    public void setPointX(int index, double x){
        if ( !((index == 0) || (index == countPoints - 1)) ){
            if ( (x > points[index - 1].x) && (x < points[index + 1].x) ){
                points[index].x = x;
            }
        }
    }

    public double getPointY(int index){ return points[index].y;}
    public void setPointY(int index, double y){ points[index].y = y; }

    public void deletePoint(int index){
        --countPoints;
        for (int i = index; i < countPoints; ++i) points[i] = points[i + 1]; // Сдвиг правой части на массива на место удалённого
        if (countPoints == points.length/2){
            FunctionPoint[] temp = new FunctionPoint[countPoints];
            System.arraycopy(points, 0, temp, 0, countPoints);
            points = temp;
        }
    }

    public void addPoint(FunctionPoint point){
        int indexForAdd = 0;
        for (int i = 0; i < countPoints; ++i) if (points[i].x < point.x) indexForAdd = i + 1; // Куда вставить элемент?
        ++countPoints;
        if (countPoints > points.length){
            FunctionPoint[] temp = new FunctionPoint[2 * points.length];
            System.arraycopy(points, 0, temp, 0, indexForAdd);
            temp[indexForAdd] = point;
            System.arraycopy(points, indexForAdd, temp, indexForAdd + 1, points.length - indexForAdd);
            points = temp;
        }else{
            for (int i = countPoints - 1; i > indexForAdd; --i) points[i] = points[i - 1];
            points[indexForAdd] = point;
        }
    }
}