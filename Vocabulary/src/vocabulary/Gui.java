package vocabulary;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Gui extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel = new JPanel();
	private JLabel title = new JLabel("Synonyms dictionary");
	private JLabel wordLabel = new JLabel("Enter a word");
	private JTextField wordTextField = new JTextField();
	private JLabel intrLabel = new JLabel("Synonyms: "); 
	private JTextField intrText = new JTextField();
	private JButton search = new JButton("search");
	private JButton add = new JButton("add");
	private JButton delete = new JButton("delete");
	private JButton regEx = new JButton("RegEx");
	private JTextArea resultArea = new JTextArea(6, 30);
	private JScrollPane scrollingArea = new JScrollPane(resultArea);
	private JButton consWord = new JButton("consistent word");
	private JButton consDic = new JButton("consistent dictionary");
 	
	Dictionary d= new Dictionary();
	
	public Gui() {
		
		mainPanel.setLayout(null);
		title.setBounds(160,50,300,40);
		title.setFont(new Font("Verdana", Font.BOLD, 18));
		title.setForeground(new Color(80, 200, 220));
		mainPanel.add(title);
		
		wordLabel.setBounds(50,150,200,30);
		mainPanel.add(wordLabel);
		
		wordTextField.setBounds(250, 150, 200, 30);
		mainPanel.add(wordTextField);
		
		intrLabel.setBounds(50, 200, 200, 30);
		mainPanel.add(intrLabel);
		
		intrText.setBounds(250,200,200,30);
		mainPanel.add(intrText);
		
		search.addActionListener(this);
		search.setBounds(50,300,100,30);
		mainPanel.add(search);
		
		add.addActionListener(this);
		add.setBounds(170,300,100,30);
		mainPanel.add(add);
		
		delete.addActionListener(this);
		delete.setBounds(290,300,100,30);
		mainPanel.add(delete);
		
		regEx.addActionListener(this);
		regEx.setBounds(410,300,100,30);
		mainPanel.add(regEx);
		
		
		consWord.addActionListener(this);
		consWord.setBounds(200,250,150,30);
		mainPanel.add(consWord);
		consDic.addActionListener(this);
		consDic.setBounds(360,250,150,30);
		mainPanel.add(consDic);
		
		scrollingArea.setBounds(30, 350, 500, 150);
		this.add(scrollingArea);
		
		this.add(mainPanel);
		this.setVisible(true);
		this.setSize(600, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == search)
		{
			String word = wordTextField.getText();
			if (d.isInDictionary(word) == false) 
				JOptionPane.showMessageDialog(mainPanel,"No results were found", "", JOptionPane.ERROR_MESSAGE);
			else {
				intrText.setText(d.synonyms(word));
			}
		}
		
		if (e.getSource() == add)
		{
			String word = wordTextField.getText();
			String sin = intrText.getText();
			d.addWord(word, sin);
			JOptionPane.showMessageDialog(mainPanel,"Word added!","",JOptionPane.INFORMATION_MESSAGE);
			d.saveDictionary();
		}
		
		if (e.getSource() == delete)
		{
			if (d.isInDictionary(wordTextField.getText()))
			{
				d.removeWord(wordTextField.getText());
				JOptionPane.showMessageDialog(mainPanel,"Word deleted!","",JOptionPane.INFORMATION_MESSAGE);
				d.saveDictionary();
			}
			else
			JOptionPane.showMessageDialog(mainPanel,"No results were found", "", JOptionPane.ERROR_MESSAGE);
		}
		
		if (e.getSource() == regEx)
		{
			resultArea.setText("");
			String word =wordTextField.getText();
			String regex=word.toLowerCase();
			if(regex.contains("?")){
				regex=regex.replace("?", ".");
			}
			if(regex.contains("*")){
				regex=regex.replace("*", ".*");
			}
			Pattern p1 = Pattern.compile(regex);///("a?t*"); 	
			Set<Map.Entry<String, String>> set = d.getMap().entrySet();
			for (Map.Entry<String, String> me : set) {
		    	Matcher m = p1.matcher(me.getKey());
		    	if(m.matches()){
		    		resultArea.append(me.getKey()+"="+ me.getValue()+"\n");
		    	}
			}
		}
		
		if (e.getSource() == consWord)
		{
			if (d.consistentWord(wordTextField.getText()))
			{
				JOptionPane.showMessageDialog(mainPanel,"Consistent word!","",JOptionPane.INFORMATION_MESSAGE);
			}
			else
			JOptionPane.showMessageDialog(mainPanel,"Not consistent word!", "", JOptionPane.INFORMATION_MESSAGE);
		}
		
		if (e.getSource() == consDic)
		{
			if (d.consistentDictionary())
			{
				JOptionPane.showMessageDialog(mainPanel,"consistent dictionary!","",JOptionPane.INFORMATION_MESSAGE);
			}
			else
			JOptionPane.showMessageDialog(mainPanel,"Not consistent dictionary!", "", JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
