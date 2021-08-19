package methods;

import java.io.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static methods.getFile.getFileExtension;
import static methods.getFile.getFileName;

public class copyFiles {

    public static LinkedBlockingDeque l;
    public static ThreadPoolExecutor tpe;

    //复制文件到目标文件
    public static void copyFile(File srcFile,String destFile){

        //源文件不存在，那么退出
        if (!srcFile.exists()){
            System.out.println("文件不存在!");
            System.exit(0);
        }
        File df = new File(destFile);
        try (
                FileReader fr = new FileReader(srcFile);
                BufferedReader br = new BufferedReader(fr);
                FileWriter fw = new FileWriter(df);
                PrintWriter pw = new PrintWriter(fw)
                ){
            while (true){
                String line = br.readLine();
                if (line == null){
                    break;
                }
                else {
                    pw.println(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //复制文件夹
    public static void copyFolder(File srcFolder,String destFolder){
        File f = new File(destFolder);
        //如果目标文件夹不存在，则创建
        if (!f.exists()){
            f.getParentFile().mkdirs();
        }
        //如果是文件则直接调用copyFile
        if (srcFolder.isFile()){
            copyFile(srcFolder,destFolder);
        }
        //如果是文件夹则递归
        else {
            File[] sf = srcFolder.listFiles();
            for (int i = 0;i<sf.length;i++){
                copyFolder(sf[i],destFolder + "/"+ getFileName(sf[i]) + getFileExtension(sf[i]));
            }
        }
    }

    public static void search(File file,String str){
        File[] fl = file.listFiles();
        for (File f : fl) {
            if (f.isFile()) {
                try (
                        FileReader fr = new FileReader(f);
                        BufferedReader br = new BufferedReader(fr)
                ) {
                    while (true) {
                        String line = br.readLine();
                        if (line == null) {
                            break;
                        }
                        if (line.indexOf(str) > 0) {
                            System.out.println(f);
                            break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                search(f, str);
            }
        }
    }

    public static void match(File f,String str){
        try(
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr)
                ){
            while (true){
                String line = br.readLine();
                if (line == null)
                    break;
                if (line.indexOf(str)>0) {
                    System.out.println(f);
                    break;
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void threadSearch(File file,String str){
        File[] fl = file.listFiles();
        for (File f : fl) {
            if (f.isFile()) {
                if (getFileExtension(f).equals(".java")) {
                    tpe.execute(() -> {
                        System.out.println("线程启动，在" + f + "中搜索中");
                        match(f,str);
                    });
                }
            } else {
                threadSearch(f,str);
            }
        }
    }

    public static void main(String[] args){
        copyFiles.l = new LinkedBlockingDeque();
        copyFiles.tpe = new ThreadPoolExecutor(10,15,10, TimeUnit.SECONDS,l);
        threadSearch(new File("/Users/apple/IdeaProjects/study-file"),"main");
    }

}
