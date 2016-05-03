package namedPackage;


public class Simulator implements Runnable {

	public static int finishTime = 1;
	public static int serverNr;
	public static int minProcessTime = 1;
	public static int maxProcessTime = 1;
	public static float average = 0;
	private Scheduler s = new Scheduler(serverNr);
	public static Thread th;
	public static Simulator sim;
	public volatile static boolean running = true;
	
	private SimulatorFrame simulatorFrame= new SimulatorFrame(serverNr);
	
	
	
	public void run() {
		int curTime = 0;
		while (curTime < finishTime && running == true) {
			curTime++;
			int processTime = (int) (Math.random() * (maxProcessTime - minProcessTime) + minProcessTime);
			average += processTime;
			Task t = new Task(curTime, processTime);
			s.dispatchTaskOnServer(t);
			try {
				/*for(Server server: s.serverList)
				{
					simulatorFrame.displayData(0, s.getTasks(counter));	
					counter++;
					Thread.sleep(1000);
				}*/
				simulatorFrame.displayData(serverNr,s);	
				Thread.sleep(1000);
						
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		average = average / finishTime;
		SimulatorFrame.errorMessage.setText("Average waiting time = "+average);
		int aux=0;
		for(Server servers : Scheduler.serverList)
		{
			int totalService;
			totalService =+ servers.serviceTime;
			SimulatorFrame.timeMessage.setText("Server "+aux+" has " + totalService + " max service time");
			System.out.println("Server "+aux+" have been serving " + servers.serviceTime + " clients");
			aux++;
		}
	}

	public static void main(String[] args) {
		sim = new Simulator();
		//th = new Thread(sim);
		//th.start();
	}

	public int getFinishTime() {
		return finishTime;
	}

	public static void setFinishTime(int finishTime) {
		Simulator.finishTime = finishTime;
	}

	public int getMinProcessTime() {
		return minProcessTime;
	}

	public static void setMinProcessTime(int minProcessTime) {
		Simulator.minProcessTime = minProcessTime;
	}

	public int getMaxProcessTime() {
		return maxProcessTime;
	}
	
	public static void setMaxProcessTime(int maxProcessTime) {
		Simulator.maxProcessTime = maxProcessTime;
	}

	public int getServerNr() {
		return serverNr;
	}

	public static void setServerNr(int serverNr) {
		Simulator.serverNr = serverNr;
	}
}
