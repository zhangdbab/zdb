package demo.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HelloImpl  extends UnicastRemoteObject implements  IHello {


    protected HelloImpl() throws RemoteException {
    }

    @Override
    public String sayHello(String msg) {
        return "hello"+msg;
    }
}
