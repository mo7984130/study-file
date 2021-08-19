package gui;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * @author mo7984130
 * @Classname TabbedPanel
 * @Description TODO
 * @Date 2021/8/15 6:29 下午
 */
public class TabbedPanel {

    public static void main(String[] args) {

        JFrame backF = new JFrame();
        backF.setBounds(200,200,400,300);
        backF.setLayout(new FlowLayout());
        backF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lPath = new JLabel("路径:");
        JTextField tfPath = new JTextField();
        tfPath.setPreferredSize(new Dimension(150,30));
        backF.add(lPath);
        backF.add(tfPath);

        JButton chooseButton = new JButton("确定");
        backF.setPreferredSize(new Dimension(80,30));
        backF.add(chooseButton);
        chooseButton.addActionListener(e->{
            for(int i1 = 0 ;i1<=0;i1++){
                String path = tfPath.getText();
                File file = new File(path);
                if (!file.exists()){
                    tfPath.setText("文件不存在!");
                    continue;
                }
                File[] fileList = file.listFiles();

                JFrame f = new JFrame("");
                f.setSize(1000,750);
                JTabbedPane tp = new JTabbedPane();
                ImageIcon icon = new ImageIcon("src/image/j.png");

                //启用绝对定位
                f.setLayout(null);
                f.setContentPane(tp);

                int i = 0;
                for (File eachFile : fileList){
                    if (eachFile.getName().toLowerCase().endsWith(".java")){

                        JTextArea ta = new JTextArea();

                        try (
                                FileReader fr = new FileReader(eachFile);
                                BufferedReader br = new BufferedReader(fr)
                        ){
                            while (true){
                                String line = br.readLine();
                                if (line != null){
                                    ta.append(line + System.getProperty("line.separator"));
                                }else {
                                    break;
                                }
                            }

                            JScrollPane sp = new JScrollPane(ta);
                            tp.add(sp);
                            tp.setTitleAt(i,eachFile.getName());
                            tp.setIconAt(i,icon);
                            i++;
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }

                    }
                }
                f.setVisible(true);
            }
        });
        backF.setVisible(true);


    }

}
