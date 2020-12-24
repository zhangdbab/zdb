package demo.reactor.lamda;

import java.util.function.Function;

/**
 * Created by DJ009828 on 2020/12/22
 * 不知道functio的行为 用户使用是时候传递行为
 */
public class FunctionTest1 {
    public static void main(String[] args) {
        FunctionTest1 functionTest1  = new FunctionTest1();
        //(b)->b+2) 是 Function 接口的 一个实现类
     System.out.println(functionTest1.test(1,(b)->b+2));

     System.out.println(functionTest1.test(1,(b)->b*b));


    }
    //不知道functio的行为 用户使用是时候传递行为
    public  int test (int a ,Function<Integer,Integer> function){
         int result=function.apply(a);
        return  result;
    }
    // 不用lamda 提前定义的行为
    public  int add(int a ){
        return  a+2;

    }

}
