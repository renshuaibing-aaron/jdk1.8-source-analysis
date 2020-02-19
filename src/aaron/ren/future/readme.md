Future的局限性。Future很难直接表述多个Future 结果之间的依赖性，开发中，我们经常需要达成以下目的：

将两个异步计算合并为一个（这两个异步计算之间相互独立，同时第二个又依赖于第一个的结果）
等待 Future 集合中的所有任务都完成。
仅等待 Future 集合中最快结束的任务完成，并返回它的结果。


1.        String result = CompletableFuture.supplyAsync(()->{return "Hello ";}).thenApplyAsync(v -> v + "world").join();
          System.out.println(result);
          
          
2.          