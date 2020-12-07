1.ÿ��redis���������������� 
struct redisServer {
   // һ�����鱣��������е��������ݿ� 
    redisDb *db;
    int dbnum ; //�����������ݿ������
}redisServer;

2.ÿ���ͻ��˵�Ŀ�����ݿ� Ĭ����0�����ݿ� ����ͨ��select��������л�
 struct redisClient {
    // ��ǰ����ʹ�õ����ݿ�
    redisDb *db;  ���ָ��ָ��redisServer �е�db��һ��Ԫ��
    }redisClient;
    
    
3.  ���ݿ�ռ����redis���ݿ� �ܶ��redis�Ĳ������Ƕ����ݿ�ļ��ռ���еĲ���
```java
/*���ݿ�ռ�*/
/* Redis database representation. There are multiple databases identified
 * by integers from 0 (the default database) up to the max configured
 * database. The database number is the 'id' field in the structure. */
typedef struct redisDb {
    // ���ݿ���ռ䣬���������ݿ��е����м�ֵ��
    dict *dict;                 /* The keyspace for this DB */
    // ���Ĺ���ʱ�䣬�ֵ�ļ�Ϊ�����ֵ��ֵΪ�����¼� UNIX ʱ���  �����ֵ�
    dict *expires;              /* Timeout of keys with a timeout set */
    // ����������״̬�ļ�
    dict *blocking_keys;        /* Keys with clients waiting for data (BLPOP) */
    // ���Խ�������ļ�
    dict *ready_keys;           /* Blocked keys that received a PUSH */
    // ���ڱ� WATCH ������ӵļ�
    dict *watched_keys;         /* WATCHED keys for MULTI/EXEC CAS */
    struct evictionPoolEntry *eviction_pool;    /* Eviction pool of keys */
    // ���ݿ����
    int id;                     /* Database ID */
    // ���ݿ�ļ���ƽ�� TTL ��ͳ����Ϣ
    long long avg_ttl;          /* Average TTL, just for stats */
} redisDb;
```

  ע���ڶ������ݿ�ļ��ռ���в�����ʱ����� ��������ݿ�Ķ�д�� ����������һЩά����������
    ���·�������д��� �����д���
    �������ݿ����LRUʱ��
    ����ڶ�ȡ��ʱ���ּ����� �����ɾ��
    �����watch��������� ����Ϊ������
    �������֪ͨ���ܼ������޸ĺ� �ᴥ��֪ͨ
    
    
3.�������redis���Ĺ���ʱ��
  expire key 1000   ��key������ʱ������Ϊ1000��
  pexpire key 1000  ��key������ʱ������Ϊ1000����
  expireat key 1000 ��key�Ĺ���ʱ������Ϊ1000�����ʱ���
  pexpireat key  10000 ��key�Ĺ���ʱ������Ϊ1000�������ʱ���  �����������ն���ת��Ϊ�������ʵ�ֵ�
  ע�⿴����Ľṹ��redisDb �еļ�����ʱ��

4.�Ƴ�����ʱ��
  persist�����Ƴ�һ�����Ĺ���ʱ��
  
5.���㷵�ؼ���ʣ������ʱ��
  ttl  ����Ϊ��λ���ؼ���ʣ������ʱ��
  pttl  �Ժ���Ϊ��λ���ؼ���ʣ������ʱ��
  
6.���������ڶ�ȡ����ʱ����Ҫ�жϼ��Ƿ���ڣ� ��ô�жϣ�
   �������ļ��Ƿ���ڹ����ֵ� ������ڻ�ȡ���Ĺ���ʱ��
   ��鵱ǰunix��ʱ����Ƿ���ڼ��Ĺ���ʱ�� 
   
7.ʲôʱ��ɾ�����ڵļ������ֲ���
 ��ʱɾ�� ����ɾ�� �ڴ�������ʱ���ͬʱ ������ʱ�� ����ɾ��
         ��ʡ�ڴ� ���Ǻķ�CPU �ر����ڴ�������������� ������
 ����ɾ�� ����ɾ�� ÿ��һ��ʱ�� ɨ���ɾ��
          �ȽϺ� �����ѵ�����ȷ��ִ�е�ʱ���Ƶ��
 ����ɾ��  ����ɾ�� ���μ����� ����ʱ�����ɾ��
  
 ��ʵ����rdis���ö���ɾ���Ͷ���ɾ����ϵķ�ʽ
   int expireIfNeeded(redisDb *db, robj *key) {}    //����ɾ��
   void activeExpireCycle(int type) {} //����ɾ��  ע��������������ִ�� ������һ�λ�ɨ����
  
  
  
8.ע��redis��ɾ�����Զ����Ӹ��� �־û�AOF BDF  ֪ͨ�µ�Ӱ��
  

   
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  