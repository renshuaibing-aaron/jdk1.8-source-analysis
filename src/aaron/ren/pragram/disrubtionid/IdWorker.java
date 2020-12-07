package aaron.ren.pragram.disrubtionid;

public class IdWorker{

    //��������ÿ��5λ������������10λ�Ĺ�������id
    private long workerId;    //����id
    private long datacenterId;   //����id
    //12λ�����к�
    private long sequence;

    public IdWorker(long workerId, long datacenterId, long sequence){
        // sanity check for workerId
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0",maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0",maxDatacenterId));
        }
        System.out.printf("worker starting. timestamp left shift %d, datacenter id bits %d, worker id bits %d, sequence bits %d, workerid %d",
                timestampLeftShift, datacenterIdBits, workerIdBits, sequenceBits, workerId);

        this.workerId = workerId;
        this.datacenterId = datacenterId;
        this.sequence = sequence;
    }

    //��ʼʱ���
    private long twepoch = 1288834974657L;

    //����Ϊ5λ
    private long workerIdBits = 5L;
    private long datacenterIdBits = 5L;
    //���ֵ
    private long maxWorkerId = -1L ^ (-1L << workerIdBits);
    private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    //���к�id����
    private long sequenceBits = 12L;
    //���к����ֵ
    private long sequenceMask = -1L ^ (-1L << sequenceBits);

    //����id��Ҫ���Ƶ�λ����12λ
    private long workerIdShift = sequenceBits;
    //����id��Ҫ����λ�� 12+5=17λ
    private long datacenterIdShift = sequenceBits + workerIdBits;
    //ʱ�����Ҫ����λ�� 12+5+5=22λ
    private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

    //�ϴ�ʱ�������ʼֵΪ����
    private long lastTimestamp = -1L;

    public long getWorkerId(){
        return workerId;
    }

    public long getDatacenterId(){
        return datacenterId;
    }

    public long getTimestamp(){
        return System.currentTimeMillis();
    }

    //��һ��ID�����㷨
    public synchronized long nextId() {
        long timestamp = timeGen();

        //��ȡ��ǰʱ������С���ϴ�ʱ��������ʾʱ�����ȡ�����쳣
        if (timestamp < lastTimestamp) {
            System.err.printf("clock is moving backwards.  Rejecting requests until %d.", lastTimestamp);
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds",
                    lastTimestamp - timestamp));
        }

        //��ȡ��ǰʱ�����������ϴ�ʱ�����ͬһ�����ڣ����������кż�һ���������кŸ�ֵΪ0����0��ʼ��
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }

        //���ϴ�ʱ���ֵˢ��
        lastTimestamp = timestamp;

        /**
         * ���ؽ����
         * (timestamp - twepoch) << timestampLeftShift) ��ʾ��ʱ�����ȥ��ʼʱ�������������Ӧλ��
         * (datacenterId << datacenterIdShift) ��ʾ������id������Ӧλ��
         * (workerId << workerIdShift) ��ʾ������id������Ӧλ��
         * | �ǰ�λ������������磺x | y��ֻ�е�x��y��Ϊ0��ʱ������Ϊ0��������������Ϊ1��
         * ��Ϊ������ֻ����Ӧλ�ϵ�ֵ�����壬����λ�϶���0�����Խ������ֵ�ֵ���� | ������ܵõ�����ƴ�Ӻõ�id
         */
        return ((timestamp - twepoch) << timestampLeftShift) |
                (datacenterId << datacenterIdShift) |
                (workerId << workerIdShift) |
                sequence;
    }

    //��ȡʱ����������ϴ�ʱ����Ƚ�
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    //��ȡϵͳʱ���
    private long timeGen(){
        return System.currentTimeMillis();
    }

    //---------------����---------------
    public static void main(String[] args) {
        IdWorker worker = new IdWorker(1,1,1);
        for (int i = 0; i < 30; i++) {
            System.out.println(worker.nextId());
        }
    }

}