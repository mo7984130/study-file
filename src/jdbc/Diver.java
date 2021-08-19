package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Diver {

    public static void main(String[] args) {

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
                String sql = "insert into Heros values(" + i +",'英雄 " + i + "',"  + Math.random()*1000 + "," + (int)(Math.random()*1000) + ")";
                s.execute(sql);
                System.out.println("英雄" + i + "插入成功");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
