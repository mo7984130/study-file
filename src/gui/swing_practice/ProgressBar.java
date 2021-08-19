package gui.swing_practice;


/*
设计一个线程，每隔100毫秒，就把进度条的进度+1。
从0%一直加到100%
刚开始加的比较快，以每隔100毫秒的速度增加，随着进度的增加，越加越慢
 */

import gui.PositionThread;

import javax.swing.*;
import java.awt.*;

/**
 * @author mo7984130
 * @Classname ProgressBar
 * @Description TODO
 * @Date 2021/8/16 5:24 下午
 */
public class ProgressBar {

    public static void main(String[] args) {
        JFrame f = new JFrame("进度条");

        f.setSize(400,400);

        //位置线程
        PositionThread positionThread = new PositionThread(f);
        positionThread.setDaemon(true);
        positionThread.start();

        JProgressBar pb = new JProgressBar();
        pb.setMaximum(100);
        pb.setStringPainted(true);

        JButton progressBarButton = new JButton("开始");
        progressBarButton.addActionListener(e->{
            new Thread(()->{
                int gap = 100;
                int value = 0;
                while (pb.getValue()<=100){
                    value++;
                    pb.setValue(value);
                    try {
                        Thread.sleep(gap);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                    gap += 1;
                }
            }).start();
        });

        f.add(pb);
        f.add(progressBarButton);
        //启用绝对定位
        f.setLayout(new GridLayout(2,1));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

}
