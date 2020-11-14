package demo.netty.decorator;

/**
 * Created by DJ009828 on 2020/11/14
 */
public class DecoratorCompotent  implements   Compotent{

     private  Compotent compotent ;
//    public DecoratorCompotent(Compotent compotent) {
//        this.compotent = compotent;
//    }
//
    public void dosomeing() {
        System.out.println("A");

    }
}
