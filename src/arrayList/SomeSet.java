package arrayList;

import java.util.LinkedHashSet;

public class SomeSet {

    public static void main(String[] args){
        LinkedHashSet<Character> lhs = new LinkedHashSet<>();
        char[] cs = String.valueOf(Math.PI).toCharArray();
        for (int i=0;i<cs.length;i++){
            if (cs[i] != '.')
            lhs.add(cs[i]);
        }
        System.out.println(lhs);
    }

}
