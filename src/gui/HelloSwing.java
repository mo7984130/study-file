package gui;

import javax.swing.*;

/**
 * @author mo7984130
 * @Classname HelloSwing
 * @Description TODO
 * @Date 2021/8/11 5:42 下午
 */
public class HelloSwing {

    public static void main(String[] args) {

        JFrame f = new JFrame("这是个gui");

        f.setSize(400,300);

        //位置线程
        PositionThread positionThread = new PositionThread(f);
        positionThread.setDaemon(true);
        positionThread.start();

        //启用绝对定位
        f.setLayout(null);

        //按钮
        JButton b = new JButton("这是个按钮");
        b.setBounds(50, 50, 280, 30);
        f.add(b);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

}
