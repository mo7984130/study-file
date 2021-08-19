package gui.swing_practice;


/*
  在JTextField中输入数据，在旁边加一个按钮JButton,
  当点击按钮的时候，判断JTextFiled中有没有数据，
  并使用JOptionPane进行提示
 */

import javax.swing.*;
import java.awt.*;

/**
 * @author mo7984130
 * @Classname NullJudgment
 * @Description TODO
 * @Date 2021/8/15 10:19 下午
 */
public class NullJudgment {

    public static void main(String[] args) {

        JFrame f = new JFrame("为空判断");
        f.setSize(400,300);

        JTextField tf = new JTextField();
        tf.setPreferredSize(new Dimension(160,30));
        JButton b = new JButton("检测");
        b.setPreferredSize(new Dimension(80,30));
        b.addActionListener(e->{
            if (tf.getText().length()<1){
                JOptionPane.showMessageDialog(f,"文本内容为空!");
            }
        });
        f.add(tf);
        f.add(b);

        //启用绝对定位
        f.setLayout(new FlowLayout());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

    }

}
