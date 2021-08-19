package gui;

import javax.swing.*;
import java.io.*;

/**
 * @author mo7984130
 * @Classname PositionThread
 * @Description TODO
 * @Date 2021/8/11 6:00 下午
 */
public class PositionThread extends Thread{

    JFrame j;

    public PositionThread(JFrame j){
        this.j = j;
    }

    @Override
    public void run(){

        File f = new File("/Users/apple/IdeaProjects/study-file/src/gui/position.position");
        if (!f.exists()){
            f.mkdirs();
        }

        try (
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr)
                ){

                String line = br.readLine();
                if (line != null){
                    j.setLocation(Integer.parseInt(line.split(",")[0]),Integer.parseInt(line.split(",")[1]));
                }else {
                    j.setLocation(200,200);
                }
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (true){
            try (
                    FileWriter fw = new FileWriter(f);
                    PrintWriter pw = new PrintWriter(fw)
            ){

                pw.print(j.getX() + "," + j.getY());
                pw.flush();
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
