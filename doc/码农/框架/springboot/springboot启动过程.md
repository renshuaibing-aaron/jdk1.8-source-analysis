��@EnableAutoConfigurationҲ�ǽ���@Import�İ����������з����Զ�����������bean������ص�IoC���������˶��ѣ�

@EnableAutoConfiguration�������·���е�jar����Ϊ��Ŀ�����Զ����ã��磺�����spring-boot-starter-web���������Զ����Tomcat��Spring MVC��������Spring Boot���Tomcat��Spring MVC�����Զ�����


1.Springboot�ĺ������Զ����� ���õľ��������ԭ��


���У���ؼ���Ҫ��@Import(EnableAutoConfigurationImportSelector.class)������EnableAutoConfigurationImportSelector��@EnableAutoConfiguration���԰���SpringBootӦ�ý����з���������@Configuration���ö����ص���ǰSpringBoot������ʹ�õ�IoC����������һֻ����צ�㡱һ����
������Spring���ԭ�е�һ�������ࣺSpringFactoriesLoader��֧�֣�@EnableAutoConfiguration�������ܵ��Զ����ù�Ч�ŵ��Դ󹦸��





2.�Զ�����Ļ��Ӣ�ۣ�SpringFactoriesLoader���
  SpringFactoriesLoader����Spring���˽�е�һ����չ����������Ҫ���ܾ��Ǵ�ָ���������ļ�META-INF/spring.factories��������
  
  
  
  ���ԣ�@EnableAutoConfiguration�Զ����õ�ħ����ʿ�ͱ���ˣ���classpath����Ѱ���е�META-INF/spring.factories�����ļ�����������org.springframework.boot.autoconfigure.EnableutoConfiguration��Ӧ��������ͨ�����䣨Java Refletion��ʵ����Ϊ��Ӧ�ı�ע��@Configuration��JavaConfig��ʽ��IoC���������࣬Ȼ�����Ϊһ�������ص�IoC����