package aaron.ren.fromat;

import java.text.DecimalFormat;

public class Test {
    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("000");
        String format = df.format("23");
        System.out.println(format);
    }
}
