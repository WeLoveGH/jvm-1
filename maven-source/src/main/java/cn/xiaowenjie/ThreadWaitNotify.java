package cn.xiaowenjie;

public class ThreadWaitNotify {

    public final static Object monitor = new Object();

    public static void main(String[] args) {
        System.out.println("vs code");

        Thread t1 = new Thread(() -> {
            synchronized (monitor) {
                System.out.println(System.currentTimeMillis() + " t1 start");
                try {
                    System.out.println(System.currentTimeMillis() + " get monitor, wait");
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(System.currentTimeMillis() + " t1 end.");
        });

        Thread t2 = new Thread(() -> {
            System.out.println(System.currentTimeMillis() + " t2 start");

            synchronized (monitor) {
                System.out.println(System.currentTimeMillis() + " get monitor, notify");
                monitor.notify();
                System.out.println(System.currentTimeMillis() + " get monitor, notify end");

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // FIXME : synchronized exit, t1 can run;

            System.out.println(System.currentTimeMillis() + " t2 end.");
        });

        t1.start();
        t2.start();
    }

}

/*

线程好玩的地方在于
1：你不知道那个线程先执行，那个线程后执行
2：你认为是原子性的操作，对于操作系统而言，可能分成好几步，并非原子性的
3：先启动的线程不一定先执行完，后启动的线程不一定后执行完
4：所以，线程的执行非常的飘忽不定，不可控不可预测，好像一个小黑盒，有线程调度器来决定什么时候执行，什么时候停止
5：世界上所有的事情，难几乎都是难在变化上，如果变化是随机的，那就更难了，线程就具有这样的特性，所以，他显得比较难一点
6：不过JVM给我们定义了一些类、方法、关键字来控制线程的执行，是我们能够控制好这个小精灵
7：锁、JUC就是最为核心的东西

 */
