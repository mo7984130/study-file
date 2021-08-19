package gui.swing_practice;

import gui.PositionThread;
import jdbc.Password;
import jdbc.PasswordDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @author mo7984130
 * @Classname UserNameAndPasswordControl
 * @Description TODO
 * @Date 2021/8/16 8:44 下午
 */
public class UserNameAndPasswordControl {


    static int displayNumberPerPage = 10;
    static PagingDisplay pagingDisplay = new PagingDisplay(displayNumberPerPage);
    static PasswordDAO passwordDAO = pagingDisplay.passwordDAO;
    static PasswordTableModel ptm = pagingDisplay.ptm;

    public static void main(String[] args) {
        //主界面
        JFrame f = new JFrame("表格");
        f.setSize(450,300);
        f.setLayout(new BorderLayout());

        //位置线程
        PositionThread positionThread = new PositionThread(f);
        positionThread.setDaemon(true);
        positionThread.start();

        //表格面板
        JScrollPane sp = new JScrollPane(pagingDisplay.table);
        f.add(sp,BorderLayout.CENTER);

        //功能面板
        //用于放置按钮面板 和 换页面板
        JPanel functionPanel = new JPanel();
        functionPanel.setLayout(new GridLayout(2,1));
        functionPanel.setSize(400,100);
        f.add(functionPanel , BorderLayout.SOUTH);

        //按钮面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        functionPanel.add(buttonPanel);

        //增加按钮
        JButton addButton = new JButton("增加");
        addButton.setPreferredSize(new Dimension(80,30));
        addButton.addActionListener(addActionListener(f,pagingDisplay.table));
        buttonPanel.add(addButton);

        //删除按钮
        JButton deleteButton = new JButton("删除");
        deleteButton.setPreferredSize(new Dimension(80,30));
        deleteButton.addActionListener(deleteActionListener(f,pagingDisplay.table));
        buttonPanel.add(deleteButton);

        //编辑按钮
        JButton updateButton = new JButton("编辑");
        updateButton.setPreferredSize(new Dimension(80,30));
        updateButton.addActionListener(updateActionListener(f,pagingDisplay.table));
        buttonPanel.add(updateButton);

        //换页面板
        JPanel changePagePanel = new JPanel();
        changePagePanel.setLayout(new FlowLayout());
        changePagePanel.setSize(400,100);
        functionPanel.add(changePagePanel);

        //首页按钮
        JButton homeButton = new JButton("首页");
        homeButton.setPreferredSize(new Dimension(80,30));
        homeButton.addActionListener(pagingDisplay.homePage(f));
        changePagePanel.add(homeButton);

        //上一页按钮
        JButton previousButton = new JButton("上一页");
        previousButton.setPreferredSize(new Dimension(80,30));
        previousButton.addActionListener(pagingDisplay.previous(f));
        changePagePanel.add(previousButton);

        //页码
        JComboBox pageNumberComboBox =  new JComboBox(pagingDisplay.getPageNumberList());
        pageNumberComboBox.setPreferredSize(new Dimension(80,30));
        pageNumberComboBox.addItemListener(pagingDisplay.pageNumberListener());
        changePagePanel.add(pageNumberComboBox);

        //下一页按钮
        JButton nextButton = new JButton("下一页");
        nextButton.setPreferredSize(new Dimension(80,30));
        nextButton.addActionListener(pagingDisplay.next(f));
        changePagePanel.add(nextButton);

        //末页按钮
        JButton lastPageButton = new JButton("末页");
        lastPageButton.setPreferredSize(new Dimension(80,30));
        lastPageButton.addActionListener(pagingDisplay.lastPage(f));
        changePagePanel.add(lastPageButton);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    /**
     * 放置文本和文本域到JDialog中。
     * 例： 文本 文本域
     * @param p 被放置的JDialog
     * @param title 文本域前的文字
     * @return 文本域
     */
    public static JTextField addLabelAndTextField(JDialog p , String title){
        JLabel l = new JLabel(title);
        JTextField tf = new JTextField();
        tf.setPreferredSize(new Dimension(80,30));
        p.add(l);
        p.add(tf);
        return tf;
    }

    /**
     * 增加按钮功能实现
     * @param f 基准窗口JFrame
     * @param t 要更新的表格
     * @return 监听器
     */
    private static ActionListener addActionListener(JFrame f , JTable t){
          return addE -> {
              //弹出新窗口
              JDialog addDialog = new JDialog(f);
              addDialog.setBounds(f.getX(),f.getY(),400,100);
              addDialog.setLayout(new FlowLayout());
              //输入
              JTextField tfUserName = addLabelAndTextField(addDialog,"用户名");
              JTextField tfPassword = addLabelAndTextField(addDialog,"密码");
              //添加按钮
              JButton enterButton = new JButton("添加");
              //添加按钮功能实现
              enterButton.addActionListener(dialogAddE->{
                  enterButton.setEnabled(false);
                  //检查空项
                  if (JtfMethods.jDialogCheckEmpty(addDialog,tfUserName,"用户名")) {}
                  else if (JtfMethods.jDialogCheckEmpty(addDialog,tfPassword,"密码")){}
                  else{
                      new SwingWorker<>() {
                          @Override
                          //swing线程
                          protected Object doInBackground() {
                              //增加
                              passwordDAO.add(new Password(tfUserName.getText(),tfPassword.getText()));
                              pagingDisplay.updateUI();
                              JOptionPane.showMessageDialog(addDialog,"添加完成");
                              enterButton.setEnabled(true);
                              return null;
                          }
                      }.execute();
                  }
              });
              enterButton.setPreferredSize(new Dimension(80,30));
              addDialog.add(enterButton);
              addDialog.setVisible(true);
          };
    }

    /**
     * 删除按钮功能实现
     * @param f 基准窗口JFrame
     * @param t 要更新的表格
     * @return 监听器
     */
    private static ActionListener deleteActionListener(JFrame f , JTable t){
        return deleteE -> {

            int option = JOptionPane.showConfirmDialog(f,"是否删除?");
            //判断是否删除
            if (JOptionPane.OK_OPTION == option){
                int row = t.getSelectedRow();
                //未选中时
                if (row == -1){
                    JOptionPane.showMessageDialog(f,"请先选中一行");
                }else {

                    new SwingWorker<>() {

                        @Override
                        protected Object doInBackground() {
                            //删除
                            Password password = ptm.all.get(row);
                            passwordDAO.delete(password);
                            int id = password.id;
                            pagingDisplay.updateUI();
                            JOptionPane.showMessageDialog(f,"id=" + id + "的用户已被删除");
                            return null;
                        }
                    }.execute();

                }
            }

        };
    }

    /**
     * 增加按钮功能实现
     * @param f 基准窗口JFrame
     * @param t 要更新的表格
     * @return 监听器
     */
    private static ActionListener updateActionListener(JFrame f , JTable t){
        return updateE -> {
            //选中行
            int row = t.getSelectedRow();
            //判断是否选中了行
            if (row == -1){
                JOptionPane.showMessageDialog(f,"请先选中一行");
            }else {
                //新窗口
                JDialog updateDialog = new JDialog(f);
                updateDialog.setBounds(f.getX(),f.getY(),400,100);
                updateDialog.setLayout(new FlowLayout());
                //输入
                JTextField tfUserName = addLabelAndTextField(updateDialog,"用户名:");
                JTextField tfPassword = addLabelAndTextField(updateDialog,"密码:");
                //修改按钮
                JButton updateButton = new JButton("修改");
                updateDialog.add(updateButton);
                //修改按钮功能实现
                updateButton.addActionListener(dialogUpdateE->{
                    updateButton.setEnabled(false);
                    //判断是否为空
                    if (JtfMethods.jDialogCheckEmpty(updateDialog,tfUserName,"用户名")){}
                    if (JtfMethods.jDialogCheckEmpty(updateDialog,tfPassword,"密码")){}
                    else {
                        new SwingWorker<>() {
                            @Override
                            protected Object doInBackground() {
                                //更新
                                Password password = ptm.all.get(row);
                                password.userName = tfUserName.getText();
                                password.password = tfPassword.getText();
                                passwordDAO.update(password);
                                pagingDisplay.updateUI();
                                JOptionPane.showMessageDialog(f,"编辑完成");
                                updateButton.setEnabled(true);
                                return null;
                            }
                        }.execute();
                    }
                });
                updateDialog.setVisible(true);
            }
        };
    }

}
