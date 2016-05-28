package namedPackage;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {

	private BlockingQueue<Task> bq =new LinkedBlockingQueue<Task>();
	private AtomicInteger waitinTime = new AtomicInteger(0);
	ArrayList<Task[]> taskList = new ArrayList<Task[]>();
	public int serviceTime=0;
	
	@Override
	public void run() {
		while (true) {
			try {
				Task t = bq.take();
				Thread.sleep(t.getProcessTime() * 1000);
				waitinTime.addAndGet((-1) * t.getProcessTime());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	void addTask(Task t){
			bq.add(t);
			waitinTime.addAndGet(t.getProcessTime());
	}
	public Task[] getTasks(){
		Task[] tasks = new Task[bq.size()];
		bq.toArray(tasks);
		return tasks;
	}
}
