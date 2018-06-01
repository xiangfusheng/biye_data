package web.domain;

public class Lexicon {
	private int id;
	private String lexicon;
	private int isFunction;
	private String explained;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLexicon() {
		return lexicon;
	}
	public void setLexicon(String lexicon) {
		this.lexicon = lexicon;
	}
	public int getIsFunction() {
		return isFunction;
	}
	public void setIsFunction(int isFunction) {
		this.isFunction = isFunction;
	}
	public String getExplained() {
		return explained;
	}
	public void setExplained(String explained) {
		this.explained = explained;
	}
	
}
