package aaron.ren.string.format;

import java.text.DecimalFormat;

public class Test {
    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("000");

        StringBuffer strbuf=new StringBuffer();
        for(int i=1;i<100;i++){
            if(i==99){
                String format = df.format(i);
                strbuf.append(format);
            }else{
                String format = df.format(i);
                strbuf.append(format).append(",");
            }

        }

        System.out.println(strbuf);
    }
}
