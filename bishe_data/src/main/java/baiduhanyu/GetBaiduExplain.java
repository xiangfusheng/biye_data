package baiduhanyu;



import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.huaban.analysis.jieba.JiebaSegmenter;
/**
 * 从百度汉语获取解释
 * @author xiang
 *
 */
public class GetBaiduExplain {
	
	public static void main(String[] args) {
		System.out.println(getBaiduExplain("划分"));
	}
	
	public static String getBaiduExplain(String word) {
		String res = HttpRequest.sendGet("https://hanyu.baidu.com/s?wd=", word);
		Document doc = Jsoup.parse(res);
		JiebaSegmenter segmenter = new JiebaSegmenter();
		String explain = doc.select("div.tab-content dl dd p").first().text();
		explain = explain.replaceAll("\\pP|[0-9a-zA-z]*", "");
		List<String> sentenceByJieba = segmenter.sentenceProcess(explain);
		explain = StringUtils.join(sentenceByJieba, " ");
		return explain;
	}

}
