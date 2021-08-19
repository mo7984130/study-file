package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mo7984130
 * @Classname ConnectionPool
 * @Description TODO
 * @Date 2021/8/11 5:18 下午
 */
public class ConnectionPool {

    List<Connection> cs = new ArrayList<>();

    /**
     * 数据库连接条数
     */
    int size;

    /**
     * 根据size创建连接池
     * @param size 数据库连接条数
     */
    public ConnectionPool(int size){
        this.size = size;
    }

    public void init(){

        try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            for (int i = 0;i<size;i++){
                Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/SQL?characterEncoding=UTF-8",
                        "root", "mo7984130");
                cs.add(c);
            }
        } catch (Exception e) {
                e.printStackTrace();
        }

    }

    public synchronized Connection getConnection(){

        while (cs.isEmpty()){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return cs.remove(0);
    }

    public synchronized void returnConnection(Connection c){
        cs.add(c);
        this.notifyAll();
    }

}
