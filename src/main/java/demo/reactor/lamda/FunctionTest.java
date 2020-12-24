package demo.reactor.lamda;

import java.util.function.Function;

/**
 * Created by DJ009828 on 2020/12/22
 */
public class FunctionTest {
    public static void main(String[] args) {
        Function<String,User> function  = (msg)-> {return new User(msg);};
        User user = function.apply("li si");
        System.out.println(user.getName());
//        toUpperCase 实例方法   方法引用 情况之一： 如果用类类型引用实例方法那么第一个输入参数是调用方法的对象
        Function<String,String> function1 = String::toUpperCase;
        System.out.println(function1.getClass().getInterfaces()[0]);
    }
}
