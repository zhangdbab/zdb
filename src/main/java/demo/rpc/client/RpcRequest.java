package demo.rpc.client;

import java.io.Serializable;

/**
 * Created by DJ009828 on 2020/10/30 20:22
 * 传输对象
 */
public class RpcRequest implements Serializable  {
    private static final long serialVersionUID = 2135808224540880428L;
    private  String className;
    private String methodName;
    private  Object[] parameters;


    public RpcRequest(String className, String methodName, Object[] parameters) {
        this.className = className;
        this.methodName = methodName;
        this.parameters = parameters;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
