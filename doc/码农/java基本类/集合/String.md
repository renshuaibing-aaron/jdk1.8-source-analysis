1.һ�������������
String a = "123"; String b = "123";
        System.out.println(a==b);
        System.out.println(a.equals(b));
        
 ��������ʲô�� ��ײ㷴Ӧ��String�Ĵ洢��ʽ��
 
 2.new String("nddd");  ʲô������ʱ�ĳ�����
 
 ���������ִ��ʱһ��������������
 
 3.String�ı��������Ż� ��ô�鿴�ֽ��� �ļ�
 
 https://blog.csdn.net/tyyking/article/details/82496901
 1��ͬһ���ַ����������ڳ�����ֻ��һ�ݸ�����
 2��ͨ��˫�����������ַ�����ֱ�ӱ����ڳ������У�
 3�������String���󣬿���ͨ��String.intern���������ַ����������浽�������У�
 
 
 https://www.jianshu.com/p/c14364f72b7e
 https://www.javadoop.com/post/string
 
 3.Java8-��ν�Listת��Ϊ���ŷָ����ַ���
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
 