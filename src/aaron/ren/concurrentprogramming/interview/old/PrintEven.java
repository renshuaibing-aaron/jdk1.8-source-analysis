package aaron.ren.concurrentprogramming.interview.old;


class PrintEven implements Runnable {
    public Counter counter;
    public PrintEven(Counter counter) {
        this.counter = counter;
    }


    @Override
    public void run() {
        while (counter.value <= 100) {
            synchronized (counter) {
                if (!counter.odd) {
                    System.out.println(counter.value);
                    counter.value++;
                    counter.odd = !counter.odd;
                    counter.notify();
                }
                try {
                    counter.wait();
                } catch (InterruptedException e) {}
            }
        }
    }
}
