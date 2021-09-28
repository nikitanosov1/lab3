import functions.FunctionPoint;
import functions.TabulatedFunction;

public class Main {
    public static void main(String[] args){
        final int countPoints = 5;
        final double left = 0.0;
        final double right = 10.0;

        TabulatedFunction graph;
        graph = new TabulatedFunction(left, right, countPoints);

        for (int i = 0; i < countPoints; ++i){
            graph.setPointY(i, - (graph.getPointX(i) - 4)*(graph.getPointX(i) - 4) + 5);
        }

        FunctionPoint p = new FunctionPoint(-1, 228);
        //graph.addPoint(p);
        graph.deletePoint(3);
        graph.deletePoint(2);
        graph.deletePoint(2);
        graph.addPoint(p);
        System.out.println(graph.getPointsCount());
        System.out.println(graph.maxCountElem());
        for (int i = 0; i < graph.getPointsCount(); ++i){
            System.out.print("x value: ");
            System.out.print(graph.getPointX(i));
            System.out.print("            y value: ");
            System.out.println(graph.getPointY(i));
        }


        System.out.println();
        final int countPointsForTest = 10;
        final double interval = (right - left)/(countPointsForTest - 1);
        for (int i = 0; i < countPointsForTest; ++i){
            System.out.print("x value: ");
            System.out.print(left + i * interval);
            System.out.print("           y value: ");
            System.out.println(graph.getFunctionValue(left + i * interval));
        }

    }
}
