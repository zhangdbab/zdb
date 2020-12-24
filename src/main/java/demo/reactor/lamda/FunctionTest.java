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
    }
}
