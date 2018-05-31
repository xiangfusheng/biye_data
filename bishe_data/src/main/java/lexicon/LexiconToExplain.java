package lexicon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import util.Util;
import baiduhanyu.GetBaiduExplain;

import com.huaban.analysis.jieba.JiebaSegmenter;
/**
 * 同义词
 */
public class LexiconToExplain {

	public static void main(String[] args) throws Exception {
		String base = Util.getCLassPath();
		//原词位置
		BufferedReader verbBr = Util.getBufferedReaderByPath(base + "lexicon/data/verbLexicon.txt");
		//第一步处理位置
		BufferedWriter verbExplainBw = Util.getBufferedWriterByPath(base + "lexicon/data/verbToExplain.txt");
		String line = null;
		int total = 0, noExplain = 0;
		while((line = verbBr.readLine()) != null){
			String[] words = line.split(" ");
			total += (words.length - 1);
			for(int i = 1; i < words.length; i++){
				String baiduExplain = null;
				try {
					baiduExplain = GetBaiduExplain.getBaiduExplain(words[i]);
					
				} catch (Exception e) {
					baiduExplain = "NONE";
					noExplain ++;
				}
				verbExplainBw.write(words[i] + "\t" + baiduExplain + "\n");
			}
		}
		verbExplainBw.close();
		verbBr.close();
		System.out.println("Total = " + total + " noExplain = " + noExplain);
	}
	
	
	static List<String> delStopWd(Set<String> stopwordSetZn, List<String> sentenceByJieba){
		List<String> res = new ArrayList<>();
		for(String s : sentenceByJieba){
			if(!stopwordSetZn.contains(s))
				res.add(s);
		}
		
		return res;
	}

}
