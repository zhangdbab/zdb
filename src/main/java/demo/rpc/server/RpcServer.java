package demo.rpc.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by DJ009828 on 2020/10/30 20:12
 */
public class RpcServer {
     private  static final ExecutorService executorService = Executors.newCachedThreadPool();

    public   void    publisher(Object service,int port ) throws IOException {
        ServerSocket serverSocket = null ;
        try {
        serverSocket = new ServerSocket(port);
        while (true){
            Socket socket = serverSocket.accept();

            executorService.execute(new ProccessHandler(socket,service));


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
