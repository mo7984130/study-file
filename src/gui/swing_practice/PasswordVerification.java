package gui.swing_practice;

/*
准备两个JTextFiled,一个用于输入账号，一个用于输入密码。
再准备一个JButton，上面的文字是登陆
点击按钮之后，首先进行为空判断，如果都不为空，则把账号和密码，拿到数据库中进行比较(SQL语句判断账号密码是否正确)，
根据判断结果，使用JOptionPane进行提示。
 */

import jdbc.Password;
import jdbc.PasswordDAO;

import javax.swing.*;
import java.awt.*;

/**
 * @author mo7984130
 * @Classname PasswordVerification
 * @Description TODO
 * @Date 2021/8/16 8:39 上午
 */
public class PasswordVerification {

    public static void main(String[] args) {
        JFrame f = new JFrame("账号密码判断");
        f.setSize(400,300);

        PasswordDAO passwordDAO = new PasswordDAO();

        JLabel lUserName = new JLabel("用户名为:");
        JTextField tfUserName = new JTextField();
        tfUserName.setPreferredSize(new Dimension(100,30));
        JLabel lPassword = new JLabel("密码为:");
        JTextField tfPassword = new JTextField();
        tfPassword.setPreferredSize(new Dimension(100,30));
        JButton logButton = new JButton("登陆");
        logButton.setPreferredSize(new Dimension(80,30));
        logButton.addActionListener(logE->{
            if (tfUserName.getText().length()<1){
                JOptionPane.showMessageDialog(f,"用户名为空!");
            }else if (tfPassword.getText().length()<1){
                JOptionPane.showMessageDialog(f,"密码为空!");
            }else if (passwordDAO.queryUserNameAndPassword(tfUserName.getText(),tfPassword.getText())){
                JOptionPane.showMessageDialog(f,"账号密码正确");
            }else {
                JOptionPane.showMessageDialog(f,"账号密码错误");
            }
        });
        JButton registerButton = new JButton("注册");
        registerButton.setPreferredSize(new Dimension(80,30));
        registerButton.addActionListener(registerE->{
            if (tfUserName.getText().length()<1){
                JOptionPane.showMessageDialog(f,"用户名为空!");
            }else if (tfPassword.getText().length()<1){
                JOptionPane.showMessageDialog(f,"密码为空!");
            }else if (passwordDAO.queryUserName(tfUserName.getText())){
                JOptionPane.showMessageDialog(f,"账号已经被使用");
            }else {
                passwordDAO.add(new Password(tfUserName.getText(),tfPassword.getText()));
                JOptionPane.showMessageDialog(f,"注册成功!");
            }
        });

        f.add(lUserName);
        f.add(tfUserName);
        f.add(lPassword);
        f.add(tfPassword);
        f.add(logButton);
        f.add(registerButton);

        //启用绝对定位
        f.setLayout(new FlowLayout());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        tfUserName.grabFocus();
    }

}
