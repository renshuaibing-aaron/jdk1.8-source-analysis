package aaron.ren.concurrentprogramming.interview.old;


class PrintOdd implements Runnable {
    public Counter counter;
    public PrintOdd(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        while (counter.value <= 100) {
            synchronized(counter) {
                if (counter.odd) {
                    System.out.println(counter.value);
                    counter.value++;
                    counter.odd = !counter.odd;
                    //����Ҫ��Ҫȥ���Ѵ�ӡż�����߳�
                    counter.notify();
                }
                try {
                    counter.wait();
                } catch (InterruptedException e) {}
            }
        }
    }
}
