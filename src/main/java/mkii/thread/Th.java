package mkii.thread;

public class Th extends Thread {

    @Override
    public void run(){
        for(int i = 0; i < 5; i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
        }
    }

    public static void main(String[] argv){

        new Th().start();
        new Th().start();

    }



}
