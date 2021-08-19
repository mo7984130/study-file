package gui.swing_practice;

import gui.PositionThread;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/*
改进复制文件夹,提供进度条，把文件复制的进度显示出来。
 */
/**
 * @author mo7984130
 * @Classname CopyFolderProgressBar
 * @Description TODO
 * @Date 2021/8/16 5:49 下午
 */
public class CopyFolderProgressBar {

    public static void main(String[] args) {
        JFrame f = new JFrame("");

        f.setSize(300,150);

        //位置线程
        PositionThread positionThread = new PositionThread(f);
        positionThread.setDaemon(true);
        positionThread.start();

        JPanel upsidePanel = new JPanel();
        upsidePanel.setLayout(new GridLayout(2,2));
        upsidePanel.setBounds(0,0,300,75);

        JLabel lSourceFileAddress = new JLabel("源文件地址");
        upsidePanel.add(lSourceFileAddress);
        JTextField tfSourceFileAddress = new JTextField();
        tfSourceFileAddress.setText("/Users/apple/Downloads/test");
        tfSourceFileAddress.setPreferredSize(new Dimension(150,30));
        upsidePanel.add(tfSourceFileAddress);

        JLabel lCopyTo = new JLabel("复制到");
        upsidePanel.add(lCopyTo);
        JTextField tfCopyTo = new JTextField();
        tfCopyTo.setText("/Users/apple/Downloads/test");
        tfCopyTo.setPreferredSize(new Dimension(150,30));
        upsidePanel.add(tfCopyTo);

        JPanel downSidePanel = new JPanel();
        downSidePanel.setLayout(new GridLayout(1,3));
        downSidePanel.setBounds(0,75,300,30);

        JButton startCopyingButton = new JButton("开始复制");
        downSidePanel.add(startCopyingButton);
        JLabel lFileCopyProgress = new JLabel("文件复制进度");
        downSidePanel.add(lFileCopyProgress);
        JProgressBar pb = new JProgressBar();
        pb.setMaximum(100);
        pb.setStringPainted(true);
        downSidePanel.add(pb);

        startCopyingButton.addActionListener(new ActionListener() {

            long srcFileLength = 0;
            volatile long destFileLength = 0;

            @Override
            public void actionPerformed(ActionEvent e) {

                getFileLength(tfSourceFileAddress.getText());
                //noinspection AlibabaAvoidManuallyCreateThread
                new Thread(()->{
                    while (pb.getValue()<100){
                        int progress = (int) (destFileLength*100/srcFileLength);
                        pb.setValue(progress);
                    }
                    JOptionPane.showMessageDialog(f,"复制完成");
                    pb.setValue(0);
                    srcFileLength = 0;
                    destFileLength = 0;
                }).start();
                //noinspection AlibabaAvoidManuallyCreateThread
                new Thread(()->{ copyFolder(tfSourceFileAddress.getText(),tfCopyTo.getText()); }).start();
            }
            private void getFileLength(String srcPath){
                File file = new File(srcPath);
                if (!file.exists()){
                    return;
                }
                if (file.isFile()){
                    srcFileLength = file.length() + srcFileLength;
                }else {
                    File[] files = file.listFiles();
                    for (File eachFile : files){
                        getFileLength(eachFile.getPath());
                    }
                }
            }

            private void getDestFileLength(String srcPath){
                File file = new File(srcPath);
                destFileLength += file.length();
            }

            private void copyFile(String srcPath , String destination){
                File file = new File(srcPath);
                //源文件不存在，那么退出
                if (!file.exists()){
                    JOptionPane.showMessageDialog(f,"文件不存在!");
                    return;
                }
                File destFile = new File(destination);
                if (!destFile.exists()){
                    destFile.getParentFile().mkdirs();
                }
                try (
                        FileReader fr = new FileReader(file);
                        BufferedReader br = new BufferedReader(fr);
                        FileWriter fw = new FileWriter(destFile);
                        PrintWriter pw = new PrintWriter(fw)
                ){
                    getDestFileLength(srcPath);
                    while (true){
                        String line = br.readLine();
                        if (line == null){
                            break;
                        }
                        else {
                            pw.println(line);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            private void copyFolder(String srcPath , String destination){
                File file = new File(srcPath);
                if (!file.exists()){
                    JOptionPane.showMessageDialog(f,"文件不存在!");
                    return;
                }
                if (file.isFile()){
                    copyFile(srcPath,destination + File.separator + file.getName());
                }else {
                    File[] fs = file.listFiles();
                    for (File eachFile : fs){
                        copyFolder(eachFile.getPath(),destination + File.separator + file.getName());
                    }
                }
            }
        });

        //启用绝对定位
        f.add(upsidePanel);
        f.add(downSidePanel);
        f.setLayout(new GridLayout(2,1));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

}
