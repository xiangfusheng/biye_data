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
 * 处理https://cidian.911cha.com/cixing_dongci.html
 * @author xiang
 *
 */
public class LexiconToExplain2 {

	public static void main(String[] args) throws Exception {
		String base = Util.getClassPath();
		//原词位置
		BufferedReader verbBr = Util.getBufferedReaderByPath(base + "lexicon/data/verbLexicon_total.txt");
		//第一步处理位置
		BufferedWriter verbExplainBw = Util.getBufferedWriterByPath(base + "lexicon/data/verbToExplain_total.txt");
		BufferedWriter verbWithoutExplainBw = Util.getBufferedWriterByPath(base + "lexicon/data/verbToNoExplain_total.txt");
		String line = null;
		int total = 0, noExplain = 0;
		while((line = verbBr.readLine()) != null){
			line = line.trim();
			total += 1;
			String baiduExplain = null;
			try {
				baiduExplain = GetBaiduExplain.getBaiduExplain(line);
			} catch (Exception e) {
				baiduExplain = null;
				noExplain ++;
			}
			if(StringUtils.isEmpty(baiduExplain)) {
				verbWithoutExplainBw.write(line + "\n");
			}else {
				verbExplainBw.write(line + "\t" + baiduExplain + "\n");
			}
		}
		verbExplainBw.close();
		verbBr.close();
		verbWithoutExplainBw.close();
		System.out.println("Total = " + total + " noExplain = " + noExplain);
	}
	

}
