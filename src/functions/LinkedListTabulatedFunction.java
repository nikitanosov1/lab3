package functions;

public class LinkedListTabulatedFunction extends TabulatedFunction{
    private int countPoints;
    private FunctionNode head;
    private FunctionNode lastHistoryNode;
    private int indexOfLastHistoryNode;

    public LinkedListTabulatedFunction(){
        countPoints = 0;
        head = new FunctionNode();
    }

    public FunctionNode getNodeByIndex(int index){
        FunctionNode nodeReturn;
        int temp;
        if (indexOfLastHistoryNode < index){
            if (index - indexOfLastHistoryNode < countPoints - index + 1){
                temp = index - indexOfLastHistoryNode;
                while (temp) nodeReturn =
            }
        }else{

        }

        lastHistoryNode = nodeReturn;
        indexOfLastHistoryNode = index;
        return nodeReturn;
    }


    private class FunctionNode{
        public FunctionPoint point;
        public FunctionNode prevFunctionNode;
        public FunctionNode nextFunctionNode;

        public FunctionNode(){
            //point = new FunctionPoint(); // temp string. may be delete this?
            prevFunctionNode = this;
            nextFunctionNode = this;
        }

    }
}
