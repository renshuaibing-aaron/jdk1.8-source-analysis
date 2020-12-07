package aaron.ren.pragram.Leetcode.arraylist;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * ����һ���˿��ƣ�����˳��ΪABCDEF���ѵ�һ�ŷŵ������ϣ��ڶ���Ų����󣬵����ŷŵ����棬������Ų�����һֱ�������ƶ�������
 * BCDEF���� A
 * CDEFB
 * DEFB������AC
 * EFBD
 * ������������ϵ��⸱�Ƹ��㣬���ԭʼ�Ƶ�˳��
 */
public class PuKeOrder {

    public List<String> getOriginOrder(List<String> list) {
        int len = list.size();
        List<String> res = Lists.newArrayListWithCapacity(len);
        for (int i = len - 1; i >= 0; i--) {
            end2start(res);
            res.add(0,list.get(i));
        }
        return res;
    }

    private void end2start(List<String> res) {
        if(res == null || res.size() <= 0){
            return;
        }
        String temp = res.get(res.size()-1);
        res.remove(temp);
        res.add(0,temp);
    }

    public static void main(String[] args) {
        ArrayList<String> strings = Lists.newArrayList("A", "C", "E", "B", "F", "D");
        PuKeOrder puKeOrder = new PuKeOrder();
        List<String> originOrder = puKeOrder.getOriginOrder(strings);
        System.out.println(originOrder);

    }
}
