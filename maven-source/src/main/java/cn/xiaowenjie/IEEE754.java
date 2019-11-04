package cn.xiaowenjie;

public class IEEE754 {

    public static void main(String[] args) {

        float f = -5;

        // float的内部表示
        //11000000101000000000000000000000
        // 32 位
        System.out.println(Integer.toBinaryString(Float.floatToRawIntBits(f)));


        double d = -5;

        // double 的内部表示
        //1100000000010100000000000000000000000000000000000000000000000000
        // 64 位
        System.out.println(Long.toBinaryString(Double.doubleToRawLongBits(d)));

        //100000000000000000000000
        // 24 位
        System.out.println(Integer.toBinaryString(Float.floatToRawIntBits(Float.MIN_NORMAL)));

        //10000000000000000000000000000000000000000000000000000
        // 53 位
        System.out.println(Long.toBinaryString(Double.doubleToRawLongBits(Double.MIN_NORMAL)));

        // 浮点数的大小比较为什么不能用等号？
        {
            float f1 = 0.1f;
            float f2 = 0.1f;

            System.out.println(f1 == f2); // true

            // 默认是 double
            System.out.println(15.1 * 100 + 0.9 * 100 == 16.0 * 100); //true

            // 默认是 double
            System.out.println(16.1 * 100 + 0.9 * 100 == 17.0 * 100); //false 感觉都是 1700 但是就是不相等，是不是很神奇

            // float
            System.out.println(16.1f * 100 + 0.9f * 100 == 17.0f * 100); //true

            System.out.println(Float.compare(16.1f * 100 + 0.9f * 100, 17.0f * 100)); //0，这样比较又相等了，是不是又很神奇

            System.out.println((16.1 + 0.9) * 100 == 17.0 * 100); // true


            //通过输入值，可以看出为啥他们不相等
            System.out.println("16.1 * 100 + 0.9 * 100 "+ (16.1 * 100 + 0.9 * 100));//1700.0000000000002
            System.out.println("17.0 * 100 "+ (17.0 * 100));//1700.0

            //加上f之后他们计算的值有事一样的了，保留的位数是一样的
            System.out.println("16.1f * 100 + 0.9f * 100 "+ (16.1f * 100 + 0.9f * 100));//1700.0
            System.out.println("17.0f * 100 "+ (17.0f * 100));//1700.0

        }

        {
            float f1 = 1f / 3;
            float f2 = 1f / 3;
            System.out.println(f1 == f2); // true
        }
    }
}
