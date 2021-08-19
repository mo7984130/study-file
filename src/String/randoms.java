package String;

import java.util.Random;

public class randoms {


    //48-57 数字
    //65-90 大写字母
    //97-122 小写字母
    public static char getRandomChar() {
        Random random = new Random();
        int x = random.nextInt(3);
        return switch (x) {
            case 0 -> (char) ((short) random.nextInt(9) + 48);
            case 1 -> (char) ((short) random.nextInt(25) + 65);
            case 2 -> (char) ((short) random.nextInt(25) + 97);
            default -> (char) 1;
        };
    }
    /*获取随机3位字符
    大小写字母，数字
     */

    public static char getChar(int i){
        if (0<=i && i<10)
            return (char)(short)(i+48);
        else if (10<=i && i<36)
            return (char)(short)(i+55);
        else if (36<=i && i<62)
            return (char)(short)(i+61);
        return 1;
    }
    //按顺序获取字符

    public static void main(String[] args) {
        char [] passport = new char [3];
        System.out.print("随机3位字符为：");
        for (int i = 0; i < 3; i++) {
            passport [i] = getRandomChar();
        }
        System.out.println(passport);
        String stringPassport = new String(passport);
        //输出

        System.out.println("开始破解");
        int count = 0;
        char[] cs = new char[3];
        for (int i1 = 0;i1<62;i1++){
            cs[0] = getChar(i1);
            for (int i2 = 0;i2<62;i2++){
                cs[1] = getChar(i2);
                for (int i3 = 0;i3<62;i3++){
                    cs[2] = getChar(i3);
                    String ans = new String(cs);
                    //获取顺序字符
                    count++;
                    //计数
                    if (ans.equals(stringPassport)) {
                        System.out.println("运算次数为：" + count);
                        System.out.print("密码为:" + ans);
                    }
                    //比较并输出
                }
            }
        }
    }
}
