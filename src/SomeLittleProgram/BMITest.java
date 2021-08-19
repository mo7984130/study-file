package SomeLittleProgram;

import java.util.Scanner;

public class BMITest {

    static void BMI(float a , float b) {
        float BMI = b / a / a;
        //计算BMI

        if (0 >= a || a >= 3) {
            System.out.println("你。。。你。。。你是外星人？");
            System.exit(0);
        }
        //防止输错

        if (0 >= b || b >= 1000) {
            System.out.println("你。。。你。。。你是外星人？");
            System.exit(0);
        }
        //防止输错

        System.out.println("你的BMI是" + BMI);
        //输出BMI

        if (0 < BMI && BMI < 18.5)
            System.out.println("你体重过轻");
        else if (18.5 <= BMI && BMI < 24)
            System.out.println("你的体重正常");
        else if (24 <= BMI && BMI < 27)
            System.out.println("你的体重过重");
        else if (27 <= BMI && BMI < 30)
            System.out.println("你轻度肥胖");
        else if (30 <= BMI && BMI < 35)
            System.out.println("你中度肥胖");
        else if (35 <= BMI && BMI < 40)
            System.out.println("你重度肥胖");
        else if (0 >= BMI && BMI >= 40)
            System.out.println("你。。。你。。。你是外星人？");
        //判断健康情况

    }


    public static void main(String[] args){

        Scanner s = new Scanner(System.in);
        System.out.println("请输入你的身高（m）");
        float a = s.nextFloat();
        System.out.println("请输入你的体重(kg)");
        float b = s.nextFloat();
        //获取身高，体重
        BMI(a,b);
    }
}
