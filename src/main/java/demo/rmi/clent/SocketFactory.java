package demo.rmi.clent;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.RMISocketFactory;

public class SocketFactory extends RMISocketFactory {

    public Socket createSocket(String host, int port) throws IOException {
        return new Socket(host, port);
    }

    public ServerSocket createServerSocket(int port) throws IOException {
        if (port == 0){
            port = 8855;
        }
        System.out.println("RMI 通信端口 : " + port);
        return new ServerSocket(port);
    }
}