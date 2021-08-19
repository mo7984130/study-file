package arrayList;

import java.util.ArrayList;
import java.util.List;

public class _Collections {
    public static void main(String[] args){
        List<Integer> l = new ArrayList<>();
        for (int i =0;i<10;i++){
            l.add(i);
        }
        int num = 0;
        int num2 = 0;
        for (int i= 0;i<1000000;i++){
            java.util.Collections.shuffle(l);
            if (l.get(0) == 3){
                if (l.get(1) == 1){
                    if (l.get(2) == 4){
                        num2++;
                    }
                }
            }
            num = i+1;
        }
        System.out.println("概率为：" + (float)num2/num*100 + "%");
    }

}
