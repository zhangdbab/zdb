package demo.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IHello extends Remote {
    String sayHello(String msg) throws RemoteException;;
}
