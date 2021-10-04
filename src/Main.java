import functions.*;

public class Main {
    public static void main(String[] args){
        final int countPoints = 5;
        final double left = 0.0;
        final double right = 10.0;

        LinkedListTabulatedFunction linkedList = new LinkedListTabulatedFunction(left, right, countPoints);

        //linkedList.addNodeToTail();
        //System.out.println(linkedList.getNodeByIndex(0));
        //System.out.println(linkedList.getNodeByIndex(1));
        /*
        TabulatedFunction graph;
        graph = new TabulatedFunction(left, right, countPoints);

        for (int i = 0; i < countPoints; ++i){
            graph.setPointY(i, - (graph.getPointX(i) - 4)*(graph.getPointX(i) - 4) + 5);
        }

        FunctionPoint p = new FunctionPoint(5.1, 228);

        try {
            graph.deletePoint(3);
            graph.deletePoint(0);

        }
        catch(FunctionPointIndexOutOfBoundsException error1){
            error1.printStackTrace();
        }
        catch (InappropriateFunctionPointException error2) {
            error2.printStackTrace();
        }

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
        */
    }
}
