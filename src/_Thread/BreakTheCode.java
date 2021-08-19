package _Thread;

import java.util.ArrayList;
import java.util.List;

public class BreakTheCode {

    public static char getSequencedChar(int i){
        if (i>=0 && i<=9){
            return (char) (i+48);
        }else if (i>=10 &&  i<=35){
            return (char)(i+55);
        }else if (i>=36 && i<=61){
            return (char)(i+61);
        }
        return '?';
    }

    public static String getRandomString(int length){
        String str = "";
        for (int i = 0; i < length; i++) {
            int j = (int)(Math.random()*(2 + 1));
            switch(j) {
                case 0:
                    str += (char)(Math.random()*('9' - '0' + 1) + '0');
                    break;
                case 1:
                    str += (char)(Math.random()*('z' - 'a' + 1) + 'a');
                    break;
                case 2:
                    str += (char)(Math.random()*('Z' - 'A' + 1) + 'A');
            }
        }
        return str;
    }

    public static void main(String[] args) {
        String code = getRandomString(3);
        System.out.println("密码为：" + code);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<String> l = new ArrayList();

        Thread t1 = new Thread(() -> {
            boolean Boolean = false;
            for (int i1 =0;i1<62;i1++){
                for (int i2 = 0;i2<62;i2++){
                    for (int i3=0;i3<62;i3++){
                        String result = "";
                        result += getSequencedChar(i1);
                        result += getSequencedChar(i2);
                        result += getSequencedChar(i3);
                        l.add(result);
                        if (code.equals(result)){
                            System.out.println("已破解,密码为:" + result);
                            Boolean = true;
                            break;
                        }
                    }
                    if (Boolean == true)
                        break;
                }
                if (Boolean == true)
                    break;
            }
        });

        Thread t2 = new Thread(() -> {
            while (true){
                    if (l.isEmpty()){
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else {
                        String str = l.remove(0);
                        System.out.println("尝试密码:" + str);
                    }

            }

        });
        t2.setDaemon(true);

        t1.start();
        t2.start();
    }
}
