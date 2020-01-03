package mkii.thread.create;

public class MyRunnable implements Runnable {
    private String threadName;
    public MyRunnable(String threadName){
        this.threadName = threadName;
    }
    @Override
    public void run(){
        for(int i = 0; i < 5; i++){
            /*try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            System.out.println(threadName + " printed: " + i);
        }
    }

    public static void main(String[] argv){

        new Thread(new MyRunnable("MyRunnable1")).start();
        new Thread(new MyRunnable("MyRunnable2")).start();
    }
}
