package demo.serializ;

import java.io.IOException;

public interface ISerializer {
    <T> byte[] Serialize(T t) throws IOException;

    <T> T doSerialize(byte[] data, Class<T> t) throws IOException;

    <T>  byte[] SerializeToFile(T t) throws IOException;


    <T> T doSerializeFromFile(byte[] data, Class<T> t) throws IOException;



}
