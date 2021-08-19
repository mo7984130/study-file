package jdbc;

import Classes.Hero;
import interfaces.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mo7984130
 * @Classname HeroDAO
 * @Description TODO
 * @Date 2021/8/10 9:53 下午
 */
@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
public class HeroDAO implements DAO<Hero> {

    public HeroDAO(){
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
    public void add(Hero h) {
        //language=MySQL
        String sql = "insert into Hero values (null,?,?,?)";
        try (
                Connection c = getSqlConnection();
                PreparedStatement ps = c.prepareStatement(sql)
                ){

            ps.setString(1,h.name);
            ps.setInt(2,h.hp);
            ps.setInt(3,h.damage);
            ps.execute();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Hero h) {
        //language=MySQL
        String sql = "delete from Hero where id = ?";
        try(
                Connection c = getSqlConnection();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {

            ps.setInt(1,h.id);
            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Hero h) {
        //language=MySQL
        String sql = "update Hero set name = ? , hp = ? ,damage = ? where id = ?";
        try(
                Connection c = getSqlConnection();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {

            ps.setString(1,h.name);
            ps.setInt(2,h.hp);
            ps.setInt(3,h.damage);
            ps.setInt(4,h.id);
            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Hero get(int id){
        //language=MySQL
        String sql = "select * from Hero where id = ?";
        try(
                Connection c = getSqlConnection();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {

            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                return new Hero(rs.getInt("id"),rs.getString("name"),rs.getInt("hp"),rs.getInt("damage"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List list() {
        List l = new ArrayList();
        //language=MySQL
        String sql = "select * from Hero";
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

    public List<Hero> list(int start, int count){
        List l = new ArrayList();
        //language=MySQL
        String sql = "select * from Hero limit ?,?";
        try(
                Connection c = getSqlConnection();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {

            ps.setInt(1,start);
            ps.setInt(2,count);
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

    public static void main(String[] args) {
        HeroDAO hd = new HeroDAO();


    }
}
