package methods;

import SomeLittleProgram.Reptile;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * @author mo7984130
 * @Classname Url
 * @Description TODO
 * @Date 2021/8/10 2:48 下午
 */

public class Url {

    /**
     * 用户输入主页路径
     * @return 主页路径
     */
    public static String userEntersHomepageLink(){
        Scanner s = new Scanner(System.in);
        System.out.println("请输入主页链接");
        System.out.println("例:https://www.bxwxorg.com/read/370");
        System.out.println("请勿在末尾带空格");
        return s.nextLine();
    }

    /**
     * 用户输入保存位置
     * 若用户没有输入那么自动放在本文件的位置
     * @return 保存路径
     */
    public static String userEntersSavePath(){
        Scanner s = new Scanner(System.in);
        System.out.println("请输入保存位置");
        System.out.println("若不输入，则默认为本文件所在位置");
        String savePath = s.nextLine();
        if (savePath.length() == 0){
            savePath = Objects.requireNonNull(Reptile.class.getResource("")).getPath();
        }
        return savePath;
    }

    /**
     * 获取链接中文章的内容并重新放在一个html文件中
     * 并追加左右键换页功能
     * @param link 章节链接
     * @param savePath 保存地址
     */
    public static void chapter(String link,String savePath){
    }

    /**
     * 将网页中的内容存入一个容器中
     * @param url 网页链接
     * @return 容器
     */
    public static ArrayList<String> webPageToList(URL url){
        ArrayList l = new ArrayList();
        HttpsURLConnection connection = null;
        try {
            connection = (HttpsURLConnection) url.openConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (
                InputStreamReader isr = new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);
        ){

            while (true){
                String line = br.readLine();

                if (line != null){
                    l.add(line);
                }else {
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return l;
    }

    /**
     * 将网页中的内容存入一个容器中
     * 若出现乱码则重新运行来避免乱码
     * ...但一般没用
     * @param url 网页链接
     * @return 容器
     */
    public static ArrayList<String> webPageToListNoGarbled(URL url){
        ArrayList l = new ArrayList();

        int runTimes = 2;
        for (int runTime = 1;runTime<runTimes;runTime++){

        }
        HttpsURLConnection connection = null;
        try {
            connection = (HttpsURLConnection) url.openConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (
                InputStreamReader isr = new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);
        ){

            while (true){
                String line = br.readLine();

                if (line != null){

                    if (line.indexOf("�")>0){
                        System.out.println("出现乱码!");
                        System.out.println("重新连接中...");
                        runTimes++;
                        break;
                    }

                    l.add(line);
                }else {
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return l;
    }

}
