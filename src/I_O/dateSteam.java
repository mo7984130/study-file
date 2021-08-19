package I_O;


import java.io.*;

public class dateSteam {

    //使用缓存流把两个数字以字符串的形式写到文件里，再用缓存流以字符串的形式读取出来，然后转换为两个数字。
    public static void method1(File f, String num1, String num2){
        //缓存流写入
        try(
                FileWriter fw = new FileWriter(f);
                PrintWriter pw = new PrintWriter(fw);
                ){
            String num = num1 + "@" + num2;
            pw.write(num);
        }catch (Exception e){
            e.printStackTrace();
        }

        //缓存流读出
        try(
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr)
                ){
            while (true){
                String line = br.readLine();
                if (line == null){break;}
                else {
                    String[] str = line.split("@");
                    for (String s : str){
                        System.out.println(s);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //使用数据流DataOutputStream向文件连续写入两个数字，然后用DataInputStream连续读取两个数字.
    public static void method2(File f,String num1,String num2){
        try (
                FileOutputStream fos = new FileOutputStream(f);
                DataOutputStream dos = new DataOutputStream(fos);
                FileInputStream fis = new FileInputStream(f);
                DataInputStream dis = new DataInputStream(fis)
                ){
            //写入数字
            int num1_ = Integer.parseInt(num1);
            int num2_ = Integer.parseInt(num2);
            dos.writeInt(num1_);
            dos.writeInt(num2_);
            //读出数字
            System.out.println("已写入" + num1 + "和" + num2);
            int i0 = dis.readInt();
            int i1 = dis.readInt();
            System.out.println("读取到" + i0 +"和" + i1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        method2(new File("/Users/apple/Downloads/test/dateSteam.txt"),"121323","132131");
    }
}
