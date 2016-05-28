package vocabulary;

public interface DictionaryInterface {
	
	/**
	 * @Pre word!=null
	 * @Pre !isEmpty()
	 */
	public boolean isInDictionary(String word);
	
	/**
	 * @Pre word!=null
	 * @Pre synonym!=null
	 * @Post getSize()==getSize()@Pre+1
	 * @Post isWellFormed();
	 */
	public void addWord(String cuvant, String sinonime);
	
	/**
	 * @Pre word!=null
	 * @Pre !isEmpty()
	 * @Post getSize()==getSize()@Pre+1
	 * @Post isWellFormed();
	 */
	public void removeWord(String cuvant);
	
	/**
	 * @Pre word!=null
	 * @Pre !isEmpty()
	 * @Post isWellFormed();
	 */
	public String[] regEx(String cuvant);
	
	/**
	 * @Pre c!=null
	 * @Pre !isEmpty()
	 * @Post isWellFormed();
	 */
	public boolean consistentWord(String c);
	
	/**
	 * @Pre !isEmpty()
	 * @Post isWellFormed();
	 */
	public boolean consistentDictionary();
}
