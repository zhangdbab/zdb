package demo.reactor.lamda;

import java.util.function.Consumer;

/**
 * Created by DJ009828 on 2020/12/22
 */
public class ConsumerTest {
    public static void main(String[] args) {

        Consumer<String> consumer = (msg)->System.out.println(msg);

        consumer.accept("hello");

    }
}
