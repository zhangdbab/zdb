package demo.rmi.clent;


import demo.rmi.server.IHello;

import java.rmi.Naming;


public class ClientMain {

    public static void main(String[] args) throws Exception {
        //服务引入
        IHello myService = (IHello) Naming.lookup("rmi://localhost:8866/myService");
        //调用远程方法
        System.out.println("RMI 服务端调用返回：" + myService.sayHello("MySelf"));
    }

}