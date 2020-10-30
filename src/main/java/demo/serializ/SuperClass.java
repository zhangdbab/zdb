package demo.serializ;

import java.io.Serializable;
//SuperClass  父子类关系 父类不序列化 字段不传递
public class SuperClass implements Serializable {
    String exe;

    public String getExe() {
        return exe;
    }

    public void setExe(String exe) {
        this.exe = exe;
    }
}
