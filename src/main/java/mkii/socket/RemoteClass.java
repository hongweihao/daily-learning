package mkii.socket;

public class RemoteClass {
    public RemoteClass() {
    }
    public String method1(){
        System.out.println("method has been called!");
        return "method1";
    }
    public String method2(String s){
        System.out.println("method2 has been called, parameter is " + s);
        return "method2";
    }
}
