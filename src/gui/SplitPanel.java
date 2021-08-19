package gui;

import javax.swing.*;
import java.awt.*;

/**
 * @author mo7984130
 * @Classname SplitPanel
 * @Description TODO
 * @Date 2021/8/15 5:19 下午
 */
public class SplitPanel {

    public static void main(String[] args) {
        JFrame f = new JFrame("这是个gui");

        f.setSize(400,300);

        //位置线程
        PositionThread positionThread = new PositionThread(f);
        positionThread.setDaemon(true);
        positionThread.start();

        //启用绝对定位
        f.setLayout(null);

        JPanel pRight = new JPanel();
        pRight.setBounds(400,300,300,300);
        pRight.setBackground(Color.darkGray);

        JLabel l = new JLabel();
        ImageIcon iMinecraft = new ImageIcon("src/image/icon.png");
        ImageIcon iAnnie = new ImageIcon("src/image/annie.jpg");
        ImageIcon iGareen = new ImageIcon("src/image/gareen.jpg");
        l.setIcon(iMinecraft);
        pRight.add(l);

        JPanel pLeft = new JPanel();
        pLeft.setBounds(0,0,100,300);
        JButton minecraftButton = new JButton("我的世界");
        minecraftButton.addActionListener(eMinecraft->{l.setIcon(iMinecraft);});
        JButton annieButton = new JButton("安妮");
        annieButton.addActionListener(eAnnie->{l.setIcon(iAnnie);});
        JButton gareenButton = new JButton("盖伦");
        gareenButton.addActionListener(eGareen->{l.setIcon(iGareen);});
        pLeft.add(minecraftButton);
        pLeft.add(annieButton);
        pLeft.add(gareenButton);

        JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,pLeft,pRight);
        sp.setDividerLocation(100);
        f.setContentPane(sp);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

}
