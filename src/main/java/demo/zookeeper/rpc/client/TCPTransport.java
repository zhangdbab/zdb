package demo.zookeeper.rpc.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by DJ009828 on 2020/10/30 20:34
 */
public class TCPTransport {

private  String serviceAddress;

    public TCPTransport(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }

    private Socket newSocket() throws IOException {
        System.out.println("创建一个新的连接");
        Socket socket = null ;
        try {
            String[] args=serviceAddress.split(":");
            socket =new Socket(args[0],Integer.parseInt(args[1]));
            return  socket;

        }catch (Exception e){
            System.out.println("创建连接失败");
            e.printStackTrace();
        }finally {
//            if(socket!=null){
//                socket.close();
//            }
        }
        return  null;
    }

   public Object send(RpcRequest rpcRequest ) throws IOException {
        Socket socket = null ;

        try{
            socket =newSocket();
            ObjectOutputStream objectOutputStream =new ObjectOutputStream(socket.getOutputStream());
            //发送请求对象
            objectOutputStream.writeObject(rpcRequest);
            objectOutputStream.flush();
            //获取输入流
            ObjectInputStream objectInputStream =new ObjectInputStream(socket.getInputStream());
            Object result =(Object)objectInputStream.readObject();
            objectOutputStream.close();
            objectInputStream.close();
            return  result;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (socket!=null){
                socket.close();
            }
        }
        return  null ;


   }

}
