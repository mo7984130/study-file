package I_O;

import methods.getFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class steam {

    //将文件以MB的大小拆分成分文件
    //b为拆分的大小，单位MB
    public static void splitFiles(File f,int b){
        String fileName = getFile.getFileName(f);
        String fileExtension = getFile.getFileExtension(f);
        byte [] all = new byte[b*1000*1024];
        //若文件大小小于b，那么拆分0次
        //否则拆分文件大小/b+1次
        long numberOfDivisions = 0;
        if (f.length()%all.length != 0){
            numberOfDivisions = f.length()/all.length+1;
        }

        //初始化输出流，以便关闭
        FileOutputStream fos = null;
        try(
                FileInputStream fis = new FileInputStream(f);
                ) {
            for (int i = 0;i<numberOfDivisions;i++){
                File f1 = new File(f.getParentFile() + "/" + fileName +"-" + i + fileExtension) ;
                fos = new FileOutputStream(f1);
                int len = fis.read(all);
                fos.write(all,0,len);
                System.out.println("第" + i + "个文件为:" + f1 + "，大小为:" + f1.length());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //关闭输出流，以免异常时消耗内存
        finally {
            if (fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //将拆分的文件拼接成一个文件
    public static void splicingFiles(File f){
        //依旧初始化输出流
        FileInputStream fis1 = null;
        try (
                FileOutputStream fos = new FileOutputStream(f)
        ){
            int i = 0;
            while (true){
                File splitFiles = new File(f.getParentFile() + "/" + getFile.getFileName(f) + "-" + i + getFile.getFileExtension(f));
                //如果文件不存在，则跳出循环
                if (!splitFiles.exists()){
                    break;
                }
                fis1 = new FileInputStream(splitFiles);
                byte[] all = new byte[(int) splitFiles.length()];
                int len = fis1.read(all);
                fos.write(all,0,len);
                System.out.println("拼接第" + i + "个文件");
                i++;
                fis1.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //同上
        finally {
            if (fis1 != null){
                try {
                    fis1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args){
        String path = "/Users/apple/Downloads/temp/bg.psd";
        //splitFiles(new File(path),1);
        //splicingFiles(new File(path));
    }
}
