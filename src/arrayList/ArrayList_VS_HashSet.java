package arrayList;

import java.util.HashSet;

public class ArrayList_VS_HashSet {

    public static void main(String[] args){
        HashSet h = new HashSet();
        while (h.size()<50){
            h.add((int) (Math.random()*10000));
        }
        System.out.println(h);
    }

}
