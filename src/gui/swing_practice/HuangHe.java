package gui.swing_practice;

/*
浙江温州最大皮革厂江南皮革厂倒闭了，王八蛋老板黄鹤吃喝嫖赌，
欠下了3.5个亿，带着他的小姨子跑了!
我们没有办法，拿着钱包抵工资!原价都是一百多、两百多、三百多的钱包，
现在全部只卖二十块，统统只要二十块!黄鹤王八蛋，你不是人!
我们辛辛苦苦给你干了大半年，你不发工资，你还我血汗钱，还我血汗钱!
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

/**
 * @author mo7984130
 * @Classname HuangHe
 * @Description TODO
 * @Date 2021/8/16 1:50 下午
 */
public class HuangHe {

    public static void main(String[] args) {

        JFrame f = new JFrame("黄鹤");
        f.setSize(400,400);

        JPanel pEnter = new JPanel();
        pEnter.setLayout(new GridLayout(4, 3, 10, 10));
        JLabel lPlaceName = new JLabel("地名:");
        pEnter.add(lPlaceName);
        JTextField tfPlaceName = new JTextField("");
        pEnter.add(tfPlaceName);

        JLabel lTypeOfCompany = new JLabel("公司类型:");
        pEnter.add(lTypeOfCompany);
        JTextField tfTypeOfCompany = new JTextField("");
        pEnter.add(tfTypeOfCompany);

        JLabel lCompanyName = new JLabel("公司名称");
        pEnter.add(lCompanyName);
        JTextField tfCompanyName = new JTextField("");
        pEnter.add(tfCompanyName);

        JLabel lBossName = new JLabel("老板名称:");
        pEnter.add(lBossName);
        JTextField tfBossName = new JTextField("");
        pEnter.add(tfBossName);

        JLabel lMoney = new JLabel("金额");
        pEnter.add(lMoney);
        JTextField tfMoney = new JTextField("");
        pEnter.add(tfMoney);

        JLabel lProduct = new JLabel("产品:");
        pEnter.add(lProduct);
        JTextField tfProduct = new JTextField("");
        pEnter.add(tfProduct);

        JLabel lPriceMeasurementUnit = new JLabel("价格计量单位:");
        pEnter.add(lPriceMeasurementUnit);
        JTextField tfPriceMeasurementUnit = new JTextField("");
        pEnter.add(tfPriceMeasurementUnit);
        pEnter.setBounds(10,10,375,120);

        JButton generateButton = new JButton("生成");
        generateButton.setBounds(150,150,80,30);
        JTextArea ta = new JTextArea();
        ta.setBounds(10,210,375,120);
        ta.setLineWrap(true);

        //启用绝对定位
        f.add(pEnter);
        f.add(generateButton);
        f.add(ta);
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkEmpty(tfPlaceName,"地名")){}
                else if (checkEmpty(tfTypeOfCompany,"公司类型")){}
                else if (checkEmpty(tfCompanyName,"公司名称")){}
                else if (checkEmpty(tfBossName,"老板名称")){}
                else if (!checkNumber(tfMoney,"金额")){}
                else if (checkEmpty(tfProduct,"产品")){}
                else if (checkEmpty(tfPriceMeasurementUnit,"价格计量单位")){}
                else {
                    ta.setText(String.format("%s最大%s%s倒闭了，王八蛋老板%s吃喝嫖赌，欠下了%s个亿，"
                            + "带着他的小姨子跑了!我们没有办法，拿着%s抵工资!原价都是一%s多、两%s多、三%s多的%s，"
                            + "现在全部只卖二十块，统统只要二十块!%s王八蛋，你不是人!我们辛辛苦苦给你干了大半年，"
                            + "你不发工资，你还我血汗钱，还我血汗钱!",tfPlaceName.getText(),tfTypeOfCompany.getText(),
                            tfCompanyName.getText(),tfBossName.getText(),tfMoney.getText(),tfProduct.getText(),
                            tfPriceMeasurementUnit.getText(),tfPriceMeasurementUnit.getText(),tfPriceMeasurementUnit.getText(),
                            tfProduct.getText(),tfBossName.getText()));
                }

            }
            private boolean checkEmpty(JTextField tf , String message){
                if (tf.getText().length()<1){
                    JOptionPane.showMessageDialog(f,message + "为空!");
                    return true;
                }
                return false;
            }
            private boolean checkNumber(JTextField tf , String message){
                if (tf.getText().length()<1){
                    JOptionPane.showMessageDialog(f,message + "为空!");
                    return false;
                }else if (!Pattern.compile("[0-9]*").matcher(tf.getText()).matches()){
                    JOptionPane.showMessageDialog(f, message + "不为数字");
                    return false;
                }else {
                    return true;
                }
            }
        });
    }

}
