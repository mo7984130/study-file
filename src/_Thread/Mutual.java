package _Thread;

import arrayList.MyStack;
import methods.Char;

public class Mutual {

    public static class Producer extends Thread{
        public String name;
        public volatile MyStack ms;

        public Producer(String name,MyStack ms){
            this.name = name;
            this.ms = ms;
        }

        public void run(){
            while (true){
                char c = Char.getUppercaseChar();
                ms.push(c);
                System.out.format("%s 压入:%s %n",name,c);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class Consumer extends Thread{
        public String name;
        public volatile MyStack<Character> ms;

        public Consumer(String name,MyStack ms){
            this.name = name;
            this.ms = ms;
        }

        public void run(){
            while (true){
                char c = ms.pull();
                System.out.format("%s 弹出:%s %n",name,c);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {

        /*

        Hero h1 = new Hero("gareen",990);

        Thread recover1 = new Thread(()->{
           while (true){
               h1.recover();

               try {
                   Thread.sleep(10);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        });
        Thread recover2 = new Thread(()->{
            while (true){
                h1.recover();

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread hurt1 = new Thread(()->{
            while (true){
                h1.hurt();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread hurt2 = new Thread(()->{
            while (true){
                h1.hurt();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread hurt3 = new Thread(()->{
            while (true){
                h1.hurt();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread hurt4 = new Thread(()->{
            while (true){
                h1.hurt();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread hurt5 = new Thread(()->{
            while (true){
                h1.hurt();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        recover1.start();
        recover2.start();
        hurt1.start();
        hurt2.start();
        hurt3.start();
        hurt4.start();
        hurt5.start();

         */

        MyStack<Character> ms = new MyStack();

        new Producer("Producer1",ms).start();
        new Producer("Producer2",ms).start();
        new Consumer("Consumer1",ms).start();
        new Consumer("Consumer2",ms).start();
        new Consumer("Consumer3",ms).start();
    }

}
