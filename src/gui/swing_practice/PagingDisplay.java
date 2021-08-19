package gui.swing_practice;

import jdbc.PasswordDAO;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

/**
 * @author mo7984130
 * @Classname PagingDisplay
 * @Description TODO
 * @Date 2021/8/18 10:23 下午
 */
public class PagingDisplay {

    public PasswordDAO passwordDAO = new PasswordDAO();

    public int rowCount = passwordDAO.getRowCount();

    public int displayNumberPerPage;

    public int nowWhere;

    public int pageNumber;

    public PasswordTableModel ptm = new PasswordTableModel();

    public JTable table;



    List l;

    /**
     * 根据每页显示数初始化分页显示
     * @param displayNumberPerPage 每页显示数
     */
    public PagingDisplay(int displayNumberPerPage){
        this.displayNumberPerPage = displayNumberPerPage;
        this.pageNumber = (int) Math.ceil((float) rowCount/ (float) displayNumberPerPage);
        ptm.all = passwordDAO.list(0,displayNumberPerPage);
        ptm.rowCount = ptm.all.size();
        table = new JTable(ptm);
        table.updateUI();
        nowWhere = 1;
    }

    /**
     * 更新UI
     * 在对数据库有操作时使用
     */
    public void updateUI(){
        rowCount = passwordDAO.getRowCount();
        if (rowCount > pageNumber*displayNumberPerPage){
            pageNumber++;
            nowWhere++;
            ptm.all = passwordDAO.list((nowWhere-1)*displayNumberPerPage,displayNumberPerPage);
            ptm.rowCount = ptm.all.size();
        }else if (rowCount <= (pageNumber-1)*displayNumberPerPage){
            pageNumber--;
            nowWhere--;
            ptm.all = passwordDAO.list((nowWhere-1)*displayNumberPerPage,displayNumberPerPage);
            ptm.rowCount = ptm.all.size();
        }
        table.updateUI();
    }

    /**
     * 主页
     * @param f 主窗口
     * @return 主页按钮监听器
     */
    public ActionListener homePage(JFrame f){
        return homePageE->{
            if (nowWhere<=1){
                JOptionPane.showMessageDialog(f,"已经是第一页了");
            }else {
                ptm.all = passwordDAO.list(0,displayNumberPerPage);
                ptm.rowCount = ptm.all.size();
                table.updateUI();
                nowWhere = 1;
            }
        };
    }

    /**
     * 上一页
     * @param f 主窗口
     * @return 上一页按钮监听器
     */
    public ActionListener previous(JFrame f){
        return previousE->{
            if (nowWhere<=1){
                JOptionPane.showMessageDialog(f,"已经是第一页了");
            }else {
                ptm.all = passwordDAO.list((nowWhere-2)*displayNumberPerPage , displayNumberPerPage);
                ptm.rowCount = ptm.all.size();
                table.updateUI();
                nowWhere -= 1;
            }
        };
    }

    public String[] getPageNumberList(){
        String[] ss = new String[pageNumber];
        for (int i = 1 ; i<= pageNumber ; i++){
            ss[i-1] = String.valueOf(i);
        }
        return ss;
    }

    /**
     * 页码
     * @return 页码监听器
     */
    public ItemListener pageNumberListener(){
        return new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent pageNumberE) {
                nowWhere = Integer.parseInt((String) pageNumberE.getItem());
                ptm.all = passwordDAO.list((nowWhere-1)*displayNumberPerPage , displayNumberPerPage);
                table.updateUI();
            }
        };
    }

    /**
     * 下一页
     * @param f 主窗口
     * @return 下一页按钮监听器
     */
    public ActionListener next(JFrame f){
        return nextE->{
            if (nowWhere == pageNumber){
                JOptionPane.showMessageDialog(f,"已经是最后一页了");
            }else {
                ptm.all = passwordDAO.list(nowWhere * displayNumberPerPage , displayNumberPerPage);
                ptm.rowCount = ptm.all.size();
                table.updateUI();
                nowWhere += 1;
            }
        };
    }

    /**
     * 末页
     * @param f 主窗口
     * @return 末页按钮监听器
     */
    public ActionListener lastPage(JFrame f){
        return lastPageE->{
            if (nowWhere == pageNumber){
                JOptionPane.showMessageDialog(f,"已经是最后一页了");
            }else {
                ptm.all = passwordDAO.list((pageNumber-1) * displayNumberPerPage , displayNumberPerPage);
                ptm.rowCount = ptm.all.size();
                table.updateUI();
                nowWhere = pageNumber;
            }
        };
    }

}
