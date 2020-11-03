package demo.dubbo.demo;

import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Protocol;

public class test {
    public static void main(String[] args) throws NoSuchMethodException {
        //判断  @Activate注解是否在User类中使用
        System.out.println(User.class.isAnnotationPresent(Activate.class));

        //获得参数类型为Protocol的构造方法 一个参数的情况 ，多个参数则列多个
        User.class.getConstructor(Protocol.class);



    }
}
