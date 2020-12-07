package aaron.ren.pragram.Leetcode.queue;

/**
 * ѭ������
 * <p>
 * ע�⣺�пպ����������������
 * ���1.����һ����ʶλ��������ǿջ�����
 * ���2.����һ��Ԫ�ؿռ䣬Լ����"����ͷָ���ڶ�βָ�����һλλ����" ��Ϊ�������ı�־
 *
 * @param <T>
 */

public class CycQueue<T> {

    private Integer MAXSIZE = 6; //ѭ��������󳤶�Ϊ7  0~6
    private Object[] arr;
    private Integer front;//ͷָ�룬�����в�Ϊ�գ�ָ���ͷԪ��
    private Integer rear; //βָ�룬�����в�Ϊ�գ�ָ�����βԪ�ص���һ��λ��

    public CycQueue InitQueue() {
        arr = new Object[MAXSIZE];
        front = rear = 0;
        return this;
    }

    public CycQueue DestroyQueue() {
        arr = null;
        rear = front = 0;
        return this;
    }

    public CycQueue ClearQueue() {
        rear = front = 0;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = null;
        }
        return this;
    }

    public Boolean isEmpty() {
        if (front == rear) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public Integer QueueLength() {
        return (rear - front + MAXSIZE) % MAXSIZE; //���ζ��е�Ԫ�ظ���
    }

    public Object GetHead() {
        return arr[front];
    }

    //���ǰ����
    public Boolean EnQueue(Object e) {
        //����ͷָ���ڶ�βָ�����һλλ����  ˵������
        if ((rear + 1) % MAXSIZE == front) {
            return Boolean.FALSE;
        }
        arr[rear] = e;
        rear = (rear + 1) % MAXSIZE;
        return Boolean.TRUE;
    }

    //����ǰ�п�
    public Object DeQueue() {
        if (rear == front) {
            return null;
        }
        T e = (T) arr[front];
        front = (front + 1) % MAXSIZE;
        return e;
    }


    public static void main(String[] args) {
        CycQueue<Integer> cycQueue = new CycQueue<Integer>();
        cycQueue.InitQueue();
        cycQueue.EnQueue(1);
        cycQueue.EnQueue(2);
        cycQueue.EnQueue(3);
        cycQueue.EnQueue(4);
        cycQueue.EnQueue(5);
        cycQueue.EnQueue(6);

        Integer s = cycQueue.QueueLength();
        System.out.println(cycQueue.GetHead());
        for (Integer integer = 0; integer < s; integer++) {
            System.out.println(cycQueue.DeQueue());
        }
        System.out.println(cycQueue.isEmpty());

        cycQueue.EnQueue(4);
        cycQueue.EnQueue(5);
        cycQueue.EnQueue(6);
        s = cycQueue.QueueLength();
        for (Integer integer = 0; integer < s; integer++) {
            System.out.println(cycQueue.DeQueue());
        }
        System.out.println(cycQueue.isEmpty());

    }
}

