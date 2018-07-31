package com.hibi.www.threads;

import java.util.UUID;
import java.util.concurrent.*;

/**
 * 线程类
 * 作者：MisPeng
 */

public class Thred {
    private String name= UUID.randomUUID().toString();
    public void testThread(int threadNum) throws InterruptedException{
//        int threadNum=10;
//初始化countDown
        CountDownLatch threadSignal=new CountDownLatch(threadNum);
        ExecutorService executor=Executors.newFixedThreadPool(threadNum);
        synchronized (Thred.class){
            for (int i = 0; i <threadNum; i++) {
                TestThread task=new TestThread(threadSignal);
                executor.execute(task);
            }
        }
        threadSignal.await();
        executor.shutdown();
        System.out.println(Thread.currentThread().getName() + "+++++++用户操作完成退出.");
    }

    public static void main(String[] args) throws InterruptedException{
        Thred test=new Thred();
        test.testThread(8000);
    }

    private  class  TestThread implements Runnable{
        private CountDownLatch threadsSignal;
        public  TestThread(CountDownLatch threadsSignal){
            this.threadsSignal=threadsSignal;
        }
        @Override
        public void run() {
//            System.out.println(Thread.currentThread().getName() + "开始..." + name);
//            System.out.println("用户进入：：：：" + threadsSignal.getCount());
            threadsSignal.countDown();//必须等核心处理逻辑处理完成后才可以减1
//            System.out.println(Thread.currentThread().getName() + "结束. 还有"
//                    + threadsSignal.getCount() + " 个线程");
        }
    }
}


