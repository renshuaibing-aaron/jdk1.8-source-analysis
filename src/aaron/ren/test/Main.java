package aaron.ren.test;

public class Main {
    public static void main(String[] args) {
        int n=16;
        System.out.println((n - (n >>> 2)));

        int rs=12;
        int i = Integer.numberOfLeadingZeros(12) | (1 << (16 - 1));
        System.out.println("------"+i);
        System.out.println("1111"+((i << 16) + 2));
    }
}
