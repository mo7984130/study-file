package exception;

public class Account {
    static float balance;

    public float getBalance(){
        return balance;
    }
    //查询余额
    public void deposit(float f){
        balance=+f;
    }
    //存钱
    public static void withdraw(float f) throws OverdraftException{
        float deficit = OverdraftException.deficit;
        if (balance>=f){balance=-f;}
        else if (balance+deficit>=f){
            System.out.printf("您的余额不足%n正在透支");
            balance=-f;
        }
        else throw new OverdraftException("你取太多了");
    }
    //取钱

    static class OverdraftException extends Exception{
        static float deficit = 1000;
        public OverdraftException(String msg){
            super(msg);
        }
    }

    public static void main(String[] args){
        balance = 100;
        try {
            withdraw(10000);
        } catch (OverdraftException e) {
            e.printStackTrace();
        }
    }
}
