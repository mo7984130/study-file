package jdbc;

import interfaces.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mo7984130
 * @Classname PasswordDAO
 * @Description TODO
 * @Date 2021/8/15 10:40 下午
 */
public class PasswordDAO implements DAO<Password> {

    public PasswordDAO(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接
     * @return 数据库连接
     * @throws SQLException 数据库连接异常
     */
    Connection getSqlConnection() throws SQLException{
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/SQL?characterEncoding=UTF-8",
                "root", "mo7984130");
    }

    @Override
    public void add(Password password) {
        //language=MySQL
        String sql = "insert into password values (null,?,?)";
        try(
                Connection c = getSqlConnection();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {

            ps.setString(1,password.userName);
            ps.setString(2,password.password);
            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Password password) {
        //language=MySQL
        String sql = "delete from password where id = ?";
        try(
                Connection c = getSqlConnection();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {

        ps.setInt(1,password.id);
        ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Password password) {
        //language=MySQL
        String sql = "update password set userName = ? , password = ? where id = ?";
        try(
                Connection c = getSqlConnection();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {

        ps.setString(1,password.userName);
        ps.setString(2,password.password);
        ps.setInt(3,password.id);
        ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Password get(int id){
        //language=MySQL
        String sql = "select * from password where id = ?";
        try(
                Connection c = getSqlConnection();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {

            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                return new Password(rs.getInt("id"),rs.getString("userName"),rs.getString("password"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean queryUserName(String userName){
        //language=MySQL
        String sql = "select * from password where userName = ?";
        try(
                Connection c = getSqlConnection();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {

        ps.setString(1,userName);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            return true;
        }else {
            return false;
        }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean queryUserNameAndPassword(String userName , String password){
        //language=MySQL
        String sql = "select * from password where userName = ? and password = ?";
        try(
                Connection c = getSqlConnection();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {

            ps.setString(1,userName);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                return true;
            }else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getRowCount(){
        //language=MySQL
        String sql = "select count(*) from password";
        try(
                Connection c = getSqlConnection();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {

            ResultSet rs = ps.executeQuery();
            int total = 0;
            while (rs.next()){
                total = rs.getInt(1);
            }
            return total;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getColumnCount(){
        //language=MySQL
        String sql = "select * from password";
        try(
                Connection c = getSqlConnection();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {

            ResultSetMetaData rsMd = ps.getMetaData();
            return rsMd.getColumnCount();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Password> list() {
        List<Password> l = new ArrayList();
        //language=MySQL
        String sql = "select * from password";
        try(
                Connection c = getSqlConnection();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                l.add(get(rs.getInt("id")));
            }
            return l;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Password> list(int start , int count){
        List<Password> list = new ArrayList<>();
        //language=MySQL
        String sql = "select * from password limit ? , ?";
        try(
                Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/SQL?characterEncoding=UTF-8",
                        "root", "mo7984130");
                PreparedStatement ps = c.prepareStatement(sql)
        ) {

        ps.setInt(1,start);
        ps.setInt(2,count);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            list.add(get(rs.getInt("id")));
        }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public String[] oneDimensionalArray(){
        String[] ss = null;
        //language=MySQL
        String sql = "select * from password";
        try(
                Connection c = getSqlConnection();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {

            ResultSetMetaData rsMd = ps.getMetaData();
            int size = rsMd.getColumnCount();
            ss = new String[size];
            for (int i = 0 ;i<size;i++){
                ss[i] = rsMd.getColumnName(i+1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ss;
    }

}
