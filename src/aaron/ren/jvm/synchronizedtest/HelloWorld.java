package aaron.ren.jvm.synchronizedtest;

public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("hello world");
    }
}


/*
* ×Ö½ÚÂëÎÄ¼þ
* Compiled from "HelloWorld.java"
public class aaron.ren.jvm.HelloWorld {
  public aaron.ren.jvm.HelloWorld();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
       3: ldc           #3                  // String hello world
       5: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
       8: return
}

*
*
* */