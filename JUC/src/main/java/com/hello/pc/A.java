package com.hello.pc;

/**
 * 线程之间的通信，生产者和消费者问题
 * 线程交替执行 A和B操作同一个变量
 * A num+1
 * B num+1
 */
public class A {
    public static void main(String[] args) {
        Data data=new Data();
        new Thread(()->{
            for(int i=0;i<10;i++){
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(()->{
            for(int i=0;i<10;i++){
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        new Thread(()->{
            for(int i=0;i<10;i++){
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
        new Thread(()->{
            for(int i=0;i<10;i++){
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }
}
//判断等待 业务 通知
class Data{
    private int number = 0;

    public synchronized void increment() throws InterruptedException {
        while(number!=0){
            //等待
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName()+"=>"+number);
        //通知其他线程
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        while(number==0){
            //等待
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName()+"=>"+number);
        //通知其它线程
        this.notifyAll();
    }
}
