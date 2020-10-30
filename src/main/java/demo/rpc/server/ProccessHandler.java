package demo.rpc.server;

import demo.rpc.client.RpcRequest;
import sun.dc.pr.PRError;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Proxy;
import java.net.Socket;

/**
 * Created by DJ009828 on 2020/10/30 20:18
 */
public class ProccessHandler implements  Runnable{


    private Socket socket ;
    private  Object service; //通过服务端发布的服务

    public ProccessHandler(Socket socket, Object service) {
        this.socket = socket;
        this.service = service;
    }

    @Override
    public void run() {
        ObjectInputStream objectInputStream =null;
        try {
            objectInputStream =new ObjectInputStream( socket.getInputStream());
            RpcRequest rpcRequest = (RpcRequest)objectInputStream.readObject();
            Object result = invoke(rpcRequest);
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(result);
            objectOutputStream.flush();
            objectOutputStream.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            if (objectInputStream!=null){
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
    private  Object invoke(RpcRequest rpcRequest) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object[] agrs = rpcRequest.getParameters();
        Class<?>[] types =new Class[agrs.length];
        for (int i=0 ;i< agrs.length;i++){
            //获取参数类型
            types[i]=agrs[i].getClass();
        }
        Method method=service.getClass().getMethod(rpcRequest.getMethodName(),types);

        return  method.invoke(service,agrs);

    }
}
