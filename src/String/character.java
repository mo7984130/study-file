package String;
import java.util.Scanner;

public class character {
    public static void main (String[] args){
        Scanner s = new Scanner(System.in);
        System.out.println("请输入：");
        String str =  s.next();
        char [] ch = str.toCharArray();
        for(char each : ch) {
            if(Character.isUpperCase(each) || Character.isDigit(each))
                System.out.print(each);
        }
    }
}
