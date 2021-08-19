package gui;

import javax.swing.*;

/**
 * @author mo7984130
 * @Classname Modal
 * @Description TODO
 * @Date 2021/8/14 3:00 下午
 */
public class Modal {

    public static void main(String[] args) {

        JFrame f = new JFrame("这是个gui");

        f.setSize(800,600);

        //位置线程
        PositionThread positionThread = new PositionThread(f);
        positionThread.setDaemon(true);
        positionThread.start();

        //启用绝对定位
        f.setLayout(null);

        JButton b = new JButton("打开一个模态窗口");
        b.setBounds(400,300,100,100);
        b.addActionListener(e->{
            JDialog d = new JDialog(f);
            d.setModal(true);
            d.setSize(400,300);
            d.setLocation(400,300);

            JButton d_b = new JButton("锁定大小");
            d_b.setBounds(100,100,100,100);
            d_b.addActionListener(e1->{
                if (d_b.getText().equals("锁定大小")){
                    d_b.setText("解锁大小");
                    d.setResizable(false);
                }else {
                    d_b.setText("锁定大小");
                    d.setResizable(true);
                }
            });

            d.add(d_b);
            d.setLayout(null);
            d.setVisible(true);
        });

        f.add(b);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

}
