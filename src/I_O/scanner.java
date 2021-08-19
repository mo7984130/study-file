package I_O;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class scanner {

    //自动创建有一个属性的类文件。
    //通过控制台，获取类名，属性名称，属性类型，根据一个模板文件，自动创建这个类文件，并且为属性提供setter和getter
    public static void main(String[] args) {
        //Scanner读取控制台
        Scanner s = new Scanner(System.in);
        System.out.println("请输入类的名称：");
        String Class = s.nextLine();
        System.out.println("请输入属性的类型：");
        String type = s.nextLine();
        System.out.println("请输入属性的名称");
        String property = s.nextLine();
        System.out.println("替换后的内容：");

        //将属性的首字母大写
        char[] cs = property.toCharArray();
        cs[0] = Character.toUpperCase(cs[0]);
        String upProperty = new String(cs);

        //模版
        //其实用文件更好，懒的弄了
        String[] str = {
                "public class @class@ {",
                "    public @type@ @property@;",
                "    public @class@() {",
                "    }",
                "    public void set@Uproperty@(@type@  @property@){",
                "        this.@property@ = @property@;",
                "    }",
                "",
                "    public @type@  get@Uproperty@(){",
                "        return this.@property@;",
                "    }",
                "}",
        };

        //替换模版
        for (int i = 0;i< str.length;i++){
            str[i] = str[i].replaceAll("@class@",Class);
            str[i] = str[i].replaceAll("@type@",type);
            str[i] = str[i].replaceAll("@Uproperty@",upProperty);
            str[i] = str[i].replaceAll("@property@",property);
        }

        //控制台输出结果
        for (String string : str){
            System.out.println(string);
        }

        //输出结果文件
        File f = new File("/Users/apple/Downloads/test/" + Class + ".java");
        try(
                FileOutputStream fos = new FileOutputStream(f);
                PrintWriter pw = new PrintWriter(fos)
                ) {
            for (String string : str){
                pw.println(string);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        //说明文件位置
        System.out.println("文件保存在:" + f);

    }
}
