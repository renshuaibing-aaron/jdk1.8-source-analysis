java nio���֪ʶ�ŵ�netty����


2.һ��ע�ᵽSelector�ϣ�Channel��һֱ����ע��ֱ���䱻���ע�ᡣ�ڽ��ע���ʱ�����
Selector�����Channel��������Դ�� Ҳ����SelectionKey�������SelectionKey#channel������
�����key�������channel �رգ��ֻ��key��������Selector�ر�֮ǰ��������Ч��������ǰ�������
������Ҳ֪����ȡ��һ��SelectionKey���������̴�Selector�Ƴ�����������ӵ�Selector��cancelledKeys���
Set�����У��Ա�����һ��ѡ������ڼ�ɾ����
���ǿ���ͨ��java.nio.channels.SelectionKey#isValid�ж�һ��SelectionKey�Ƿ���Ч
