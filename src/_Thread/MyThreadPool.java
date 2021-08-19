package _Thread;

import java.util.LinkedList;

public class MyThreadPool {

    int ThreadPoolSize;

    LinkedList<Runnable> tasks = new LinkedList<>();

    public MyThreadPool(int ThreadPoolSize){
        this.ThreadPoolSize = ThreadPoolSize;

        for (int i =0;i<ThreadPoolSize;i++){
            new TaskThread("线程" + i).start();
        }
    }

    public void add(Runnable r){
        synchronized (tasks){
            tasks.add(r);
            tasks.notifyAll();
        }
    }

    public class TaskThread extends Thread{

        public TaskThread(String name){
            super(name);
        }

        Runnable task;

        public void run(){

            System.out.format("%s 已启动%n",this.getName());
            while (true){
                synchronized (tasks){
                    while (tasks.isEmpty()){
                        try {
                            tasks.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    task = tasks.removeLast();
                    System.out.format("%s 已获取到任务%n",this.getName());
                    tasks.notifyAll();
                }
                System.out.format("%s 执行任务中%n",this.getName());
                task.run();
            }
        }

    }

    public static void main(String[] args) {
        MyThreadPool mtp = new MyThreadPool(5);

        for (int i =0;i<100;i++){
            int i1 = i;
            mtp.add(()->{
                System.out.format("%d 已被执行%n",i1);
            });
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
