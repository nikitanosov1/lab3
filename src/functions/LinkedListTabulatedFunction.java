package functions;

public class LinkedListTabulatedFunction implements TabulatedFunction{
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
    public LinkedListTabulatedFunction(double leftX, double rightX, int pointsCount) throws IllegalArgumentException{
        if ((pointsCount < 2) || (leftX >= rightX)) throw new IllegalArgumentException();
        head = new FunctionNode();
        double interval = (rightX - leftX)/(pointsCount - 1);
        for (int i = 0; i < pointsCount; ++i) {
            this.addNodeToTail();
            head.prevFunctionNode.point = new FunctionPoint(leftX + i * interval, 0);
        }
    }
    public LinkedListTabulatedFunction(double leftX, double rightX, double[] values) throws IllegalArgumentException{
        if ((values.length < 2) || (leftX >= rightX)) throw new IllegalArgumentException();
        head = new FunctionNode();
        double interval = (rightX - leftX)/(values.length - 1);
        for (int i = 0; i < values.length; ++i) {
            this.addNodeToTail();
            head.prevFunctionNode.point = new FunctionPoint(leftX + i * interval, values[i]);
        }
    }
    public double getLeftDomainBorder(){ return head.nextFunctionNode.point.getX();}
    public double getRightDomainBorder(){ return head.prevFunctionNode.point.getX();}
    public double getFunctionValue(double x){
        if ((x < getLeftDomainBorder()) || (x > getRightDomainBorder())){ return Double.NaN;}
        int index = -1;
        for (int i = 0; i < countPoints - 1; ++i){
            if ((x >= getNodeByIndex(i).point.getX()) && (x <= getNodeByIndex(i + 1).point.getX())){
                index = i;
                break;
            }
        }
        return getNodeByIndex(index).point.getY() + (getNodeByIndex(index + 1).point.getY() - getNodeByIndex(index).point.getY())*(x - getNodeByIndex(index).point.getX())/(getNodeByIndex(index + 1).point.getX() - getNodeByIndex(index).point.getX());
    }
    public int getPointsCount(){ return countPoints;}
    public FunctionPoint getPoint(int index) throws FunctionPointIndexOutOfBoundsException{
        if ((index < 0) || (index >= countPoints)) { throw new FunctionPointIndexOutOfBoundsException();}
        return getNodeByIndex(index).point;
    }
    public void setPoint(int index, FunctionPoint point) throws FunctionPointIndexOutOfBoundsException, InappropriateFunctionPointException{
        if ((index < 0) || (index >= countPoints)) { throw new FunctionPointIndexOutOfBoundsException();}

        if ((index == 0) && (point.getX() < getNodeByIndex(1).point.getX())){
            getNodeByIndex(0).point = point;
        }else if ((index == countPoints - 1) && (point.getX() > getNodeByIndex(countPoints - 2).point.getX())){
            getNodeByIndex(countPoints - 1).point = point;
        }else{
            if ((point.getX() > getNodeByIndex(index - 1).point.getX()) && (point.getX() < getNodeByIndex(index + 1).point.getX())) { throw new InappropriateFunctionPointException();}
            getNodeByIndex(index).point = point;
        }
    }
    public double getPointX(int index) throws FunctionPointIndexOutOfBoundsException{
        if ((index < 0) || (index >= countPoints)) { throw new FunctionPointIndexOutOfBoundsException();}
        return getNodeByIndex(index).point.getX();
    }
    public void setPointX(int index, double x) throws FunctionPointIndexOutOfBoundsException, InappropriateFunctionPointException{
        if ((index < 0) || (index >= countPoints)) { throw new FunctionPointIndexOutOfBoundsException();}

        if ((index == 0) && (x < getNodeByIndex(1).point.getX())){
            getNodeByIndex(0).point.setX(x);
        }else if ((index == countPoints - 1) && (x > getNodeByIndex(countPoints - 2).point.getX())){
            getNodeByIndex(countPoints - 1).point.setX(x);
        }else{
            if ((x > getNodeByIndex(index - 1).point.getX()) && (x < getNodeByIndex(index + 1).point.getX())) { throw new InappropriateFunctionPointException();}
            getNodeByIndex(index).point.setX(x);
        }
    }
    public double getPointY(int index) throws FunctionPointIndexOutOfBoundsException{
        if ((index < 0) || (index >= countPoints)) { throw new FunctionPointIndexOutOfBoundsException();}
        return getNodeByIndex(index).point.getY();
    }
    public void setPointY(int index, double y) throws FunctionPointIndexOutOfBoundsException{
        if ((index < 0) || (index >= countPoints)) { throw new FunctionPointIndexOutOfBoundsException();}
        getNodeByIndex(index).point.setY(y);
    }
    public void deletePoint(int index) throws FunctionPointIndexOutOfBoundsException, InappropriateFunctionPointException{
        if ((index < 0) || (index >= countPoints)) { throw new FunctionPointIndexOutOfBoundsException();}
        if (countPoints == 0){ throw new InappropriateFunctionPointException();}
        FunctionNode nodeToDelete = getNodeByIndex(index);
        nodeToDelete.prevFunctionNode.nextFunctionNode = nodeToDelete.nextFunctionNode;
        nodeToDelete.nextFunctionNode.prevFunctionNode = nodeToDelete.prevFunctionNode;

        indexOfLastHistoryNode = 0;
        lastHistoryNode = head.nextFunctionNode;
        --countPoints;
    }
    public void addPoint(FunctionPoint point) throws InappropriateFunctionPointException  {
        int indexForAdd = 0;
        for (int i = 0; i < countPoints; ++i)
            if (getNodeByIndex(i).point.getX() < point.getX()) indexForAdd = i + 1; // ???????? ???????????????? ???????????????

        if ((indexForAdd != countPoints) && (getNodeByIndex(indexForAdd).point.getX() == point.getX())) {
            throw new InappropriateFunctionPointException();
        }
        FunctionNode nodeToAdd = new FunctionNode();
        nodeToAdd.point = point;
        nodeToAdd.nextFunctionNode = getNodeByIndex(indexForAdd);
        nodeToAdd.prevFunctionNode = getNodeByIndex(indexForAdd - 1);
        getNodeByIndex(indexForAdd).prevFunctionNode = nodeToAdd;
        getNodeByIndex(indexForAdd - 1).nextFunctionNode = nodeToAdd;

        indexOfLastHistoryNode = countPoints;
        lastHistoryNode = nodeToAdd;
        ++countPoints;
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
