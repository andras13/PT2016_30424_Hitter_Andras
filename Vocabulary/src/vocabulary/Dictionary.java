package vocabulary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Vector;

public class Dictionary implements DictionaryInterface, Serializable {

	private static final long serialVersionUID = 1L;
	private TreeMap<String, String> map;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Dictionary() {
		map = new TreeMap<String, String>();
		try {
			FileReader fReader = new FileReader("dictionary.txt");
			BufferedReader reader = new BufferedReader(fReader);
			String line = reader.readLine();
			while (line != null) {
				StringTokenizer st = new StringTokenizer(line, "=;");

				Vector v = new Vector();
				while (st.hasMoreTokens()) {
					v.addElement(st.nextToken());
				}
				String word = (String) v.elementAt(0);
				String synonyms = (String) v.elementAt(1);
				addWord(word, synonyms);
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException ex) {
			System.out.println("Error reading file dictionary.txt");
		}
	}

	/**
	 * @return map
	 */
	public TreeMap<String, String> getMap() {
		return map;
	}

	/**
	 * @return int
	 */
	public int getSize() {
		return map.size();
	}

	/**
	 * @param word
	 * @return boolean
	 */
	public boolean isInDictionary(String word) {
		assert word != null;
		assert !map.isEmpty();
		for (String s : map.keySet()) {
			if (s.matches(word))
				return true;
		}
		return false;
	}

	/**
	 * @param word
	 * @param synonyms
	 */
	public void addWord(String word, String synonyms) {
		assert word != null;
		assert synonyms != null;
		int mapSize = map.size();
		map.put(word, synonyms);
		assert mapSize == map.size() + 1;
		assert isWellFormed();
		// assert isConsistent();
	}

	/**
	 * @param cuvant
	 */
	public void removeWord(String word) {
		assert word != null;
		assert !map.isEmpty();
		int mapSize = map.size();
		map.remove(word);
		assert mapSize < map.size();
		assert isWellFormed();
	}

	/**
	 * @param cuvant
	 * @return s
	 */
	public String synonyms(String word) {
		String s = map.get(word);
		return s;
	}

	public void saveDictionary() {
		assert isWellFormed();
		try {
			FileWriter fWriter = new FileWriter("dictionary.txt", false);
			BufferedWriter writer = new BufferedWriter(fWriter);
			String line = "";

			for (Map.Entry<String, String> entry : map.entrySet()) {
				// value from entry
				String word = entry.getKey();
				String synonyms = entry.getValue();
				line = word + "=" + synonyms + ";";
				writer.write(line);
				writer.newLine();
			}
			writer.close();
		} catch (IOException ex) {
			System.out.println("Error writing file acount.txt");
		}
	}

	public String[] regEx(String word) {
		assert word != null;
		assert !map.isEmpty();
		assert isWellFormed();
		assert !map.isEmpty();

		String[] result = new String[20];
		int index;
		for (index = 0; index < 20; index++)
			result[index] = null;
		index = 0;
		String regex = word.toLowerCase();
		if (regex.contains("?")) {
			regex = regex.replace("?", ".");
		}
		if (regex.contains("*")) {
			regex = regex.replace("*", ".*");
		}
		Pattern p1 = Pattern.compile(regex);
		Set<Map.Entry<String, String>> set = map.entrySet();
		for (Map.Entry<String, String> me : set) {
			Matcher m = p1.matcher(me.getKey());
			if (m.matches()) {
				System.out.println(me.getKey() + " " + me.getValue());
				result[index] = me.getKey() + " " + me.getValue();
				index++;
			}
		}
		return result;
	}

	/**
	 * @return boolean
	 */
	public boolean isWellFormed() {
		if (map.containsKey(null) || map.containsValue(null)) {
			return false;
		}
		@SuppressWarnings("rawtypes")
		Set keys = map.keySet();
		@SuppressWarnings("rawtypes")
		Iterator it = keys.iterator();
		while (it.hasNext()) {
			if (map.get(it.next()) == null)
				return false;
		}
		return true;
	}

	/**
	 * @param c
	 * @return boolean
	 */
	public boolean consistentWord(String c) {
		assert c != null;
		assert !map.isEmpty();
		assert isWellFormed();
		boolean cons = true;
		String s = synonyms(c);
		String delims = " ,";
		StringTokenizer st = new StringTokenizer(s, delims);
		String test = st.nextToken();
		while (st.hasMoreElements()) {
			if (map.get(test) == null)
				cons = false;
			test = st.nextToken();
		}
		return cons;
	}

	/**
	 * @return boolean
	 */
	public boolean consistentDictionary() {
		assert !map.isEmpty();
		assert isWellFormed();
		boolean cons = true;
		for (Map.Entry<String, String> entry : map.entrySet()) {
			if (consistentWord(entry.getKey()) == false)
				cons = false;
		}
		return cons;
	}

}
