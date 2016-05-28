package banking;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class GUI extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * private JPanel mainPanel; private JLabel Accounts; private JLabel
	 * Clinets; public static JButton Acc; public static JButton Client;
	 */
	private JLabel title = new JLabel();
	private JPanel mainPanel = new JPanel();
	private JLabel titleAut = new JLabel();
	private JLabel titlePin = new JLabel("PIN Code");
	private JTextField code = new JTextField();
	private JButton autAdmin = new JButton("Autentificate as Administrator");
	private JButton autClient = new JButton("Autentificate as Client");

	private JPanel adminPanel = new JPanel();
	private JLabel title1 = new JLabel();
	private JButton createAccount = new JButton("Create account");
	private JButton deleteAccount = new JButton("Delete account");
	private JButton listAccounts = new JButton("List Accounts");
	private JButton listClients = new JButton("List clients");
	private JButton adminReturn = new JButton("Back");

	private JPanel adminPanel1 = new JPanel();
	private JLabel titleCreate = new JLabel();
	private JLabel dateClient = new JLabel("Client data");
	private JLabel titleName = new JLabel("Name");
	private JTextField nameClient = new JTextField();
	private JLabel titleFamilyName = new JLabel("FamilyName");
	private JTextField familyNameClient = new JTextField();
	private JLabel titleCNP = new JLabel("CNP");
	private JTextField cnpClient = new JTextField();
	private JLabel dataCont = new JLabel("Account data");
	private JLabel titleType = new JLabel("Account type ");
	private JRadioButton saving = new JRadioButton("Saving");
	private JRadioButton spending = new JRadioButton("Spending");
	private JLabel titleIdContC = new JLabel("ID cont ");
	private JTextField idContC = new JTextField();
	private JLabel titleMoney = new JLabel("Money ");
	private JTextField moneyClient = new JTextField();
	private JLabel titleCodePin = new JLabel("Code PIN ");
	private JTextField codePin = new JTextField();
	private JButton create = new JButton("Create");
	private JButton creareReturn = new JButton("Back");

	private JPanel adminPanel2 = new JPanel();
	private JLabel titleDelete = new JLabel();
	private JLabel titleIdAccountS = new JLabel("ID cont ");
	private JTextField idAccountS = new JTextField();
	private JButton delete = new JButton("Delete");
	private JButton deleteReturn = new JButton("Back");

	private JPanel clientPanel = new JPanel();
	private JLabel title2 = new JLabel();
	private JLabel titleMoneyR = new JLabel("Withdraw money");
	private JTextField moneyWith = new JTextField();
	private JButton withdraw = new JButton("Withdraw");
	private JLabel titleMoneyD = new JLabel("Deposit money");
	private JTextField moneyDep = new JTextField();;
	private JButton deposit = new JButton("Deposit");
	private Object[][] data;
	private DefaultTableModel model;
	private JTable productTable;
	private String capTableClient[] = { "ID cont", "Money" };
	private JButton clientReturn = new JButton("Back");

	private Bank banc = new Bank();
	private Person client;
	private String cnp = "";
	private String name = "";
	private String familyName = "";
	private int type;
	private int codeAcc;
	private double money;
	private String pinAccount = "";

	public GUI() {
		// panou pricipal
		mainPanel.setLayout(null);
		title.setText("Account Operations");
		title.setBounds(210, 40, 400, 30);
		title.setFont(new Font("Verdana", Font.BOLD, 20));
		title.setForeground(new Color(80, 135, 220));
		titleAut.setText("Autentificate");
		titleAut.setBounds(50, 100, 200, 30);
		titleAut.setFont(new Font("Verdana", Font.BOLD, 20));
		titlePin.setBounds(200, 140, 100, 30);
		code.setBounds(260, 140, 100, 30);
		autAdmin.setBounds(50, 180, 220, 30);
		autClient.setBounds(290, 180, 200, 30);
		mainPanel.add(autAdmin);
		autAdmin.addActionListener(this);
		mainPanel.add(autClient);
		autClient.addActionListener(this);
		mainPanel.add(titlePin);
		mainPanel.add(code);
		mainPanel.add(title);
		mainPanel.add(titleAut);
		mainPanel.setVisible(true);

		Border raisedbevel = BorderFactory.createRaisedBevelBorder();

		/*
		 * private JButton afisCont = new JButton("Afisare conturi"); private
		 * JButton afisClient = new JButton("Afisare clienti");
		 */

		// panou administrator
		adminPanel.setBounds(40, 30, 600, 600);
		adminPanel.setLayout(null);
		adminPanel.setBorder(raisedbevel);
		title1.setText("Account operations");
		title1.setBounds(210, 40, 400, 30);
		title1.setFont(new Font("Verdana", Font.BOLD, 20));
		title1.setForeground(new Color(80, 135, 220));
		adminPanel.add(title1);
		listAccounts.setBounds(200, 150, 200, 30);
		adminPanel.add(listAccounts);
		listAccounts.addActionListener(this);
		listClients.setBounds(200, 200, 200, 30);
		adminPanel.add(listClients);
		listClients.addActionListener(this);
		createAccount.setBounds(200, 250, 200, 30);
		adminPanel.add(createAccount);
		createAccount.addActionListener(this);
		deleteAccount.setBounds(200, 300, 200, 30);
		adminPanel.add(deleteAccount);
		deleteAccount.addActionListener(this);
		adminReturn.setBounds(200, 500, 200, 30);
		adminPanel.add(adminReturn);
		adminReturn.addActionListener(this);
		adminPanel.setVisible(false);
		this.add(adminPanel);

		// panou client
		clientPanel.setBounds(40, 30, 600, 600);
		clientPanel.setLayout(null);
		clientPanel.setBorder(raisedbevel);
		title2.setText("Withraw/ Deposit");
		title2.setBounds(210, 40, 400, 30);
		title2.setFont(new Font("Verdana", Font.BOLD, 20));
		title2.setForeground(new Color(80, 135, 220));
		clientPanel.add(title2);
		// interogare.setBounds(200,100,200,30); // interogare
		// clientPanel.add(interogare);
		// interogare.addActionListener(this);
		titleMoneyR.setBounds(60, 150, 300, 30); // retragere
		clientPanel.add(titleMoneyR);
		moneyWith.setBounds(100, 200, 100, 30);
		clientPanel.add(moneyWith);
		withdraw.setBounds(220, 200, 180, 30);
		clientPanel.add(withdraw);
		withdraw.addActionListener(this);
		titleMoneyD.setBounds(60, 250, 300, 30); // depunere
		clientPanel.add(titleMoneyD);
		moneyDep.setBounds(100, 300, 100, 30);
		clientPanel.add(moneyDep);
		deposit.setBounds(220, 300, 180, 30);
		clientPanel.add(deposit);
		deposit.addActionListener(this);
		clientReturn.setBounds(200, 500, 200, 30);
		clientPanel.add(clientReturn);
		clientReturn.addActionListener(this);
		clientPanel.setVisible(false);
		this.add(clientPanel);

		// panou creare cont
		adminPanel1.setLayout(null);
		titleCreate.setText("Create account");
		titleCreate.setBounds(250, 20, 400, 30);
		titleCreate.setFont(new Font("Verdana", Font.BOLD, 20));
		titleCreate.setForeground(new Color(80, 135, 220));
		adminPanel1.add(titleCreate);
		adminPanel1.setBounds(20, 20, 640, 620);
		adminPanel1.setLayout(null);
		adminPanel1.setBorder(raisedbevel);
		dateClient.setBounds(40, 70, 300, 30);
		adminPanel1.add(dateClient);
		titleName.setBounds(80, 110, 60, 30);
		adminPanel1.add(titleName);
		nameClient.setBounds(150, 110, 200, 30);
		adminPanel1.add(nameClient);
		titleFamilyName.setBounds(80, 150, 60, 30);
		adminPanel1.add(titleFamilyName);
		familyNameClient.setBounds(150, 150, 200, 30);
		adminPanel1.add(familyNameClient);
		titleCNP.setBounds(80, 190, 60, 30);
		adminPanel1.add(titleCNP);
		cnpClient.setBounds(150, 190, 200, 30);
		adminPanel1.add(cnpClient);
		dataCont.setBounds(40, 250, 300, 30);
		adminPanel1.add(dataCont);
		titleType.setBounds(80, 290, 60, 30);
		adminPanel1.add(titleType);
		saving.setBounds(150, 290, 80, 30);
		adminPanel1.add(saving);
		spending.setBounds(240, 290, 80, 30);
		adminPanel1.add(spending);
		titleIdContC.setBounds(80, 330, 60, 30);
		adminPanel1.add(titleIdContC);
		idContC.setBounds(150, 330, 80, 30);
		adminPanel1.add(idContC);
		titleMoney.setBounds(80, 370, 60, 30);
		adminPanel1.add(titleMoney);
		moneyClient.setBounds(150, 370, 80, 30);
		adminPanel1.add(moneyClient);
		titleCodePin.setBounds(80, 410, 60, 30);
		adminPanel1.add(titleCodePin);
		codePin.setBounds(150, 410, 80, 30);
		adminPanel1.add(codePin);

		create.setBounds(200, 550, 100, 30);
		adminPanel1.add(create);
		create.addActionListener(this);
		creareReturn.setBounds(325, 550, 100, 30);
		adminPanel1.add(creareReturn);
		creareReturn.addActionListener(this);
		adminPanel1.setVisible(false);
		this.add(adminPanel1);

		// panou stergere cont
		adminPanel2.setLayout(null);
		titleDelete.setText("Delete account");
		titleDelete.setBounds(250, 20, 400, 30);
		titleDelete.setFont(new Font("Verdana", Font.BOLD, 20));
		titleDelete.setForeground(new Color(80, 135, 220));
		adminPanel2.add(titleDelete);
		adminPanel2.setBounds(20, 20, 640, 620);
		adminPanel2.setLayout(null);
		adminPanel2.setBorder(raisedbevel);
		titleIdAccountS.setBounds(200, 250, 50, 30);
		adminPanel2.add(titleIdAccountS);
		idAccountS.setBounds(270, 250, 100, 30);
		adminPanel2.add(idAccountS);
		delete.setBounds(200, 350, 100, 30);
		adminPanel2.add(delete);
		delete.addActionListener(this);
		deleteReturn.setBounds(325, 350, 100, 30);
		adminPanel2.add(deleteReturn);
		deleteReturn.addActionListener(this);
		adminPanel2.setVisible(false);
		this.add(adminPanel2);

		this.add(mainPanel);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(700, 700);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == autAdmin) {
			this.pinAccount = code.getText();
			code.setText("");
			if (this.matchUser("admin.txt") == true) {
				mainPanel.setVisible(false);
				adminPanel.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(adminPanel1, "PIN incorrect", "Inane error", JOptionPane.ERROR_MESSAGE);
			}
		}

		if (e.getSource() == adminReturn) {
			mainPanel.setVisible(true);
			adminPanel.setVisible(false);
		}

		if (e.getSource() == autClient) {
			this.pinAccount = code.getText();
			code.setText("");
			if (this.matchUser("acount.txt") == true) {
				mainPanel.setVisible(false);
				clientPanel.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(adminPanel1, "PIN incorrect", "Inane error", JOptionPane.ERROR_MESSAGE);
			}

			// data = banc.listClientAccount(pinAccount);
			model = new DefaultTableModel(data, capTableClient);
			productTable = new JTable(model);
			JTableHeader header = productTable.getTableHeader();
			header.setBackground(Color.green);
			JScrollPane pane = new JScrollPane(productTable);
			productTable.setFillsViewportHeight(true);
			TableColumn column = null;
			for (int i = 0; i < 2; i++) {
				column = productTable.getColumnModel().getColumn(i);
				column.setPreferredWidth(80);
			}
			pane.setBounds(100, 100, 400, 35);
			clientPanel.add(pane);
		}

		if (e.getSource() == clientReturn) {
			mainPanel.setVisible(true);
			clientPanel.setVisible(false);
		}

		if (e.getSource() == listAccounts) {
			String[] capTabelConturi = { "ID cont", "Client Name", "Client Family Name", "cont type", "Sold",
					"Cod PIN" };
			JTable tabel = new JTable(banc.listAccounts(), capTabelConturi);
			JScrollPane panou = new JScrollPane(tabel);
			getContentPane().add(panou);
			// jt.setBackground(Color.orange);
			JFrame accounts = new JFrame("Accounts");
			accounts.setLocation(150, 150);
			accounts.setSize(700, 400);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			accounts.add(panou);
			accounts.setVisible(true);
		}

		if (e.getSource() == listClients) {
			String[] capTabelClienti = { "Name", "Family Name", "CNP" };
			JTable tabel = new JTable(banc.listClients(), capTabelClienti);
			JScrollPane panou = new JScrollPane(tabel);
			getContentPane().add(panou);
			// jt.setBackground(Color.pink);
			JFrame f = new JFrame("Clienti");
			f.setLocation(150, 150);
			f.setSize(500, 300);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.add(panou);
			f.setVisible(true);
		}

		if (e.getSource() == createAccount) {
			adminPanel.setVisible(false);
			adminPanel1.setVisible(true);
		}

		if (e.getSource() == creareReturn) {
			adminPanel.setVisible(true);
			adminPanel1.setVisible(false);
		}

		if (e.getSource() == deleteAccount) {
			adminPanel.setVisible(false);
			adminPanel2.setVisible(true);
		}

		if (e.getSource() == deleteReturn) {
			adminPanel.setVisible(true);
			adminPanel2.setVisible(false);
		}

		if (e.getSource() == create) {
			Person p = new Person(this.name, this.familyName, this.cnp);
			try {
				this.cnp = cnpClient.getText();
				System.out.println("cnp: " + this.cnp);
				this.name = nameClient.getText();
				System.out.println("Name: " + this.name);
				this.familyName = familyNameClient.getText();
				System.out.println("Family: " + this.familyName);
				if (saving.isSelected() == true) {
					this.type = 1;
				} else {
					if (spending.isSelected() == true) {
						this.type = 2;
					} else {
						JOptionPane.showMessageDialog(adminPanel1, "Incorrect data", "Inane error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
				System.out.println("type: " + this.type);
				if (cnp.equals("") || name.equals("") || familyName.equals(""))
					JOptionPane.showMessageDialog(adminPanel1, "Incorrect data", "Inane error",
							JOptionPane.ERROR_MESSAGE);
				else {
					// this.client = new Person(name,familyName,cnp);
					p.setCNP(cnp);
					p.setfamilyName(familyName);
					p.setName(name);
					this.client = p;
				}
				this.codeAcc = Integer.parseInt(idContC.getText());
				this.money = Double.parseDouble(moneyClient.getText());
				this.pinAccount = codePin.getText();

				System.out.println("good till now");
				// banc.addAccountForPerson(p, assocAcc);
				createNewAcount(p);
				adminPanel.setVisible(true);
				adminPanel1.setVisible(false);
				JOptionPane.showMessageDialog(adminPanel1, "Cont created!");
			} catch (Exception e1) {

			}
		}

		if (e.getSource() == delete) {
			try {
				int removeCode = Integer.parseInt(idAccountS.getText());
				HashMap<Person, Set<Account>> mapGui = banc.map;
				Account a = null;
				Iterator i = mapGui.entrySet().iterator();
				while (i.hasNext()) {
					Person p = null;
					Set<Account> accounts = new HashSet<Account>();
					@SuppressWarnings("rawtypes")
					Map.Entry pair = (Map.Entry) i.next();
					p = (Person) pair.getKey();
					if (mapGui.containsKey(p)) {
						Set<Account> accountSet = mapGui.get(p);
						for (Account account : accountSet) {
							accounts.add(account);
						}
						// }
						a = accounts.iterator().next();
						int accId = a.getAccID();
						if (accId == removeCode) {
							banc.removeAccountForPerson(p, a);
							// banc.saveAllAccounts();
						}
					}
				}
				JOptionPane.showMessageDialog(adminPanel2, "Account deleted !");
				adminPanel.setVisible(true);
				adminPanel2.setVisible(false);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(adminPanel2, "Deletion failed!", "Inane error",
						JOptionPane.ERROR_MESSAGE);
			}
			idAccountS.setText("");
		}

		if (e.getSource() == withdraw) {
			if (moneyWith.getText() != "") {
				money = Double.parseDouble(moneyWith.getText());
				if (this.banc.withdrawMoney(money, this.codeAcc, client) == true) {
					JOptionPane.showMessageDialog(adminPanel1, "Tranzaction done!");
				} else {
					JOptionPane.showMessageDialog(adminPanel1, "Tranzaction error!", "Inane error",
							JOptionPane.ERROR_MESSAGE);
				}
				this.banc.saveAllAccounts();
				// data = this.banc.listClientAccount(pinAccount);
				model = new DefaultTableModel(data, capTableClient);
				productTable.setModel(model);
				moneyWith.setText("");
				moneyDep.setText("");
			}
		}

		if (e.getSource() == deposit) {
			if (moneyDep.getText() != "") {
				money = Integer.parseInt(moneyDep.getText());

				// this.banc.saveAllAccounts();
				// data = this.banc.listClientAccount(pinAccount);
				model = new DefaultTableModel(data, capTableClient);
				productTable.setModel(model);
				moneyWith.setText("");
				moneyDep.setText("");
			}
		}
	}

	public void createNewAcount(Person client) {

		// System.out.println(client.getName());
		System.out.println("Creating new acc");
		if (type == 1) // saving
		{
			SavingAccount saveAccount = new SavingAccount(codeAcc, client, money, type, pinAccount);
			// banca.afisare();
			banc.addAccountForPerson(client, saveAccount);
			// banca.afisare();
		}
		if (type == 2) // spending
		{
			SpendingAccount spendAccount = new SpendingAccount(codeAcc, client, money, type, pinAccount);
			banc.addAccountForPerson(client, spendAccount);
		}
		banc.saveAllAccounts();
	}

	public boolean matchUser(String filename) {
		boolean match = false;
		try {
			FileReader fReader = new FileReader(filename);
			BufferedReader reader = new BufferedReader(fReader);
			String line = reader.readLine();
			while (line != null) {
				StringTokenizer st = new StringTokenizer(line, " ");
				Vector v = new Vector();
				while (st.hasMoreTokens()) {
					v.addElement(st.nextToken());
				}
				String pin = (String) v.elementAt(0);
				if (pinAccount.equals(pin))
					match = true;
				line = reader.readLine();
			}
			reader.close();
		} catch (Exception e) {
			System.out.println("Error");
		}
		return match;
	}
}
