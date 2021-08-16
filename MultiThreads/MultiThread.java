import java.util.concurrent.*;

class SearchThread implements Runnable{
  private Thread t;
  private int threadId;

  SearchThread(int id){
    threadId = id;
    System.out.println("Creating thread" + threadId);
  }

  @Override
  public void run(){
    System.out.println("Running thread" + threadId);
    try {
      for (int i=10; i>0; i--) {
        System.out.println("thread" + threadId + " working on data[" + i + "]");
        Thread.sleep(1000);
      }
      while (true) {

      }
    } catch(Exception e) {
      System.out.println("thread" + threadId + " interrupted.");
    }
    System.out.println("thread" + threadId + " exiting.");
  }

  public void start(){
    System.out.println("Launching thread" + threadId);
    if (t==null) {
      String s = String.valueOf(threadId);
      t = new Thread(this, s);
      t.start();
    }
  }

  public void join(){

    try {
      t.join();
      System.out.println("thread" + threadId + " has finished its task and waiting for other thread(s) to exit. ");
    } catch(Exception e) {
      System.out.println("thread" + threadId + " hasn't started yet.");
    }

  }

}

public class MultiThread{
  public static void main(String args[]){
    System.out.println("main thread has launched ");

    int numberOfCores = Runtime.getRuntime().availableProcessors();
    float workloadOccupancy = 0.6F;
    int numberOfThreads = Math.round((float) numberOfCores * workloadOccupancy);
    System.out.println("You've set working threads " + numberOfThreads + " out of " + numberOfCores + " on your CPU capability.");

    ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
    try{
      for ( int i=0; i < numberOfThreads; i++){
        executor.execute(new SearchThread(i));
      }
    }catch(Exception err){
      err.printStackTrace();
    }
    executor.shutdown();


    // SearchThread t0 = new SearchThread(0);
    // SearchThread t1 = new SearchThread(1);
    // SearchThread t2 = new SearchThread(2);
    // SearchThread t3 = new SearchThread(3);
    // SearchThread t4 = new SearchThread(4);
    // SearchThread t5 = new SearchThread(5);
    // t0.start();
    // t1.start();
    // t2.start();
    // t3.start();
    // t4.start();
    // t5.start();
    //
    // t0.join();
    // t1.join();
    // t2.join();
    // t3.join();
    // t4.join();
    // t5.join();



  }
}
