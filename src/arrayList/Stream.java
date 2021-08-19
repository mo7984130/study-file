package arrayList;

import Classes.Hero;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Stream {

    public static void main(String[] args) {
        List<Hero> l = new ArrayList<Hero>();
        for (int i =0;i<10;i++){
            Hero h = new Hero();
            h.name = String.valueOf(i);
            h.hp = (int)(Math.random()*1000);
            l.add(h);
        }

        System.out.println(l);
        Collections.sort(l,(h1,h2)->h1.hp<h2.hp?1:-1);
        System.out.println(l.get(2));

        Hero h = l
        .stream()
        .sorted((h1,h2)->h1.hp<h2.hp ?1 :-1)
        .skip(2)
        .findFirst()
        .get();
        System.out.println(h);

    }

}
