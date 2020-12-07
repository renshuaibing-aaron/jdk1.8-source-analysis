package aaron.ren.serialization.fastjson;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * 针对fastjson使用中出现的循环引用和重复引用问题解决方法
 */
public class FastJsonDemo{
    List<Person> list = new ArrayList<Person>();
    Person person = new Person("FLY", 25);

    /**
     *  测试main
     */
    public static void main(String[] args) {
        new FastJsonDemo().test1();

    }

    /**
     * 未关闭引用检测,转化字符串时会出现$ref
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
