package arrayList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class _HashSet {

    public static String randomChar(){
        String s = "";
        char c = 0;

        for (int j = 0; j < 2; j++) {
            int i = (int)(Math.random()*(2 + 1));
            switch(i) {
                case 0:
                    c = (char)(Math.random()*('9' - '0' + 1) + '0');
                    break;
                case 1:
                    c = (char)(Math.random()*('z' - 'a' + 1) + 'a');
                    break;
                case 2:
                    c = (char)(Math.random()*('Z' - 'A' + 1) + 'A');
            }
            s += c;
        }
        return s;
    }

    public static void main(String[] args){

        HashSet<String> str = new HashSet<>();
        HashSet<String> result = new HashSet<>();
        List l1 = new ArrayList();
        for (int i = 0;i<50;i++){
            l1.add(randomChar());
        }
        for (int i =0;i<l1.size();i++){
            if (!str.add((String) l1.get(i))){
                result.add(String.valueOf(l1.get(i)));
            }
        }
        System.out.println(l1);
        System.out.println(result);

    }
}
