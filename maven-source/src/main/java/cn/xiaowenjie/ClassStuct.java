package cn.xiaowenjie;

public class ClassStuct {
    public static int varStatic = 999;

    public static int varStatic2;

    static {
        varStatic2 = 1111;
    }






    public int varLocal = 888;

    public static void main(String[] args) throws Exception {
        System.out.println(varStatic);
        System.out.println(methodStatic(true));

        ClassStuct cs = new ClassStuct();
        System.out.println(cs.varLocal);
        System.out.println(cs.methodLocal(true, (byte)'a'));
    }

    public static int methodStatic(boolean flag){
        return 1;
    }


    /**
     * 最终返回的是finally中的值
     * @param flag
     * @param b
     * @return
     * @throws Exception
     */
    public synchronized int  methodLocal(boolean flag, byte b) throws Exception{
        System.out.println(b);
        try {
            return 1;
        }
        catch (Exception e){
            return 2;
        }
        finally {
            return 3;
        }
    }
}




