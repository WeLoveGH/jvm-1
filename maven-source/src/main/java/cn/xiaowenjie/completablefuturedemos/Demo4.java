package cn.xiaowenjie.completablefuturedemos;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Demo4 {

    public static int test(int i) {
       return i / 0;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("start...");

        /**
         * jdk8的链式调用，中间出现异常了，后面也能继续执行
         */
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> test(2))
                .exceptionally(e -> {
                    e.printStackTrace();
                    return -1;
                })
                .thenApply(s -> s + " 函数接口，加个尾巴")
                .thenAccept(System.out::println);

        System.out.println("end..." + future.get());
    }
}
