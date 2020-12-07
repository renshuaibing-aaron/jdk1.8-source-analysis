1.一道经典的面试题
String a = "123"; String b = "123";
        System.out.println(a==b);
        System.out.println(a.equals(b));
        
 输出结果是什么？ 这底层反应了String的存储方式？
 
 2.new String("nddd");  什么是运行时的常量池
 
 这个方法在执行时一共创建几个对象
 
 3.String的编译器的优化 怎么查看字节码 文件
 
 https://blog.csdn.net/tyyking/article/details/82496901
 1、同一个字符串常量，在常量池只有一份副本；
 2、通过双引号声明的字符串，直接保存在常量池中；
 3、如果是String对象，可以通过String.intern方法，把字符串常量保存到常量池中；
 
 
 https://www.jianshu.com/p/c14364f72b7e
 https://www.javadoop.com/post/string
 
 3.Java8-如何将List转变为逗号分隔的字符串
 List<String> cities = Arrays.asList("Milan", 
                                     "London", 
                                     "New York", 
                                     "San Francisco");
 String citiesCommaSeparated = String.join(",", cities);
 System.out.println(citiesCommaSeparated);
 //Output: Milan,London,New York,San Francisco
 
 String citiesCommaSeparated = cities.stream()
                                     .collect(Collectors.joining(","));
 System.out.println(citiesCommaSeparated);
 
 
 String citiesCommaSeparated = cities.stream()
                                     .map(String::toUpperCase)
                                     .collect(Collectors.joining(","));
 //Output: MILAN,LONDON,NEW YORK,SAN FRANCISCO
 