package cn.xiaowenjie.completablefuturedemos;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Demo3 {

    public static String test() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "我完成了";
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("start...");

        /**
         * jdk8的链式调用方式，如果不给返回值，则返回值为null
         */
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> test())
                .thenApply(s -> s + " 函数接口，加个尾巴")
                .thenAccept(System.out::println);

        System.out.println("end..." + future.get());
    }
}
