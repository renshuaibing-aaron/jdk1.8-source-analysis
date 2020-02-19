package aaron.ren.jvm;

public class SynchronizedMethod {
    public synchronized void method() {
        System.out.println("Hello World!");
    }
}

/*Compiled from "SynchronizedMethod.java"
public class aaron.ren.jvm.SynchronizedMethod {
  public aaron.ren.jvm.SynchronizedMethod();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public synchronized void method();
    Code:
       0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
       3: ldc           #3                  // String Hello World!
       5: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
       8: return
}

* */