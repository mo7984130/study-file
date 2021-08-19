package SomeLittleProgram;

import methods.Url;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
  @author mo7984130
 * @Classname Reptile
 * @Description TODO
 * @Date 2021/8/10 1:11 下午
 */
public class Reptile {

    /**
     *爬取笔下文学(www.bxwxorg.com)的小说
     */

    public static void main(String[] args) {


        //用户输入主页链接
        String homepageLink = Url.userEntersHomepageLink();
        //用户输入保存位置
        String savePath = Url.userEntersSavePath();

        List<String> l = new ArrayList<>();

        int runTimes = 2;
        for (int runTime = 1;runTime<runTimes;runTime++){
            try {
                l = Url.webPageToListNoGarbled(new URL(homepageLink));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            System.out.println("连接成功!");

            String novelName = "";

            for (int i = 0;i<l.size();i++){
                String line = l.get(i);

                if (line.indexOf("<div id=\"info\">")>0){
                    novelName = l.get(i+1);
                    novelName = novelName.replaceAll("<h1>","");
                    novelName = novelName.replaceAll("</h1>","");
                    novelName = novelName.trim();
                    System.out.format("小说名称:\t%s%n",novelName);

                    String[] novelAuthorArray = l.get(i+2).split(">");
                    String novelAuthor = novelAuthorArray[2];
                    novelAuthor = novelAuthor.replaceAll("</a","");
                    System.out.format("小说作者:\t%s%n",novelAuthor);

                }else if (line.indexOf("<div id=\"intro\">")>0){
                    String novelIntroduction = line;
                    String[] novelIntroductionArray = novelIntroduction.split("<p>");
                    novelIntroduction = novelIntroductionArray[1];
                    novelIntroduction = novelIntroduction.replaceAll("</p>","");
                    System.out.format("小说简介:\t%s%n",novelIntroduction);
                }

            }

            //创建保存文件夹
            savePath = savePath+novelName;
            File saveFolder = new File(savePath);
            if (!saveFolder.exists()){
                saveFolder.mkdirs();
            }

            for (int i = 3435;i<l.size();i++){
                String line = l.get(i);
                if (line != null){
                    if (line.indexOf("<a href")>0 && line.indexOf("章")>0){

                        String chapterName = line.split(">")[2].replaceAll("</a","");

                        String nextChapterName = "";
                        if (i+1 <= l.size()){
                            nextChapterName = l.get(i+1).split(">")[2].replaceAll("</a","");
                        }

                        String beforeChapterName = "";
                        if (i-1 >=2924){
                            beforeChapterName = l.get(i-1).split(">")[2].replaceAll("</a","");
                        }


                        ArrayList<String> contentArrayList = null;
                        try {
                            contentArrayList = Url.webPageToList(new URL("https://www.bxwxorg.com" + l.get(i).split("\"")[1]));
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }

                        for (int i2 = 0;i2<contentArrayList.size();i2++){
                            String chapterLine = contentArrayList.get(i2);
                            StringBuilder content = new StringBuilder();
                            if (chapterLine.indexOf("<div id=\"content\">")>0){
                                int i1 = i2+2;
                                while (true){
                                    String contentLine = contentArrayList.get(i1);
                                    if (contentLine.indexOf("<p style=\"color: #999;font-size: 10px;line-height: 18px;\">")>0){
                                        break;
                                    }
                                    content.append(contentArrayList.get(i1));
                                    i1++;
                                }

                                File f = new File(savePath + "/" + chapterName + ".html");
                                System.out.format("正在创建文件%s\t",f);
                                try (
                                        FileOutputStream fos = new FileOutputStream(f);
                                        PrintWriter pw = new PrintWriter(fos)
                                        ){

                                    pw.println("<!DOCTYPE html>");
                                    pw.println("<html lang=\"en\">");
                                    pw.println("<head>");
                                    pw.println("    <meta charset=\"UTF-8\">");
                                    pw.println("    <title>"+ chapterName + "</title>");
                                    pw.println("    <script>function onDocKeydown(e) {e = e || window.event;if (e.keyCode==39) {");
                                    pw.println("                window.location.href=\"" + savePath + "/"+ nextChapterName + ".html" +"\";");
                                    pw.println("            }else if (e.keyCode==37){");
                                    pw.println("                window.location.href=\"" + savePath + "/" + beforeChapterName + ".html" + "\";");
                                    pw.println("            }}document.onkeydown = onDocKeydown;</script>");
                                    pw.println("</head>");
                                    pw.println("<body>");
                                    pw.println("<div align=\"center\">");
                                    pw.println("    <h1>"+ chapterName +"</h1></br></br>");
                                    pw.println(content);
                                    pw.println("</div>");
                                    pw.println("</body>");
                                    pw.println("</html>");
                                    pw.flush();
                                    System.out.format("文件创建完毕%n");
                                    break;

                                }catch (Exception e){
                                    e.printStackTrace();
                                }

                            }
                        }
                    }

                    }else{
                        break;
                    }
                }

        }

    }

}
