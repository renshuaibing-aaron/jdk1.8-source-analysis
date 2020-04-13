package aaron.ren.concurrentprogramming.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

// ����һ�����ڻ�������Ĺ���

/**
 * ����Ƕ�д���ľ��䰸��  ������������Ĺ���
 */
class DowngradeReadWriteLock {
    Object data;
    volatile boolean cacheValid;
    // ��д��ʵ��
    final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    void processCachedData() {
        // ��ȡ����
        rwl.readLock().lock();
        if (!cacheValid) { // �����������ˣ�����Ϊ null
            // �ͷŵ�������Ȼ���ȡд�� (����ῴ����û�ͷŵ������ͻ�ȡд�����ᷢ���������)
            rwl.readLock().unlock();
            rwl.writeLock().lock();

            try {
                if (!cacheValid) { // �����жϣ���Ϊ�ڵȴ�д���Ĺ����У�����ǰ��������д�߳�ִ�й���
                    data = new Object();
                    cacheValid = true;
                }
                // ��ȡ���� (����д��������£��������ȡ�����ģ���Ϊ ��������������֮���С�)
                rwl.readLock().lock();
            } finally {
                // �ͷ�д������ʱ��ʣһ������
                rwl.writeLock().unlock(); // Unlock write, still hold read
            }
        }

        try {
            use(data);
        } finally {
            // �ͷŶ���
            rwl.readLock().unlock();
        }
    }

    private void use(Object data) {
    }
}