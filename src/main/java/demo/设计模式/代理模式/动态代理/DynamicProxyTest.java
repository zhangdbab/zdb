package demo.设计模式.代理模式.动态代理;

import java.lang.reflect.Proxy;

/**
 * 动态代理主要是通过反射机制，在运行时动态生成所需代理的class.
 */
public class DynamicProxyTest {

    public static void main(String[] args) {
        Target target = new TargetImpl();
        DynamicProxyHandler handler = new DynamicProxyHandler(target);
        Target proxySubject = (Target) Proxy.newProxyInstance(TargetImpl.class.getClassLoader(),TargetImpl.class.getInterfaces(),handler);
        String result = proxySubject.execute();
        System.out.println(result);
    }

}