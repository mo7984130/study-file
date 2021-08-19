package gui.swing_practice;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;

/**
 * @author mo7984130
 * @Classname JtfMethods
 * @Description TODO
 * @Date 2021/8/17 5:57 下午
 */
public class JtfMethods {

    public static boolean jFrameCheckEmpty(JFrame f, JTextField tf, String message){
        if (tf.getText().length()<1){
            JOptionPane.showMessageDialog(f,message + "为空!");
            return true;
        }
        return false;
    }

    public static boolean jFrameCheckNumber(JFrame f , JTextField tf , String message){
        if (!Pattern.compile("[0-9]*").matcher(tf.getText()).matches()){
            JOptionPane.showMessageDialog(f, message + "不为数字");
            return false;
        }else {
            return true;
        }
    }

    public static boolean jDialogCheckEmpty(JDialog f, JTextField tf, String message){
        if (tf.getText().length()<1){
            JOptionPane.showMessageDialog(f,message + "为空!");
            return true;
        }
        return false;
    }

    public static boolean jDialogCheckNumber(JDialog f , JTextField tf , String message){
        if (!Pattern.compile("[0-9]*").matcher(tf.getText()).matches()){
            JOptionPane.showMessageDialog(f, message + "不为数字");
            return false;
        }else {
            return true;
        }
    }

    public static JTextField addLabelAndTextFieldInFrame(JFrame f , String title){
        JLabel l = new JLabel(title);
        JTextField tf = new JTextField();
        tf.setPreferredSize(new Dimension(80,30));
        f.add(l);
        f.add(tf);
        return tf;
    }

    public static JTextField addLabelAndTextFieldInDialog(JDialog p , String title){
        JLabel l = new JLabel(title);
        JTextField tf = new JTextField();
        tf.setPreferredSize(new Dimension(80,30));
        p.add(l);
        p.add(tf);
        return tf;
    }
}
