package web.domain;

public class Lexicon {
	private int id;
	private String lexicon;
	//标注是否是功能词汇
	private int isFunction;
	//标注是否被设置过
	private int isSet;
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
	public int getIsSet() {
		return isSet;
	}
	public void setIsSet(int isSet) {
		this.isSet = isSet;
	}
	
	
}
