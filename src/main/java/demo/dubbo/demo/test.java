package demo.dubbo.demo;

import com.alibaba.dubbo.common.extension.Activate;

public class test {
    public static void main(String[] args) {
        //判断  @Activate注解是否在User类中使用
        System.out.println(User.class.isAnnotationPresent(Activate.class));



    }
}
