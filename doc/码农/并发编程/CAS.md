1.利用CAS操作 可以使用乐观锁机制  但是有个缺点 
就是会导致大量线程的等待和自旋


这个在JDK8中已经得到优化 采用分段锁机制保证