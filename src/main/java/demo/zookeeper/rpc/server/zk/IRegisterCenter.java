package demo.zookeeper.rpc.server.zk;

/**
 * Created by DJ009828 on 2020/10/31 14:56
 */
public  interface  IRegisterCenter {
    /**
     * 注册服务名称和地址
     * @param svcName
     * @param serviceAddress
     */
   void  register(String svcName,String serviceAddress);


}
