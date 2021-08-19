package arrayList;

import Classes.Hero;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class _HashMap {
    public static void main(String[] args){
        HashMap<Integer , Hero> herosMap = new HashMap<Integer,Hero>();
        long time1 = System.currentTimeMillis();

        for (int i=0;i<3000000;i++){
            Hero h = new Hero();
            int i1 = (int) (Math.random()*10000);
            h.name = "Heros-" + i1;
            herosMap.put(i,h);
        }
        for (int i = 0;i<herosMap.size();i++){
            if (herosMap.get(i).name.equals("Heros-5555"));
        }
        System.out.println("");

        long time2 = System.currentTimeMillis() - time1;

        System.out.println(time2);

        List<Hero> l = new ArrayList();
        for (int i=0;i<3000000;i++){
            Hero h = new Hero();
            int i1 = (int) (Math.random()*10000);
            h.name = "Heros-" + i1;
            l.add(h);
        }

        for (int i = 0;i<herosMap.size();i++){
            Hero h = l.get(i);
            if (h.name.equals("Heros-5555"));
        }
        System.out.println("");

        System.out.println(System.currentTimeMillis() -time1 - time2);
    }
}
