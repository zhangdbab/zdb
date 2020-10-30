package demo.rmi.server;


import demo.rmi.clent.SocketFactory;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.RMISocketFactory;


public class ServerMain {

    public static void main(String[] args) throws Exception {
        //注册服务
        LocateRegistry.createRegistry(8866);
        //指定通信端口，防止被防火墙拦截
        RMISocketFactory.setSocketFactory(new SocketFactory());
        //创建服务
        IHello myService = new HelloImpl();
        Naming.bind("rmi://localhost:8866/myService",myService);
        System.out.println("RMI 服务端启动正常");
    }

}