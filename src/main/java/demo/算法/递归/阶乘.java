package demo.算法.递归;

public class 阶乘 {
    public static Integer recursionMulity(Integer n) {
        if (n == 1) {
            return 1;
        }
        return n * recursionMulity(n - 1);
    }

    public static void main(String[] args) {
     System.out.println(阶乘.recursionMulity(3));

    }
}
