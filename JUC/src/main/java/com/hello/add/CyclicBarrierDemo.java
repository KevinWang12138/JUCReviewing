package com.hello.add;

import com.hello.pc.C;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier=new CyclicBarrier(7,()->{
            System.out.println("召唤神龙");
        });
        for(int i=0;i<7;i++){
            final int temp=i;
            new Thread(()->{
                System.out.println("收集到第"+(temp+1)+"颗龙珠");
                try {
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread().getName()+"歇逼了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
