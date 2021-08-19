package test;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLException;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author mo7984130
 * @Classname Show
 * @Description TODO
 * @Date 未知
 */

public class Show {

    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        //确定主页链接
        System.out.println("请输入小说主页链接");
        System.out.println("例: https://www.biquwx.la/0_376/ ");
        String link = s.nextLine();

        //确定文件存放位置
        System.out.println("请输入放置小说文件的地址");
        System.out.println("若不输入，这默认为本文件所在地址");
        String path = "";

        String pathUser = s.nextLine();
        if (pathUser.length() == 0){
            String path1 = String.valueOf(Show.class.getResource(""));
            char[] cs = path1.toCharArray();
            for (int i=5;i<cs.length;i++){
                path += cs[i];
            }
        }else {
            path = pathUser;
        }


        //默认运行一次，当连接不上链接时（也就是出现SSLException异常时），runTime会+1，也就是仔运行一次
        int runTimes = 1;
        for (int runtime=0;runtime<runTimes;runtime++){
            try{

                //新建URL对象
                URL url = new URL(link);
                //打开连接
                URLConnection urlConnection = url.openConnection();
                //建立Http的连接
                HttpsURLConnection connection = (HttpsURLConnection) urlConnection;
                //新建流
                InputStreamReader isr = new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);
                //现在网页中的内容已经读进了BufferedReader中。
                System.out.println("连接成功！");

                //新建一个容器，容器的长度会随内容的增加而增加，不用担心数组越界的问题
                ArrayList al = new ArrayList();
                //while循环，一直执行直到，line == null时停止，也就是网页内容读完后停止。
                while (true){
                    //新建字符串，用于储存一行的内容
                    String line = br.readLine();
                    //如果line == null ，则跳出循环
                    if (line == null){
                        break;
                    }
                    //如果line ！= null，那么把字符串添加进容器中。
                    else {
                        al.add(line);
                    }
                }

                //关闭流
                br.close();
                isr.close();

                //while循环完毕后，网页中的内容也都被放进了容器中
                //现在将容器中的内容放入一个字符串数组中
                //字符串的长度就为容器的大小
                String[] str = new String[al.size()];
                for (int i=0;i<str.length;i++){
                    str[i] = String.valueOf(al.get(i));
                }

                //如果出现乱码，则重新运行
                //其实，没多大用，我也不知道为什么会出现乱码。
                //但一般等会在运行就好了
                //如果有办法的，可以在评论中告诉我，谢谢。

                if (str[0].contains("�")){
                    System.out.println(str[0]);
                    System.out.println("出现乱码，重新连接中...");
                    Thread.sleep(3000);
                    runTimes++;
                    continue;
                }

                //初始化标题
                String name = "";
                //初始化简介
                String description = "";

                /*
                通过观察我们可以发现，一行的字符串中，我们所需的全在""中。
                那么我们可以，以"为分割符，创建一个字符串数组，然后提取我们所需的。
                 */

                //创建循环，判断我们所需内容
                for (int i = 0;i<str.length;i++){
                    if (str[i].contains("property=\"og:title\"")){
                        //创建一个字符串数组，以"为分割符
                        String[] temp = str[i].split("\"");
                        //标题位于这个数组的第4位
                        name = temp[3];
                        continue;
                    }else if (str[i].contains("property=\"og:description\"")){
                        //简介不一样，它不仅占了一行，它占据了<meta property="og:description" content="/>这个标签。
                        //而随后的标签是<meta property="og:image"  那我们就可以检测这个标签，来作为结束。

                        //i1是用来计算开始到结束的行数的
                        int i1 = 1;
                        while (true){
                            if (str[i+i1].contains("<meta property=\"og:image\"")){
                                for (int i2 = i;i2<i1+i;i2++){
                                    //因为只有少量字符串拼接，所以我就用了String的+
                                    description += str[i2];
                                }
                                break;
                            }else{
                                i1++;
                            }
                        }

                        //现在description不仅包含了简介还包含了标签，所以要像小说名一样操纵一下。

                        //创建一个字符串数组，以"为分割符
                        String[] temp = description.split("\"");
                        //标题位于这个数组的第4位
                        description = temp[3];
                    }
                }
                System.out.printf("小说的名字为：%n" + name + "%n");
                System.out.printf( "小说的简介为: %n" + description);
                System.out.println("--------------");
                System.out.println("正在获取章节内容中");
                //小说名 和 简介 我们都有了
                //现在就是小说章节了
                //依旧由观察可知，章节在<div id="list"> 标签中
                //而每个章节的前面都会有href 和 title ，我们就从这俩下手
                //例：<a href="3102496.html" title="目前细胞的一些数据及第一卷解释">目前细胞的一些数据及第一卷解释</a>

                //现将包含章节的行提取到一个字符串数组，再进行操纵
                //同上依旧先用容器装，在转成字符串数组
                ArrayList al2 = new ArrayList();
                for (int i = 0;i<str.length;i++){
                    if (str[i].contains("href") && str[i].contains("title")){
                        al2.add(str[i]);
                    }
                }

                String[] chapter = new String[al2.size()];
                for (int i=0;i<chapter.length;i++){
                    chapter[i] = String.valueOf(al2.get(i));
                }
                //现在已经转完了，那么我们就可以进行操纵了
                /*
                for (int i =0;i<chapter.length;i++){
                    if (chapter[i].indexOf("?")>0){
                        System.out.println(i);
                        Thread.sleep(10000);
                    }
                }
                 */


                //创建文件放置目录
                File directory = new File(path + name);
                System.out.println("已创建文件" + directory);

                for (int i=0;i<chapter.length;i++){
                    //将一个字符串以"为分割符分割
                    String[] temp = chapter[i].split("\"");
                    //章节名位于第四位
                    String chapterName = temp[3];
                    //章节链接位于第二位
                    String chapterLink = temp[1];

                    //首先初始化上，下一章的名称
                    String nextChapterName = "";
                    String beforeChapterName = "";
                    if (i != chapter.length-1){
                        //获取下一章的名称
                        String[] temp1 = chapter[i+1].split("\"");
                        nextChapterName =  temp1[3];
                        if(nextChapterName.indexOf("?")>0){
                            nextChapterName = nextChapterName.replace("?","%3F");
                        }
                    }

                    if (i != 0){
                        //获取上一章的名称
                        String[] temp2 = chapter[i-1].split("\"");
                        beforeChapterName = temp2[3];
                        if(beforeChapterName.indexOf("?")>0){
                            beforeChapterName = beforeChapterName.replace("?","%3F");
                        }
                    }

                    //现在要读取章节中我们需要的内容

                    //创建流来输入
                    //新建一个文件，文件名为获得的章节名
                    File f = new File(path + name + "/" + chapterName + ".html");
                    System.out.print("正在创建文件" + f + "             ");
                    if (!f.exists()){
                        f.getParentFile().mkdirs();
                    }
                    try (
                            FileOutputStream fos = new FileOutputStream(f);
                            PrintWriter pw = new PrintWriter(fos)
                    ){

                        //新建URL对象
                        URL url1 = new URL(link + chapterLink);
                        //打开连接
                        URLConnection urlConnection1 = url1.openConnection();
                        //建立Http的连接
                        HttpsURLConnection connection1 = (HttpsURLConnection) urlConnection1;
                        //新建流
                        InputStreamReader isr1 = new InputStreamReader(connection1.getInputStream(), StandardCharsets.UTF_8);
                        BufferedReader br1 = new BufferedReader(isr1);
                        //现在网页中的内容已经读进了BufferedReader中。
                        //打开一个一个章节的源码，我们可以看正文部分前面都有空格标识符&nbsp;  所以我们可以从这个下手.
                        //例:  &nbsp;&nbsp;&nbsp;&nbsp;在城市的街道上，所有的虚民都在疯狂地奔跑着，在大地的震颤之下，它们纷纷从建筑之中逃了出来……

                        //用一个容器，和一个字符串数组就够了
                        //用StringBuffer更节约性能
                        ArrayList al3 = new ArrayList();
                        while (true){
                            String line = br1.readLine();
                            if (line == null){
                                break;
                            }else {al3.add(line);}
                        }

                        String[] content = new String[al3.size()];
                        for (int i1= 0;i1<al3.size();i1++){
                            content[i1] = String.valueOf(al3.get(i1));
                        }

                        StringBuffer sb = new StringBuffer();
                        for (int i1 = 0;i1<content.length;i1++){
                            if (content[i1].contains("&nbsp;")){
                                sb.append(content[i1]);
                            }
                        }

                        //我们要创建一个html文件，用于实现方向键换章。
                        //这里可以读取一个文件模版，但我懒的做了所以直接写这了.
                        pw.println("<!DOCTYPE html>");
                        pw.println("<html lang=\"en\">");
                        pw.println("<head>");
                        pw.println("    <meta charset=\"UTF-8\">");
                        pw.println("    <title>"+ chapterName + "</title>");
                        pw.println("    <script>function onDocKeydown(e) {e = e || window.event;if (e.keyCode==39) {");
                        pw.println("                window.location.href=\"" + directory + "/"+ nextChapterName + ".html" +"\";");
                        pw.println("            }else if (e.keyCode==37){");
                        pw.println("                window.location.href=\"" + directory + "/" + beforeChapterName + ".html" + "\";");
                        pw.println("            }}document.onkeydown = onDocKeydown;</script>");
                        pw.println("</head>");
                        pw.println("<body>");
                        pw.println("<div align=\"center\">");
                        pw.println("    <h1>"+ chapterName +"</h1></br></br>");
                        pw.println(sb);
                        pw.println("</div>");
                        pw.println("</body>");
                        pw.println("</html>");
                        System.out.println("文件创建完毕");

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            } catch (Exception e) {
                //如果异常为SSLException，那么跳出循环，在运行一次
                if (e instanceof SSLException){
                    System.out.println("出现异常：未连接成功");
                    System.out.println("尝试再次运行...");
                    runTimes++;
                }
                e.printStackTrace();
            }
        }
    }
}
