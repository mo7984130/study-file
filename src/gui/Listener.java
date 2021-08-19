package gui;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author mo7984130
 * @Classname Listener
 * @Description TODO
 * @Date 2021/8/12 2:38 下午
 */
public class Listener {

    public static void main(String[] args) {

        JFrame f = new JFrame("这是个gui");

        f.setSize(400,300);

        //位置线程
        PositionThread positionThread = new PositionThread(f);
        positionThread.setDaemon(true);
        positionThread.start();

        //启用绝对定位
        f.setLayout(null);

         final JLabel l = new JLabel();

        ImageIcon i = new ImageIcon("/Users/apple/IdeaProjects/study-file/src/image/icon.png");
        l.setIcon(i);
        l.setBounds(50,50,i.getIconWidth(),i.getIconWidth());

        f.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {

                if (e.getKeyCode() == 38){
                    l.setLocation(l.getX(),l.getY()-10);
                }else if (e.getKeyCode() == 40){
                    l.setLocation(l.getX(),l.getY()+10);
                }else if (e.getKeyCode() == 37){
                    l.setLocation(l.getX()-10,l.getY());
                }else if (e.getKeyCode() == 39){
                    l.setLocation(l.getX()+10,l.getY());
                }

            }
        });

        //按钮

        JButton b = new JButton("隐藏图片");
        b.setBounds(150,200,100,30);
        b.addActionListener(e->{
            if ("显示图片".equals(b.getText())){
                b.setText("隐藏图片");
                l.setVisible(true);
            }else {
                b.setText("显示图片");
                l.setVisible(false);
            }
        });

        f.add(b);
        f.setFocusable(true);
        f.add(l);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

}
