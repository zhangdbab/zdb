package demo.serializ;


import java.io.*;

public class ISerializerImpl implements ISerializer   {
    @Override
    public <T> byte[] Serialize(T t) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream =new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream=new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(t);
            return  byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            objectOutputStream.close();
            byteArrayOutputStream.close();
        }
        return new byte[0];
    }

    @Override
    public <T> T doSerialize(byte[] data, Class<T> t) throws IOException {
        ByteArrayInputStream byteArrayInputStream =new ByteArrayInputStream(data);
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream=new ObjectInputStream(byteArrayInputStream);
          return  (T)objectInputStream.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            byteArrayInputStream.close();
            objectInputStream.close();
        }
        return null;
    }


    public <T> byte[] SerializeToFile(T t) throws IOException {
//        ByteArrayOutputStream byteArrayOutputStream =new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream=new ObjectOutputStream(new FileOutputStream(new File("user")));
            objectOutputStream.writeObject(t);
//            return  byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            objectOutputStream.close();
//            byteArrayOutputStream.close();
        }
        return new byte[0];
    }


    public <T> T doSerializeFromFile(byte[] data, Class<T> t) throws IOException {
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream=new ObjectInputStream(new FileInputStream(new  File("user")));
            return  (T)objectInputStream.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            objectInputStream.close();
        }
        return null;
    }


}
