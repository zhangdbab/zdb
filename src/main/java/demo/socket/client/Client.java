package demo.socket.client;


import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Client extends Thread {

    //定义一个Socket对象
    Socket socket = null;

    public Client(String host, int port) {
        try {
            //需要服务器的IP地址和端口号，才能获得正确的Socket对象
            socket = new Socket(host, port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        //客户端一连接就可以写数据给服务器了
        new sendMessThread().start();
        super.run();
        try {
            // 读Sock里面的数据
            InputStream s = socket.getInputStream();
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = s.read(buf)) != -1) {
                System.out.println(getdate() + "  服务器说：  "+new String(buf, 0, len,"UTF-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //往Socket里面写数据，需要新开一个线程
    class sendMessThread extends Thread{
        @Override
        public void run() {
            super.run();
            //写操作
            Scanner scanner=null;
            OutputStream os= null;
            try {
                scanner=new Scanner(System.in);
                os= socket.getOutputStream();
                String in="";
                do {
                    in=scanner.next();
                    os.write((""+in).getBytes());
                    os.flush();
                } while (!in.equals("bye"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            scanner.close();
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getdate() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String result = format.format(date);
        return result;
    }

    //函数入口
    public static void main(String[] args) {
        //需要服务器的正确的IP地址和端口号
//        Client clientTest=new Client("www.zdb-dev.com", 48090);
//        clientTest.start();
        try {
            //发送到8888端口
//            Socket socket=new Socket("www.zdb-dev.com", 48090);
            Socket socket=new Socket("localhost", 8091);

            OutputStream outputStream=socket.getOutputStream();
            PrintWriter printWriter=new PrintWriter(outputStream);
            printWriter.write(Xml.sendstr);

            printWriter.flush();
            //关闭资源
            //从服务端程序接收数据

            InputStream s = socket.getInputStream();
            byte[] buf = new byte[1024];
            int len = 0;

            while ((len = s.read(buf)) != -1) {
                System.out.println(getdate() + "  服务器说：  "+new String(buf, 0, len,"UTF-8"));
                if (len==991)break;
            }
            s.close();
            printWriter.close();
            outputStream.close();

            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }

    }
}