package aaron.ren.pragram.Leetcode.arraylist;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * 手中一幅扑克牌，假设顺序为ABCDEF，把第一张放到桌面上，第二张挪到最后，第三张放到桌面，第四张挪到最后，一直到所有牌都在桌面
 * BCDEF　　 A
 * CDEFB
 * DEFB　　　AC
 * EFBD
 * 把最后在桌面上的这副牌给你，求出原始牌的顺序
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
