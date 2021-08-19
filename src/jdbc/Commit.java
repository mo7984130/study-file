package jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author mo7984130
 * @Classname Commit
 * @Description TODO
 * @Date 2021/8/10 11:48 上午
 */
public class Commit {

    /**
     * 删除数据库前n条数据
     * @param c 数据库连接
     * @param n n条数据
     */
    public static void deleteTheFirstNData(Connection c, int n){
        List<Integer> l = new ArrayList<>();
        //language=MySQL
        String selectSql = "select * from Hero limit 0,?";
        try (
                PreparedStatement ps = c.prepareStatement(selectSql)
        ){
            ps.setInt(1,n);
            ResultSet rs= ps.executeQuery();
            while (rs.next()){
                l.add(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //language=MySQL
        String deleteSql = "delete from Hero where id = ?";
        try (
                PreparedStatement ps = c.prepareStatement(deleteSql)
        ){
            c.setAutoCommit(false);
            for (int i : l){
                ps.setInt(1,i);
                System.out.format("试图删除id=%d的数据%n",i);
                ps.execute();
            }
            System.out.println("是否要提交数据?(Y/N)");
            Scanner s = new Scanner(System.in);
            while (true){
                String enter = s.nextLine();
                if (enter.equals("Y")) {
                    System.out.println("确认删除");
                    c.commit();
                    break;
                } else if (enter.equals("N")){
                    System.out.println("取消删除");
                    System.exit(0);
                } else {
                    System.out.println("请输入Y/N");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("数据库驱动加载成功!");
        //language=MySQL
        String sql = "insert into Hero values (null,?,?,?)";
        try(
                Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/SQL?characterEncoding=UTF-8",
                        "root", "mo7984130");
                PreparedStatement ps = c.prepareStatement(sql)
        ) {

            deleteTheFirstNData(c,20000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
