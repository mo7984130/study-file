package arrayList;

import Classes.Item;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Comparable {

    public static void main(String[] args) {
        List<Item> l = new ArrayList();
        for (int i =0;i<10;i++){
            Item item = new Item();
            item.name = String.valueOf(i);
            item.price = (int)(Math.random()*1000);
            l.add(item);
        }
        System.out.println(l);
        Collections.sort(l,(i1,i2) ->i1.price > i2.price ?1:-1 );
        System.out.println(l);
    }

}
