package demo.reactor.lamda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StringComparator {
    public static void main(String[] args) {

        List<String> stringList = Arrays.asList("aa","ac","ee");
        Collections.sort(stringList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        System.out.println(stringList);
        //方式二
        Collections.sort(stringList,(String o1,String o2)->{return o1.compareTo(o2);});
        System.out.println(stringList);
        //方式三 去掉 return 和 {;}
        Collections.sort(stringList,(String o1,String o2)-> o1.compareTo(o2));
        System.out.println(stringList);
        //方式四
        Collections.sort(stringList, Comparator.naturalOrder());
        System.out.println(stringList);



    }
}
