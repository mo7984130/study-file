package arrayList;

import java.util.LinkedList;

public class linkedList {
    LinkedList ll = new LinkedList();
    public synchronized void push(Object o){
        ll.addLast(o);
    }
    public synchronized Object pull(){
        Object o = ll.getLast();
        ll.removeLast();
        return o;
    }
    public synchronized Object peek(){
        return ll.getLast();
    }

    public static void main(String[] args){
        linkedList ll = new linkedList();

        for (int i=0;i<5;i++){
            ll.push(i);
        }

        for (int i=0;i<5;i++){
            System.out.println(ll.pull());
        }
    }
}
