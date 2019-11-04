package cn.xiaowenjie;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 利用semaphore的先V（release）再P（acquire）操作，实现线程的前置依赖关系。
 * semaphore的容量是0
 * <p>
 * 利用信号量，来控制线程的先后执行顺序
 * 第二个线程等待第一个线程执行完，再执行
 * 第三个线程等待第二个线程执行完，再执行
 * 第一个线程执行完释放第二个线程的凭证
 * 第二个线程执行完释放第三个线程的凭证
 */
public class SemaphorePVDemo {


    public static void main(String[] args) {
        /**
         * 创建两个信号量，凭证的个数都0
         */
        Semaphore semaphore1 = new Semaphore(0);

        Semaphore semaphore2 = new Semaphore(0);

        /**
         * 创建一个线程，并启动它
         */
        new Thread(() -> {
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程1最先工作");
            //释放凭证
            semaphore1.release();
        }).start();

        /**
         * 创建一个线程，并启动它
         */
        new Thread(() -> {
            try {
                //尝试获取凭证，如果获取不到，则阻塞等待
                semaphore1.acquire();
                Thread.sleep(ThreadLocalRandom.current().nextInt(5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程2然后工作");
            //释放凭证
            semaphore2.release();
        }).start();

        /**
         * 创建一个线程，并启动它
         */
        new Thread(() -> {
            try {
                //尝试获取凭证，如果获取不到，则阻塞等待
                semaphore2.acquire();
                Thread.sleep(ThreadLocalRandom.current().nextInt(5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程3最后工作");
        }).start();

    }

}
