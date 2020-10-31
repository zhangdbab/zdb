package demo.zookeeper.rpc.ano;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RpcAnnotation {
    /**
     * 对外发布的接口地址
     * @return
     */
    Class<?> value();
    String version()  default "";
}
