package demo.netty.decorator;

import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * Created by DJ009828 on 2020/11/14
 */
public class DecoratorCompotent2 implements Compotent{
    private  Compotent compotent;

    public DecoratorCompotent2(Compotent compotent) {
        this.compotent = compotent;
    }

    public void dosomeing() {
        compotent.dosomeing();
        doanthoerthing();
    }
    public  void doanthoerthing(){
        System.out.println("B");
    }
}
