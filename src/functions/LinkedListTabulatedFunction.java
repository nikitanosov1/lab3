package functions;

public class LinkedListTabulatedFunction extends TabulatedFunction{
    private int countPoints;
    private FunctionNode head;
    private FunctionNode lastHistoryNode;
    private int indexOfLastHistoryNode;

    public LinkedListTabulatedFunction(){
        head = new FunctionNode();
        lastHistoryNode = head;
        indexOfLastHistoryNode = 0;
        countPoints = 0;
    }

    public FunctionNode getNodeByIndex(int index){
        FunctionNode nodeReturn;
        if (indexOfLastHistoryNode < index){
            if (index - indexOfLastHistoryNode < countPoints - index){
                nodeReturn = lastHistoryNode;
                for (int i = 0; i < index - indexOfLastHistoryNode; ++i){
                    nodeReturn = nodeReturn.nextFunctionNode;
                }
            }else{
                nodeReturn = head;
                for (int i = 0; i < countPoints - index; ++i){
                    nodeReturn = nodeReturn.prevFunctionNode;
                }
            }
        }else{
            if (index + 1 < indexOfLastHistoryNode - index){
                nodeReturn = head;
                for (int i = 0; i < index + 1; ++i){
                    nodeReturn = nodeReturn.nextFunctionNode;
                }
            }else{
                nodeReturn = lastHistoryNode;
                for (int i = 0; i < indexOfLastHistoryNode - index; ++i){
                    nodeReturn = nodeReturn.prevFunctionNode;
                }
            }
        }
        lastHistoryNode = nodeReturn;
        indexOfLastHistoryNode = index;
        return nodeReturn;
    }

    public FunctionNode addNodeToTail(){
        FunctionNode insertNode =  new FunctionNode();
        insertNode.nextFunctionNode = head;
        insertNode.prevFunctionNode = head.prevFunctionNode;
        head.prevFunctionNode.nextFunctionNode = insertNode;
        head.prevFunctionNode = insertNode;

        lastHistoryNode = insertNode; //??
        indexOfLastHistoryNode = countPoints; //??

        ++countPoints;
        return insertNode;
    }

    public FunctionNode addNodeByIndex(int index){
        FunctionNode insertNode =  new FunctionNode();
        FunctionNode temp = this.getNodeByIndex(index);

        insertNode.nextFunctionNode = temp.nextFunctionNode;
        insertNode.prevFunctionNode = temp;
        temp.nextFunctionNode.prevFunctionNode = insertNode;
        temp.nextFunctionNode = insertNode;

        lastHistoryNode = insertNode; //??
        indexOfLastHistoryNode = index; //??

        return insertNode;
    }

    public FunctionNode deleteNodeByIndex(int index){
        FunctionNode temp = this.getNodeByIndex(index);

        temp.prevFunctionNode.nextFunctionNode = temp.nextFunctionNode;
        temp.nextFunctionNode.prevFunctionNode = temp.prevFunctionNode;

        return temp;
    }

    class FunctionNode{
        private FunctionPoint point;
        private FunctionNode prevFunctionNode;
        private FunctionNode nextFunctionNode;

        public FunctionNode(){
            //point = new FunctionPoint(); // temp string. may be delete this?
            prevFunctionNode = this;
            nextFunctionNode = this;
        }

    }
}
