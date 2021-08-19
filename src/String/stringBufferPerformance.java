package String;

import java.util.Random;

public class stringBufferPerformance {

    //生成1位长度的随机字符串
    //然后,先使用String的+,连接10000个随机字符串,计算消耗的时间
    //然后,再使用StringBuffer连接10000个随机字符串,计算消耗的时间

    //创建一个方法，用于获取随机字符串。
    public static String getRandomString(){
        //1位字符对应一个1位长度的随机字符串
        char[] cs = new char[1];
        for (int i = 0;i<cs.length;i++){
            cs[i] = (char)(new Random().nextInt(91)+32);
            //用ASCII码来获取字符（32-122）。
            //Random().nextInt(num)可以获取一个位于[0，num]区间的整数（包括0，不包括num）
        }
        //返回字符串
        return String.valueOf(cs);
    }

    public static void main(String[] args){

        //记录开始时间，方便记录拼接时间
        long time = System.currentTimeMillis();

        //首先初始化一个字符串
        String str1 = getRandomString();
        for (int i=0;i<10000;i++){
            //使用String的+拼接
            str1 += getRandomString();
        }
        //记录此方法所用时间（要减去初始时间）
        long time1 = System.currentTimeMillis() - time ;
        System.out.println("使用字符串连接的+方式，连接10000次，耗时" + time1 + "毫秒");

        //初始化字符串
        String str2 = getRandomString();
        //新建StringBuffer
        StringBuffer sb = new StringBuffer(str2);
        for (int i=0;i<10000;i++){
            //使用StringBuffer的append方法拼接字符串
            sb.append(getRandomString());
        }
        //记录此方法所用时间（同样减去初始时间和上个方法的时间）
        long time2 = System.currentTimeMillis()-time1-time;
        System.out.println("使用StringBuffer的方式，连接10000次，耗时" + time2 + "毫秒");
    }
}
