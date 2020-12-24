package demo.reactor.lamda;
//Runnable 只有一个抽象方法
public class ThreadTest {
    public static void main(String[] args) {
        Thread thread = new Thread(()->System.out.println("hello")) ;
        thread.start();
    }

}
