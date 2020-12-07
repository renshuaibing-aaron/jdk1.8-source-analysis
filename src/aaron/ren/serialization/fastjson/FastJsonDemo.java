package aaron.ren.serialization.fastjson;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * ���fastjsonʹ���г��ֵ�ѭ�����ú��ظ���������������
 */
public class FastJsonDemo{
    List<Person> list = new ArrayList<Person>();
    Person person = new Person("FLY", 25);

    /**
     *  ����main
     */
    public static void main(String[] args) {
        new FastJsonDemo().test1();

    }

    /**
     * δ�ر����ü��,ת���ַ���ʱ�����$ref
     */
    public void test1() {
        list.add(person);
        list.add(person);
        System.out.println(list);
        String jsonStr = JSON.toJSONString(list);
        System.out.println(jsonStr);
        // [{"age":25,"name":"FLY"},{"$ref":"$[0]"}]

    }

}
