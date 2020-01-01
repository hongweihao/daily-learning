package mkii.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {

    public ExecutorService single(){
        return Executors.newSingleThreadExecutor();
    }


    public static void main(String[] argv){

        ThreadPool pool = new ThreadPool();

        ExecutorService threadPool = pool.single();



    }
}
