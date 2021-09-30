package functions;
import java.lang.*;

public class TabulatedFunction {
    private FunctionPoint[] points;
    private int countPoints;

    public int maxCountElem(){return points.length;}; //Сколько с запасом влезет элементов в данный массив

    public TabulatedFunction(double leftX, double rightX, int pointsCount) throws IllegalArgumentException{
        if ((pointsCount < 2) || (leftX >= rightX)) throw new IllegalArgumentException();
        countPoints = pointsCount;
        points = new FunctionPoint[pointsCount];
        double interval = (rightX - leftX)/(pointsCount - 1);
        for (int i = 0; i < pointsCount; ++i) {
            points[i] = new FunctionPoint(leftX + i * interval, 0);
        }
    }

    public TabulatedFunction(double leftX, double rightX, double[] values) throws IllegalArgumentException{
        if ((values.length < 2) || (leftX >= rightX)) throw new IllegalArgumentException();
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
    public FunctionPoint getPoint(int index) throws FunctionPointIndexOutOfBoundsException{
        if ((index < 0) || (index >= countPoints)) { throw new FunctionPointIndexOutOfBoundsException();}
        return points[index];
    }

    public void setPoint(int index, FunctionPoint point) throws FunctionPointIndexOutOfBoundsException, InappropriateFunctionPointException{
        if ((index < 0) || (index >= countPoints)) { throw new FunctionPointIndexOutOfBoundsException();}

        if ((index == 0) && (point.x < points[1].x)){
            points[0] = point;
        }else if ((index == countPoints - 1) && (point.x > points[countPoints - 2].x)){
            points[countPoints - 1] = point;
        }else{
            if ((point.x > points[index - 1].x) && (point.x < points[index + 1].x)) { throw new InappropriateFunctionPointException();}
            points[index] = point;
        }
    }

    public double getPointX(int index) throws FunctionPointIndexOutOfBoundsException{
        if ((index < 0) || (index >= countPoints)) { throw new FunctionPointIndexOutOfBoundsException();}
        return points[index].x;
    }

    public void setPointX(int index, double x) throws FunctionPointIndexOutOfBoundsException, InappropriateFunctionPointException{
        if ((index < 0) || (index >= countPoints)) { throw new FunctionPointIndexOutOfBoundsException();}

        if ((index == 0) && (x < points[1].x)){
            points[0].x = x;
        }else if ((index == countPoints - 1) && (x > points[countPoints - 2].x)){
            points[countPoints - 1].x = x;
        }else{
            if ((x > points[index - 1].x) && (x < points[index + 1].x)) { throw new InappropriateFunctionPointException();}
            points[index].x = x;
        }
    }

    public double getPointY(int index) throws FunctionPointIndexOutOfBoundsException{
        if ((index < 0) || (index >= countPoints)) { throw new FunctionPointIndexOutOfBoundsException();}
        return points[index].y;
    }
    public void setPointY(int index, double y) throws FunctionPointIndexOutOfBoundsException{
        if ((index < 0) || (index >= countPoints)) { throw new FunctionPointIndexOutOfBoundsException();}
        points[index].y = y;
    }

    public void deletePoint(int index) throws FunctionPointIndexOutOfBoundsException, InappropriateFunctionPointException{
        if ((index < 0) || (index >= countPoints)) { throw new FunctionPointIndexOutOfBoundsException();}
        if (countPoints == 3){ throw new InappropriateFunctionPointException();}

        --countPoints;
        for (int i = index; i < countPoints; ++i) points[i] = points[i + 1]; // Сдвиг правой части на массива на место удалённого
        if (countPoints == points.length/2){
            FunctionPoint[] temp = new FunctionPoint[countPoints];
            System.arraycopy(points, 0, temp, 0, countPoints);
            points = temp;
        }
    }

    public void addPoint(FunctionPoint point) throws InappropriateFunctionPointException{
        int left = 0;
        int right = countPoints - 1;
        int mid = -1;
        while (left < right){
            mid = (left + right) / 2;
            if (point.x == points[mid].x) throw new InappropriateFunctionPointException();
            if (point.x < points[mid].x){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
            System.out.print(left);
            System.out.println(right);
        }

        int indexForAdd = right;
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