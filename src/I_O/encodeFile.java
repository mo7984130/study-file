package I_O;

import java.io.*;

public class encodeFile {

    //将一个文本文件加密
    /*
    加密算法：
   数字：
    如果不是9的数字，在原来的基础上加1，比如5变成6, 3变成4
    如果是9的数字，变成0
   字母字符：
    如果是非z字符，向右移动一个，比如d变成e, G变成H
    如果是z，z->a, Z-A。
    字符需要保留大小写
   非字母字符：
    比如',&^ 保留不变，中文也保留不变
     */
    public static void encrypt(File originalFile,File result){
        try(FileReader fr = new FileReader(originalFile) ;
            FileWriter fw = new FileWriter(result)
        ) {
            //将原文件中的内容读取到流中
            char[] cs = new char[(int)originalFile.length()];
            fr.read(cs);
            //判断并进行加密
            for (int i=0;i<cs.length;i++){
                if (Character.isDigit(cs[i])){
                    if (cs[i] == '9')
                        cs[i] = '0';
                    else {
                        int c = (int)cs[i]+1;
                        cs[i] = (char)c;
                    }
                }
                else if (cs[i]>='A' && cs[i]<='Z'){
                    if (cs[i] == 'Z')
                        cs[i] = 'A';
                    else{ cs[i]+=1; }
                }
                else if (cs[i]>='a' && cs[i]<='z'){
                    if (cs[i] == 'z')
                        cs[i] = 'a';
                    else { cs[i]+=1; }
                }
            }
            //将流写入到新文件中
            fw.write(cs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //将一个用encrypt加密的文件解密
    //原理同加密
    public static void decrypt(File beforeDecryption,File result){
        try (FileReader fr = new FileReader(beforeDecryption) ;
             FileWriter fw = new FileWriter(result)
        ){
            char[]cs = new char[(int) beforeDecryption.length()];
            fr.read(cs);
            for (int i=0;i<beforeDecryption.length();i++){
                if (Character.isDigit(cs[i])){
                    if (cs[i] == '0')
                        cs[i] = '9';
                    else {
                        int c = (int)cs[i]-1;
                        cs[i] = (char)c;
                    }
                }
                else if (cs[i]>='A' && cs[i]<='Z'){
                    if (cs[i] == 'A')
                        cs[i] = 'Z';
                    else{ cs[i]-=1; }
                }
                else if (cs[i]>='a' && cs[i]<='z'){
                    if (cs[i] == 'a')
                        cs[i] = 'z';
                    else { cs[i]-=1; }
                }
            }
            fw.write(cs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        encrypt(new File("/Users/apple/Downloads/test/加密前.txt"),new File("/Users/apple/Downloads/test/加密后.encrypt"));
        decrypt(new File("/Users/apple/Downloads/test/加密后.encrypt"),new File("/Users/apple/Downloads/test/解密后.txt"));
    }
}
