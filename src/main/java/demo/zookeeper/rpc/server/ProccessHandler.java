package demo.zookeeper.rpc.server;

import demo.zookeeper.rpc.client.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

/**
 * Created by DJ009828 on 2020/10/30 20:18
 */
public class ProccessHandler implements  Runnable{


    private Socket socket ;

    Map<String,Object> handlerMap ;

    public ProccessHandler(Socket socket, Map<String,Object> handlerMap ) {
        this.socket = socket;
        this.handlerMap = handlerMap;
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
        String version =rpcRequest.getVersion();
        String serviceName =rpcRequest.getClassName();
        if (version!=null&&!version.equals("")){
            serviceName=serviceName+"-"+version;
        }
        //从handlerMap 中，根据客户端请求的地址，去拿到相应的服务，通过反射发起调用
        Object service = handlerMap.get(serviceName);
        Method method=service.getClass().getMethod(rpcRequest.getMethodName(),types);

        return  method.invoke(service,agrs);

    }
}
