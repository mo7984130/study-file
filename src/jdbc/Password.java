package jdbc;

/**
 * @author mo7984130
 * @Classname Password
 * @Description TODO
 * @Date 2021/8/15 10:41 下午
 */
public class Password {
    public int id;
    public String userName;
    public String password;

    public Password(String userName,String password){
        this.userName = userName;
        this.password = password;
    }

    public Password(int id,String userName,String password){
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public String[] stringList(){
        String[] ss = new String[3];
        ss[0] = String.valueOf(this.id);
        ss[1] = this.userName;
        ss[2] = this.password;
        return ss;
    }
}
