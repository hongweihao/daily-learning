package mkii.rpc.entity;

import java.io.Serializable;

public class Request implements Serializable {
    private static final long serialVersionUID = 3933918042687238629L;
    private String className;
    private String methodName;
    private Class<?> [] paramTypes;
    private Object [] parameters;

    public Request() {
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes(Class<?>[] paramTypes) {
        this.paramTypes = paramTypes;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }
}
