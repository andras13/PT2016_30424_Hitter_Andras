import java.util.ArrayList;

public class Scheduler {

	static ArrayList<Server> serverList = new ArrayList<Server>();
	//Maybe it should not be static?
	// private Server s;

	public Scheduler(int size) {
		for (int i = 0; i < size; i++) {
			Server s = new Server();
			serverList.add(s);
			Thread th = new Thread(s);
			th.start();
		}
	}

	void dispatchTaskOnServer(Task t) {
		for (Server s : serverList) {
			int size = s.getTasks().length;
			if (size < 10) {
				s.addTask(t);
				s.serviceTime++;
				break;
			}
		}
	}

	public Task[] getTasks(int id) {
		return serverList.get(id).getTasks();
	}

	@Override
	public String toString() {
		return "Thread: \n " + serverList.get(0).getTasks().length;
	}
}
// String in task/ scheduler