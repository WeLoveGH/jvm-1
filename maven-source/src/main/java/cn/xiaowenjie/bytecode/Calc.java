package cn.xiaowenjie.bytecode;

public class Calc {

    public static int calc(){
        int a = 100;
        int b = 200;
        int c = 300;
        return (a+b)/c;
    }

    public static void main(String[] args) {
        System.out.println("calc : "+calc());
    }
}
