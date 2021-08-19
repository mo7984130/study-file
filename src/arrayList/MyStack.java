package arrayList;

import java.util.LinkedList;

public class MyStack<T>{
   volatile LinkedList<T> l = new LinkedList<>();

    public synchronized void push(T t){
        if (l.size()>=200) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        l.addFirst(t);
        this.notify();
    };

    public synchronized T pull(){
        if (l.size()<=0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notify();
        return l.removeFirst();

    };

    public synchronized T peek(){
        return l.getFirst();
    };

}
