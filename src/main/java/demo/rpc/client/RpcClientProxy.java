package demo.rpc.client;

import java.lang.reflect.Proxy;

/**
 * Created by DJ009828 on 2020/10/30 20:23
 */
public class RpcClientProxy {

   public  <T> T clientProxy(final Class<T> interfaceclass,
                             final  String host,final  int port)
   {
     return  (T) Proxy.newProxyInstance(interfaceclass.getClassLoader(),
             new Class[]{interfaceclass},new RemoteInvocationHandler(host,port));

   }


}
