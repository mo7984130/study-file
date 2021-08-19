package gui.swing_practice;

import gui.PositionThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

/*
计算复利
 */

/**
 * @author mo7984130
 * @Classname CompoundInterestCalculator
 * @Description TODO
 * @Date 2021/8/16 4:13 下午
 */
public class CompoundInterestCalculator {

    public static String[] compoundInterestCalculate(float startingCapital , float annualIncome ,
                                                     float compoundInterestYears , float additionalFundsEveryYear){
        String[] strings = new String[3];
        float principalSum = 0;
        float interestSum = 0;
        float principalAndInterestSum = 0;
        for (int year = 1 ; year<=compoundInterestYears ; year++){
            if (year == 1){
                principalSum = startingCapital;
                interestSum = startingCapital*annualIncome;
            }else {
                principalSum += additionalFundsEveryYear;
                interestSum += (principalAndInterestSum + additionalFundsEveryYear) *annualIncome;
            }
            principalAndInterestSum = principalSum + interestSum;
        }
        strings[0] = String.valueOf(principalSum);
        strings[1] = String.valueOf(interestSum);
        strings[2] = String.valueOf(principalAndInterestSum);
        return strings;
    }

    public static void addLabel(JPanel p , String title){
        JLabel l = new JLabel(title);
        p.add(l);
    }

    public static void addLabelAndTextField(JPanel p , String title){
        JLabel l = new JLabel(title);
        JTextField tf = new JTextField();
        tf.setPreferredSize(new Dimension(80,30));
        p.add(l);
        p.add(tf);
    }

    public static JTextField addLabelAndTextFieldReturnJTextField(JPanel p , String title){
        JLabel l = new JLabel(title);
        JTextField tf = new JTextField();
        tf.setPreferredSize(new Dimension(80,30));
        p.add(l);
        p.add(tf);
        return tf;
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("复利计算器");
        f.setSize(400,450);

        //位置线程
        PositionThread positionThread = new PositionThread(f);
        positionThread.setDaemon(true);
        positionThread.start();

        JPanel pEnter = new JPanel();
        pEnter.setLayout(new GridLayout(4,3,10,10));

        JTextField tfStartingCapital = addLabelAndTextFieldReturnJTextField(pEnter,"起始资金");
        addLabel(pEnter,"￥");
        JTextField tfAnnualIncome = addLabelAndTextFieldReturnJTextField(pEnter,"每年收益");
        addLabel(pEnter,"%");
        JTextField tfCompoundInterestYears = addLabelAndTextFieldReturnJTextField(pEnter,"复利年数");
        addLabel(pEnter,"年");
        JTextField tfAdditionalFundsEveryYear = addLabelAndTextFieldReturnJTextField(pEnter,"每年追加资金");
        addLabel(pEnter,"￥");
        pEnter.setBounds(10,10,400,200);

        JButton calculateButton = new JButton("计算");
        calculateButton.setBounds(160,210,80,30);

        JPanel pResult = new JPanel();
        pResult.setLayout(new GridLayout(3,3,10,10));
        JTextField tfPrincipalSum = addLabelAndTextFieldReturnJTextField(pResult,"本金和");
        addLabel(pResult,"￥");
        JTextField tfInterestSum = addLabelAndTextFieldReturnJTextField(pResult,"利息和");
        addLabel(pResult,"￥");
        JTextField tfPrincipalAndInterestSum = addLabelAndTextFieldReturnJTextField(pResult,"本息和");
        addLabel(pResult,"￥");
        pResult.setBounds(10,250,400,150);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!checkNumber(tfStartingCapital,"起始资金")){}
                else if (!checkNumber(tfAnnualIncome,"每年收益")){}
                else if (!checkNumber(tfCompoundInterestYears,"复利年数")){}
                else if (!checkNumber(tfAdditionalFundsEveryYear,"每年追加资金")){}
                else {
                    String[] strings = compoundInterestCalculate(Float.parseFloat(tfStartingCapital.getText()) , (float) (Float.parseFloat(tfAnnualIncome.getText())*0.01),
                            Float.parseFloat(tfCompoundInterestYears.getText()) , Float.parseFloat(tfAdditionalFundsEveryYear.getText()) );
                    tfPrincipalSum.setText(strings[0]);
                    tfInterestSum.setText(strings[1]);
                    tfPrincipalAndInterestSum.setText(strings[2]);
                }
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

        f.add(pResult);
        f.add(calculateButton);
        f.add(pEnter);
        //启用绝对定位
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

}
