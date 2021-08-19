package I_O;

import java.io.*;

public class buffered {

    //移除Java文件中的注释(不包括/* */类).
    public static void removeComments(File javaFile){

        //创建临时文件，用于使Buffered 和 Print 不同时读同一个文件
        File temp = new File("/Users/apple/Downloads/test/temp.txt");
        if (!temp.exists()){
            temp.getParentFile().mkdirs();
        }
        try (
                FileReader fr = new FileReader(javaFile);
                BufferedReader br = new BufferedReader(fr);
                FileWriter fw = new FileWriter(temp);
                PrintWriter pw = new PrintWriter(fw)
                ){
            while (true) {
                String line = br.readLine();
                if (line == null){break;}
                else {
                    //判断是否以"//"开头
                    if (line.trim().startsWith("//")) {
                        continue;
                    }
                    else {
                        //将行写入到临时文件中
                        pw.println(line);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //将行从临时文件中读出来，并写入原文件中
        try(
                FileReader fr = new FileReader(temp);
                BufferedReader br = new BufferedReader(fr);
                FileWriter fw = new FileWriter(javaFile);
                PrintWriter pw = new PrintWriter(fw)
                )
        {
            while (true){
                String line = br.readLine();
                if (line == null){break;}
                else {
                    pw.println(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //删除临时文件
        temp.deleteOnExit();
    }

    public static void main(String[] args){
        removeComments(new File("/Users/apple/Downloads/test/buffered.txt"));
    }
}
