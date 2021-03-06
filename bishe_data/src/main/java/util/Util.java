package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Util {
	public static BufferedReader getBufferedReaderByPath(String path) throws FileNotFoundException {
		FileReader sourceReader = new FileReader(path);
		return new BufferedReader(sourceReader);
	}
	public static BufferedWriter getBufferedWriterByPath(String path) throws IOException{
		FileWriter verbLexiconW = new FileWriter(path);
		return new BufferedWriter(verbLexiconW);
	}
	public static String getClassPath() throws UnsupportedEncodingException{
		return System.getProperty("user.dir") + "/src/main/java/";
	}
	public static Set<String> getStopwordSetZn() throws Exception{
		BufferedReader br = getBufferedReaderByPath(getClassPath() + "lexicon/data/chineseStopwords.txt");
		Set<String> set = new HashSet<>();
		String line = null;
		while((line = br.readLine()) != null){
			set.add(line.trim());
		}
		
		return set;
	}
	public static List<String> delStopWd(Set<String> stopwordSetZn, List<String> sentenceByJieba){
		List<String> res = new ArrayList<>();
		for(String s : sentenceByJieba){
			if(!stopwordSetZn.contains(s))
				res.add(s);
		}
		
		return res;
	}
	
}
