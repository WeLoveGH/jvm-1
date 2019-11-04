package cn.xiaowenjie;

public class LocalVarAndGC {

    public static void main(String[] args) {
        System.out.println("-XX:+PrintGC/ PrintGCDetails");
        System.out.println("--------------1");
        gc();
        System.out.println("-------------2");
        gc2();
        System.out.println("-------------3");
    }

    public static  void gc(){
        {
            byte[] a = new byte[6*1024*1024];
        }

        // a 虽然失效，但仍然在局部变量表，无法gc
        System.gc();
    }

    /**
     * 看打印的日志信息，确实如此，不过表面上看还是比较好玩的，代码上差别不大，但是实际上JVM的处理方式却非常的大
     */
    public static  void gc2(){
        {
            byte[] a = new byte[6*1024*1024];
        }

        int c = 1;

        //  a 失效，槽位重用，a已经不存在，可以回收
        System.gc();
    }

}


/*

-XX:+PrintGC/ PrintGCDetails
--------------1
[GC (System.gc()) [PSYoungGen: 9062K->6952K(56320K)] 9062K->6952K(184832K), 0.0144286 secs] [Times: user=0.03 sys=0.00, real=0.02 secs]
[Full GC (System.gc()) [PSYoungGen: 6952K->0K(56320K)] [ParOldGen: 0K->6875K(128512K)] 6952K->6875K(184832K), [Metaspace: 3445K->3445K(1056768K)], 0.0100631 secs] [Times: user=0.05 sys=0.00, real=0.01 secs]
-------------2
[GC (System.gc()) [PSYoungGen: 7116K->32K(56320K)] 13992K->6907K(184832K), 0.0003845 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[Full GC (System.gc()) [PSYoungGen: 32K->0K(56320K)] [ParOldGen: 6875K->727K(128512K)] 6907K->727K(184832K), [Metaspace: 3446K->3446K(1056768K)], 0.0178446 secs] [Times: user=0.05 sys=0.00, real=0.02 secs]
-------------3
Heap
 PSYoungGen      total 56320K, used 2918K [0x0000000781900000, 0x0000000785780000, 0x00000007c0000000)
  eden space 48640K, 6% used [0x0000000781900000,0x0000000781bd9b38,0x0000000784880000)
  from space 7680K, 0% used [0x0000000785000000,0x0000000785000000,0x0000000785780000)
  to   space 7680K, 0% used [0x0000000784880000,0x0000000784880000,0x0000000785000000)
 ParOldGen       total 128512K, used 727K [0x0000000704a00000, 0x000000070c780000, 0x0000000781900000)
  object space 128512K, 0% used [0x0000000704a00000,0x0000000704ab5df8,0x000000070c780000)
 Metaspace       used 3452K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 376K, capacity 388K, committed 512K, reserved 1048576K

Process finished with exit code 0

*/
