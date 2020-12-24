package demo.reactor.lamda;

/**
 *  必须根据上下文推断类型
 */
public class Test3 {
    public static void main(String[] args) {

        IHelloService iHelloService = ()->System.out.println("hello");
        IHelloService iHelloService2 = ()->System.out.println("hello2");
        iHelloService.say();
        iHelloService2.say();

    }
}
@FunctionalInterface
interface   IHelloService {

    void say();

}



@FunctionalInterface
interface   IHelloService2 {

    void say();

}
