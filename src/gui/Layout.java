package gui;

import javax.swing.*;
import java.awt.*;

/**
 * @author mo7984130
 * @Classname Layout
 * @Description TODO
 * @Date 2021/8/14 3:13 下午
 */
public class Layout {

    public static void main(String[] args) {
        JFrame f = new JFrame("这是个gui");

        f.setSize(400,300);

        //位置线程
        PositionThread positionThread = new PositionThread(f);
        positionThread.setDaemon(true);
        positionThread.start();


        f.setLayout(new GridLayout(4,5));
        f.add(new JButton("7"));
        f.add(new JButton("8"));
        f.add(new JButton("9"));
        f.add(new JButton("/"));
        f.add(new JButton("sq"));
        f.add(new JButton("4"));
        f.add(new JButton("5"));
        f.add(new JButton("6"));
        f.add(new JButton("*"));
        f.add(new JButton("%"));
        f.add(new JButton("1"));
        f.add(new JButton("2"));
        f.add(new JButton("3"));
        f.add(new JButton("-"));
        f.add(new JButton("1/x"));
        f.add(new JButton("0"));
        f.add(new JButton("+/-"));
        f.add(new JButton("."));
        f.add(new JButton("+"));
        f.add(new JButton("="));





        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

}
