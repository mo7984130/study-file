package gui.swing_practice;

import jdbc.Password;
import jdbc.PasswordDAO;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * @author mo7984130
 * @Classname PasswordTableModel
 * @Description TODO
 * @Date 2021/8/16 8:46 下午
 */
public class PasswordTableModel extends AbstractTableModel {

    private final PasswordDAO passwordDAO = new PasswordDAO();

    String[] columnNames = passwordDAO.oneDimensionalArray();

    List<Password> all = null;

    int rowCount;

    public void init(){
        all = passwordDAO.list();
    }

    @Override
    public int getRowCount() {
        return rowCount;
    }

    @Override
    public int getColumnCount() {
        return passwordDAO.getColumnCount();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return all.get(rowIndex).stringList()[columnIndex];
    }
}
