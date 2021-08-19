package arrayList;

import java.util.ArrayList;
import java.util.List;

public class Traverse {

    public static void main(String[] args){

        List heros = new ArrayList();
        for (int i=0;i<100;i++){
            heros.add(i);
        }

        for (int i = 0;i<heros.size();i++){
            int i1 = (int) heros.get(i);
            if (i1 % 8 == 0){
                heros.remove(i);
            }
        }

        for (Object i : heros){
            System.out.println(i);
        }

    }
}
