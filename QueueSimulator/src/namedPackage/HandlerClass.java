package namedPackage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HandlerClass implements ActionListener {

	public void terminate(){
		Simulator.running = false;
	}
	
	public void actionPerformed(ActionEvent event) {
		int nrOfServers;
		int finish;
		int max;
		int min;

		if (event.getSource() == SimulatorFrame.start) {
			try {
				nrOfServers = Integer.parseInt(SimulatorFrame.noServers.getText());
				Simulator.setServerNr(nrOfServers);
				System.out.println("Number of servers: "+nrOfServers);
				finish = Integer.parseInt(SimulatorFrame.finish.getText());
				Simulator.setFinishTime(finish);
				System.out.println("Total time: "+finish);
				max = Integer.parseInt(SimulatorFrame.max.getText());
				Simulator.setMaxProcessTime(max);
				System.out.println("Max waiting time: "+max);
				min = Integer.parseInt(SimulatorFrame.min.getText());
				Simulator.setMinProcessTime(min);
				System.out.println("Min waiting time: "+min);
				Simulator.sim = new Simulator();
				
				Simulator.th = new Thread(Simulator.sim);
				Simulator.th.start();
				SimulatorFrame.errorMessage.setText("Nr. of servers: " + nrOfServers + ", finish time " + finish
						+ ", min waitin' time: " + min + ", max waitin' time" + max);
			} catch (NumberFormatException e) {
				SimulatorFrame.errorMessage.setText("Fill all the fields!!!");
				e.printStackTrace();
			}
		}
		if (event.getSource() == SimulatorFrame.stop) {
			if (Simulator.th != null) {
				SimulatorFrame.errorMessage1.setText("    Stopped !! (Restart to work further safely)");
				terminate();
			}
		}
	}

}
