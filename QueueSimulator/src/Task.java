
public class Task {

	public int arriveTime;
	public int processTime;
	/*private int queueIndex;
	private int clientNr;
	private int startOfServingTime;
	private int waitingTime;*/
	
	public Task(Task t) {
		super();
		arriveTime = t.arriveTime;
		processTime = t.processTime;
	}
	
	public Task(int arriveTime,int processTime) {
		super();
		this.arriveTime = arriveTime;
		this.processTime = processTime;
		/*this.queueIndex = queueIndex;
		this.clientNr = clientNr;
		startOfServingTime = -1;*/
	}
	
	public int getArriveTime() {
		return arriveTime;
	}

	public void setArriveTime(int arriveTime) {
		this.arriveTime = arriveTime;
	}

	public int getProcessTime() {
		return processTime;
	}

	public void setProcessTime(int processTime) {
		this.processTime = processTime;
	}
	@Override
	public String toString(){
		return "[Arrival time : " + arriveTime + ", process time: " + processTime
				+  "]";
	}
}
