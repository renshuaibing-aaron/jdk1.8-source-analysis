package aaron.ren.jvm;

public class SynchronizedCodeBlock {

    public void method() {
        synchronized (this) {
            System.out.println("Hello World!");
        }

    }
}

/*
Compiled from "SynchronizedCodeBlock.java"
public class aaron.ren.jvm.SynchronizedCodeBlock {
public aaron.ren.jvm.SynchronizedCodeBlock();
        Code:
        0: aload_0
        1: invokespecial #1                  // Method java/lang/Object."<init>":()V
        4: return

public void method();
        Code:
        0: aload_0
        1: dup
        2: astore_1
        //todo
        3: monitorenter
        4: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
        7: ldc           #3                  // String Hello World!
        9: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
        12: aload_1
        13: monitorexit
        14: goto          22
        17: astore_2
        18: aload_1
        //todo
        19: monitorexit
        20: aload_2
        21: athrow
        22: return
        Exception table:
        from    to  target type
        4    14    17   any
        17    20    17   any
        }


 */
