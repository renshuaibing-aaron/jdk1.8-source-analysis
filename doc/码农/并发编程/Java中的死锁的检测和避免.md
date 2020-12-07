java 线程排查问题流程
1. 通过top命令查看当前系统CPU使用情况，定位CPU使用率超过100%的进程ID；
2. 通过ps aux | grep PID命令进一步确定具体的线程信息；
3. 通过ps -mp pid -o THREAD,tid,time命令显示线程信息列表，然后找到耗时的线程ID；
4. 将需要的线程ID转换为16进制格式：printf "%x\n" tid
5. 最后找到线程堆栈信息：jstack pid |grep tid ,其中tid是上面转换后的16进制的线程ID