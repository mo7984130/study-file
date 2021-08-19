package String;

public class myStringBuffer {
    int capacity = 19;
    int length = 0;
    char[] value;

    public myStringBuffer(){
        value = new char[capacity];
    }

    public myStringBuffer(String str){
        value = str.toCharArray();
        while (true){
            if (value.length > capacity){
                capacity += capacity*0.5;
            }else {break;}
        }
        length = value.length;
    }

    public void append(String str){

    }

    public static void main(String[] args){
        myStringBuffer msb = new myStringBuffer("testssadadwadasdawdadadadsdada");
        System.out.println(msb.capacity);
        System.out.println(msb.length);
        System.out.println(msb.value);
    }

}
