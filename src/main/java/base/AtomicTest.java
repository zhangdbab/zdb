package base;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {
    private  int  a  =0 ;
    AtomicInteger atomicInteger =new AtomicInteger(a);
    public  void  add(){
//        a++;
//        atomicInteger.incrementAndGet();
        atomicInteger.compareAndSet(1,2);
    }


    public static void main(String[] args) throws InterruptedException {

        AtomicTest atomicTest = new AtomicTest();
        atomicTest.add();

//        for (int a =0; a<20;a++){
//
//            for (int b =0; b<10;b++){
//                new Thread(()->atomicTest.add()).start();
//            }
//
//        }
//
//        Thread.sleep(3000);
        System.out.println(atomicTest.atomicInteger.get());
    }
}
