package mkii.thread;

public class Ru implements Runnable {
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

        new Thread(new Ru()).start();
        new Thread(new Ru()).start();
    }
}
