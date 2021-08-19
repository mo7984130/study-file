package _Thread;

import Classes.Hero;

public class DeadLock {

    public static void main(String[] args) {

        Hero a = new Hero();
        Hero b = new Hero();
        Hero c = new Hero();

        Thread t1 = new Thread (()->{
            synchronized (a){
                System.out.println("t1已占有a");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("t1试图占有b");
                System.out.println("等待中");

                synchronized (b){
                    System.out.println("t1已占有b");
                }
            }
        });

        Thread t2 = new Thread (()->{
            synchronized (b){
                System.out.println("t2已占有b");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("t2试图占有c");
                System.out.println("等待中");

                synchronized (c){
                    System.out.println("t2已占有c");
                }
            }
        });

        Thread t3 = new Thread (()->{
            synchronized (c){
                System.out.println("t3已占有c");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("t3试图占有a");
                System.out.println("等待中");

                synchronized (b){
                    System.out.println("t3已占有a");
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }

}
