package namedPackage;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.TextField;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;

public class SimulatorFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panel = new JPanel();
	private JPanel inputData = new JPanel();

	private JLabel title = new JLabel();
	public static JLabel errorMessage = new JLabel();
	public static JLabel errorMessage1 = new JLabel();
	public static JLabel timeMessage = new JLabel();
	private JLabel nrOfServers = new JLabel("no.Servers");
	private JLabel finishTime = new JLabel("Finish time");
	private JLabel minProcessTime = new JLabel("min process time");
	private JLabel maxProcessTime = new JLabel("max process time");

	public static JButton start = new JButton("Start");
	public static JButton stop = new JButton("Stop");

	public static TextField noServers = new TextField("", 10);
	public static TextField finish = new TextField("", 10);
	public static TextField min = new TextField("", 10);
	public static TextField max = new TextField("", 10);

	private JScrollPane sp;
	private JScrollPane sp1;
	private JScrollPane sp2;
	private JScrollPane sp3;
	private JScrollPane sp4;
	private JScrollPane sp5;
	private JScrollPane sp6;
	private JScrollPane sp7;
	private JScrollPane sp8;
	private JScrollPane sp9;
	
	ArrayList<JScrollPane> panels = new ArrayList<JScrollPane>();

	public SimulatorFrame(int size) {

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBackground(Color.LIGHT_GRAY);
		this.setSize(1000, 400);

		/*
		 * for(int i=0;i<size;i++) { JScrollPane sp = new JScrollPane();
		 * panels.add(sp); }
		 */

		this.add(inputData);
		inputData.setLayout(new MigLayout());
		/*
		 * for(int i=0;i<Simulator.serverNr;i++) { JPanel auxPanel =new
		 * JPanel(); panels.add(auxPanel);
		 * this.add(panels.get(i),BorderLayout.PAGE_END); }
		 */
		this.add(panel, BorderLayout.PAGE_END);
		this.setVisible(true);

		title.setText("Queue simulator");
		title.setBounds(270, 40, 400, 30);
		title.setFont(new Font("Verdana", Font.BOLD, 20));
		title.setForeground(Color.blue);
		errorMessage.setText("Errors:");
		errorMessage.setFont(new Font("Verdana", Font.ITALIC, 12));
		errorMessage.setForeground(Color.red);
		errorMessage1.setText("");
		errorMessage1.setFont(new Font("Verdana", Font.ITALIC, 12));
		errorMessage1.setForeground(Color.red);
		timeMessage.setText("Time message");
		timeMessage.setFont(new Font("Verdana", Font.ITALIC, 12));
		timeMessage.setForeground(Color.green);

		start.setBounds(600, 150, 70, 30);

		inputData.add(title, "wrap");
		inputData.add(nrOfServers);
		inputData.add(noServers, "wrap");
		inputData.add(finishTime);
		inputData.add(finish, "wrap");
		inputData.add(minProcessTime);
		inputData.add(min, "wrap");
		inputData.add(maxProcessTime);
		inputData.add(max, "wrap");
		inputData.add(start);
		inputData.add(stop, "wrap");
		inputData.add(errorMessage);
		inputData.add(errorMessage1,"wrap");
		inputData.add(timeMessage,"wrap");

		HandlerClass handler = new HandlerClass();
		start.addActionListener(handler);
		stop.addActionListener(handler);
	}

	void displayData(int panelId, Scheduler s/* , Task[] tasks */) {
		panel.removeAll();
		panel.revalidate();
		int serverNrCheck = panelId;
		
		if (serverNrCheck != 0) {
			JList<Task> JTasks0 = new JList<Task>(s.getTasks(0));
			sp = new JScrollPane(JTasks0);
			panel.add(sp);
			serverNrCheck--;
			if(serverNrCheck != 0){
				JList<Task> JTasks1 = new JList<Task>(s.getTasks(1));
				sp1 = new JScrollPane(JTasks1);
				panel.add(sp1);
				serverNrCheck--;
				if(serverNrCheck != 0){
					JList<Task> JTasks2 = new JList<Task>(s.getTasks(2));
					sp2 = new JScrollPane(JTasks2);
					panel.add(sp2);
					serverNrCheck--;
					if(serverNrCheck != 0){
						JList<Task> JTasks3 = new JList<Task>(s.getTasks(3));
						sp3 = new JScrollPane(JTasks3);
						panel.add(sp3);
						serverNrCheck--;
						if(serverNrCheck != 0){
							JList<Task> JTasks4 = new JList<Task>(s.getTasks(4));
							sp4 = new JScrollPane(JTasks4);
							panel.add(sp4);
							serverNrCheck--;
							if(serverNrCheck != 0){
								JList<Task> JTasks5 = new JList<Task>(s.getTasks(5));
								sp5 = new JScrollPane(JTasks5);
								panel.add(sp5);
								serverNrCheck--;
								if(serverNrCheck != 0){
									JList<Task> JTasks6 = new JList<Task>(s.getTasks(6));
									sp6 = new JScrollPane(JTasks6);
									panel.add(sp6);
									serverNrCheck--;
									if(serverNrCheck != 0){
										JList<Task> JTasks7 = new JList<Task>(s.getTasks(7));
										sp7 = new JScrollPane(JTasks7);
										panel.add(sp7);
										serverNrCheck--;
										if(serverNrCheck != 0){
											JList<Task> JTasks8 = new JList<Task>(s.getTasks(8));
											sp8 = new JScrollPane(JTasks8);
											panel.add(sp8);
											serverNrCheck--;
											if(serverNrCheck != 0){
												JList<Task> JTasks9 = new JList<Task>(s.getTasks(9));
												sp9 = new JScrollPane(JTasks9);
												panel.add(sp9);
												serverNrCheck--;
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		//panel.add(sp);
		//panel.add(sp1);
		//panel.add(sp2);
		
		panel.repaint();
		panel.revalidate();
		

	}
}
