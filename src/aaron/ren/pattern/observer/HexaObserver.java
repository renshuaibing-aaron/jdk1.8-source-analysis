package aaron.ren.pattern.observer;

public class HexaObserver extends Observer {
    public HexaObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }
    @Override
    public void update() {
        String result = Integer.toHexString(subject.getState()).toUpperCase();
        System.out.println("���ĵ����ݷ����仯���µ����ݴ���Ϊʮ������ֵΪ��" + result);
    }
}