package aaron.ren.pattern.observer;

public class BinaryObserver extends Observer {
    // �ڹ��췽���н��ж�������
    public BinaryObserver(Subject subject) {
        this.subject = subject;
        // ͨ���ڹ��췽���н� this ������ȥ�Ĳ���һ��ҪС��
        this.subject.attach(this);
    }
    // �÷����������������ݱ����ʱ����е���
    @Override
    public void update() {
        String result = Integer.toBinaryString(subject.getState());
        System.out.println("���ĵ����ݷ����仯���µ����ݴ���Ϊ������ֵΪ��" + result);
    }
}
