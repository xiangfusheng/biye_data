package test;



import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import baiduhanyu.HttpRequest;

public class GetBaiduExplain {
	public static String getBaiduExplain(String word) {
		String res = HttpRequest.sendGet("https://hanyu.baidu.com/s?wd=", word);
		Document doc = Jsoup.parse(res);
		String explain = doc.select("div.tab-content p").text();
		return explain;
	}

}
