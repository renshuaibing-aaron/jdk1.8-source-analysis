package aaron.ren.pragram.bloomfilter;

import java.nio.charset.Charset;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.PrimitiveSink;

public class BloomTest {
    // Ԥ��Ԫ�ظ���
    private long size = 1024 * 1024;

    private BloomFilter<String> bloom = BloomFilter.create(new Funnel<String>() {
        @Override
        public void funnel(String from, PrimitiveSink into) {
            // �Զ���������� �˴������κι���
            into.putString((CharSequence) from, Charset.forName("UTF-8"));
        }
    }, (int) size);

    public void fun() {
        // �������������Ԫ��
        bloom.put("��¡������");
        // ��ѯ
        boolean mightContain = bloom.mightContain("��¡������");
        System.out.println(mightContain);
    }

    public static void main(String[] args) {
        BloomTest blBoolmTest = new BloomTest();
        blBoolmTest.fun();
    }
}
