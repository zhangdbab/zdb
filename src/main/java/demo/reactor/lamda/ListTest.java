package demo.reactor.lamda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * forEach stream ::
 */
public class ListTest {
    public static void main(String[] args) {

        List<String> list = Arrays.asList("a", "b", "c");

        list.forEach((input) -> System.out.println(input));

        List<String> list1 = new ArrayList<>();
        list.forEach(input -> list1.add(input));
        list1.forEach(input -> System.out.println(input));
        //流的方式编写
        List<String> list2 = new ArrayList<>();

        System.out.println("stream-----------");
//        list.parallelStream()  并行流  多线程
//        list.stream()    串行流   单线程
        list.stream().map(input->input.toUpperCase()).forEach(input->System.out.println(input));
        System.out.println(":: 是一个Function ");
         //:: 是一个Function
//        方法引用 情况之一： 如果用类类型引用实例方法那么第一个输入参数是调用方法的对象
        list.stream().map(String::toUpperCase).forEach(input->System.out.println(input));

    }
}
