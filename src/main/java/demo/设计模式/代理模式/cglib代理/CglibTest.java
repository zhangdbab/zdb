package demo.设计模式.代理模式.cglib代理;


import org.springframework.cglib.proxy.Enhancer;

/**
 *  无论是动态代理还是静态带领，都需要定义接口，然后才能实现代理功能。这同样存在局限性，
 *  因此，为了解决这个问题，出现了第三种代理方式：cglib代理。
 * CGLib采用了非常底层的字节码技术，其原理是通过字节码技术为一个类创建子类
 * ，并在子类中采用方法拦截的技术拦截所有父类方法的调用，
 * 顺势织入横切逻辑。JDK动态代理与CGLib动态代理均是实现Spring AOP的基础。
 *
 */
public class CglibTest {

    public static void main(String[] args) {
        System.out.println("***************");
        Target target = new Target();
        CglibTest test = new CglibTest();
        Target proxyTarget = (Target) test.createProxy(Target.class);
        String res = proxyTarget.execute();
        System.out.println(res);
    }

    public Object createProxy(Class targetClass) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetClass);
        enhancer.setCallback(new MyMethodInterceptor());
        return enhancer.create();
    }

}
