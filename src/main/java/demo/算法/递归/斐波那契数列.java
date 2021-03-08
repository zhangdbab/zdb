package demo.算法.递归;

/**
 * 1、1、2、3、5、8、13
 *
 * */

public class 斐波那契数列 {
    public static int fib(int mon) {
        if (mon < 2) {
            return 1;
        } else {
            return fib(mon - 1) + fib(mon - 2);
        }
    }

    public static void main(String[] args) {
        System.out.println(斐波那契数列.fib(2));
    }
}
