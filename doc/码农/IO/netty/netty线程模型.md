1.netty �е�boss�߳���������1����������������
 ����netty��nio����˳���ֻ����һ���˿ڵ������������bossGroups���ö���߳������ǲ����˷���Դ�� 
 ��Ϊ����˵�NiOServerSocketChannelֻע�ᵽ��һ��eventLoop�У�Ҳ��ֻ��һ���߳��ڴ�����������
 
 
2.netty�е�work�߳���ô���ã�
https://www.jianshu.com/p/a6d2baf09897
https://www.cnblogs.com/crazymakercircle/archive/2018/11/05/9911981.html
  work�߳������������Ҿ�����Ҫ���ݾ����Ӧ������ һ����˵����CPU�����й�
  CPU�ܼ��ͣ��̳߳�����ΪN+1 (CPU�ܼ��� ��ֹ���߳�֮����л� ����Ҫ̫����߳�)
  IO�ܼ��ͣ��̳߳�Ϊ2N+1 
boss�̳߳غ�worker�̳߳��ܲ��ܺ���һ��

https://www.cnblogs.com/luoxn28/p/11875340.html
