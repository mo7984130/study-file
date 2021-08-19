package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author mo7984130
 */
public class Query {

    public void addRandomHero(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("数据库驱动加载成功!");
        try(
                Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/SQL?characterEncoding=UTF-8",
                        "root", "mo7984130");
                Statement s = c.createStatement()
        ) {

            for (int i =1;i<=100;i++){
                String sql = "insert into Heroes values ( " + i + ",'英雄 " +i + "', " + Math.random()*1000 + "," + (int)(Math.random()*1000) + ")";
                s.execute(sql);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void list(int start,int count){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("数据库驱动加载成功!");
        try(
                Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/SQL?characterEncoding=UTF-8",
                        "root", "mo7984130");
                Statement s = c.createStatement()
        ) {

            for (int i = start +1;i<=start+count;i++){
                String sql = "select * from Hero where id = " + i;
                ResultSet rs = s.executeQuery(sql);
                while (rs.next()){
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    float hp = rs.getInt("hp");
                    int damage = rs.getInt("damage");
                    System.out.format("%d\t%s\t%f\t%d%n",id,name,hp,damage);
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
        String sql = "";
        try(
                Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/SQL?characterEncoding=UTF-8",
                        "root", "mo7984130");
        ) {



        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
