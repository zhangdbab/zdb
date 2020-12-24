package demo.reactor.lamda;

import java.util.function.Supplier;

/**
 * Created by DJ009828 on 2020/12/22
 */
public class SupplierTest   {
    public static void main(String[] args) {
        Supplier<User>  supplier = ()->new User("zhang san");
        User user = supplier.get();
        System.out.println(user.getName());
    }
}
