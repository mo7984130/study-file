package gui.swing_practice;

import gui.PositionThread;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * @author mo7984130
 * @Classname FindTheContentsOfFile
 * @Description TODO
 * @Date 2021/8/19 1:26 下午
 */
public class FindTheContentsOfFile {

    public static void main(String[] args) {
        JFrame f = new JFrame("");
        f.setSize(400,100);

        //位置线程
        PositionThread positionThread = new PositionThread(f);
        positionThread.setDaemon(true);
        positionThread.start();

        JTextField tfPath = JtfMethods.addLabelAndTextFieldInFrame(f,"路径");
        JTextField tfMatchWords = JtfMethods.addLabelAndTextFieldInFrame(f,"匹配词");
        JButton searchButton = new JButton("开始搜索");
        f.add(searchButton);
        searchButton.addActionListener(searchE->{
            if (JtfMethods.jFrameCheckEmpty(f,tfPath,"路径")){}
            else if (JtfMethods.jFrameCheckEmpty(f,tfMatchWords,"匹配词")){}
            else {
                File file = new File(tfPath.getText());
                if (!file.exists()){
                    JOptionPane.showMessageDialog(f,"文件不存在!");
                }else {
                    new SwingWorker<>() {
                        @Override
                        protected Object doInBackground() throws Exception {
                            searchButton.setEnabled(false);
                            searchButton.setText("正在搜索中...");
                            int total = search(file,tfMatchWords.getText()).size();
                            JOptionPane.showMessageDialog(f,"共找到" + total + "个文件");
                            searchButton.setEnabled(true);
                            searchButton.setText("开始搜索");
                            return null;
                        }
                    }.execute();
                }

            }

        });

        f.setLayout(new FlowLayout());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    private static ArrayList<File> search(File file, String matchWords){
        ArrayList<File> l = new ArrayList<>();
        if (file.isFile()){

            try (
                    FileReader fr = new FileReader(file);
                    BufferedReader br = new BufferedReader(fr)
                    ){

                while (true){
                    String line = br.readLine();
                    if (line == null){
                        break;
                    }else {
                        if (line.indexOf(matchWords)>0){
                            l.add(file);
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return l;
        }else {
            File[] files = file.listFiles();
            for (File eachFile : files){
                l.addAll(search(eachFile , matchWords));
            }
        }
        return l;
    }

}
