package demo.netty.decorator;

/**
 * Created by DJ009828 on 2020/11/14
 */
public class Client {
    public static void main(String[] args) {
        Compotent compotent = new DecoratorCompotent2(new DecoratorCompotent());
        compotent.dosomeing();
    }
}
