package arrayList;

import java.util.ArrayList;
import java.util.List;

public class Node<T extends Comparable> {

    public Node leftNode;
    public Node rightNode;
    public T value;



    public void add(T t){

    }

    public List values(){
        List values = new ArrayList();
        if ( null != leftNode)
            values.addAll(leftNode.values());
        values.add(value);
        if ( null != rightNode)
            values.addAll(rightNode.values());
        return  values;
    }

    public static void main(String[] args){

    }
}
