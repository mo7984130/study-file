package _Thread;

public class a_du_gen {

    public static void main(String[] args) {
        Thread t = new Thread(){
            public void run(){
                try {
                    while (true){
                        for (int i=1;i<4;i++){
                            System.out.println("波动拳第" + i + "发");
                            Thread.sleep(1_000);
                        }
                        System.out.println("开始五秒充能");
                        Thread.sleep(5_000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        t.start();
    }
}
