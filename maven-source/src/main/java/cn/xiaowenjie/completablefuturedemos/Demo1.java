package cn.xiaowenjie.completablefuturedemos;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Demo1 {

    public static void main(String[] args) throws InterruptedException {


        /**
         * 带返回值的线程调用
         */
        CompletableFuture<String> future = new CompletableFuture<>();

        System.out.println("start...");

        /**
         * 创建线程，且启动线程
         */
        new Thread(() -> {
            try {
                System.out.println(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
		}).start();

        // 模拟耗时
        Thread.sleep(1000);

        // 告知完成
        future.complete("我做完了");
    }
}
