package demo.zookeeper.rpc.server;

import demo.zookeeper.rpc.ano.RpcAnnotation;
import demo.zookeeper.rpc.server.zk.IRegisterCenter;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by DJ009828 on 2020/10/30 20:12
 */
public class RpcServer {
     private  static final ExecutorService executorService = Executors.newCachedThreadPool();
     private IRegisterCenter registerCenter ;  //注册中心
     private  String  serviceAddress ;//服务发布地址
     //存放服务名和服务对象之间的关系
    Map<String,Object> handlerMap = new HashMap();

    public RpcServer(IRegisterCenter registerCenter, String serviceAddress) {
        this.registerCenter = registerCenter;
        this.serviceAddress = serviceAddress;
    }
    //绑定服务名称和服务对象
    public   void  bind(Object... services){
        for(Object service:services){
            RpcAnnotation rpcAnnotation = service.getClass().getAnnotation(RpcAnnotation.class);

            String svcName= rpcAnnotation.value().getName();
            String version =rpcAnnotation.version();
            if (version!=null&&!version.equals("")){
                svcName=svcName+"-"+version;
            }
            handlerMap.put(svcName,service);
        }

    }


    public   void    publisher() throws IOException {
        ServerSocket serverSocket = null ;
        try {
        String[]  args = serviceAddress.split(":");
        serverSocket = new ServerSocket(Integer.parseInt(args[1]));

        for (String interfaceName:handlerMap.keySet()){
            registerCenter.register(interfaceName,serviceAddress);
            System.out.println("" +
                    "注册服务成功"+interfaceName+"->"+serviceAddress);
        }
        while (true){
            Socket socket = serverSocket.accept();
            executorService.execute(new ProccessHandler(socket,handlerMap));


        }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (serverSocket!=null){
                serverSocket.close();
            }
        }
    }
}
