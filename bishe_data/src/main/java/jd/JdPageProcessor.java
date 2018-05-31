package jd;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import util.Util;
/**
 * ¥¶¿Ìhttps://cidian.911cha.com/cixing_dongci.html
 * @author xiang
 *
 */
public class JdPageProcessor  implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);
    static String[] URLs = new String[20];
    
    {
    	URLs[0] = "https://cidian.911cha.com/cixing_dongci.html";
    	for(int i = 2; i <= 20; i++ ){
    		URLs[i-1] = "https://cidian.911cha.com/cixing_dongci_p"+i+".html";
    	}
    }
    @Override
    public void process(Page page) {
//        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
       
        String s = page.getHtml().xpath("//div[@class='mcon f14']").toString();
        String p = "<li><a.*?>(.*?)</a>.*</li>";
		Pattern pattern = Pattern.compile(p);
		Matcher matcher = pattern.matcher(s);
		String base;
		BufferedWriter verbBw;
		try {
			base = Util.getCLassPath();
			FileWriter fileWriter = new FileWriter(base + "lexicon/data/verbLexicon_1.txt", true);
			while(matcher.find())
				fileWriter.write(matcher.group(1) + "\n");
			fileWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
		
//		}
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new JdPageProcessor())
        	.addPipeline(new ConsolePipeline())
//        	.addPipeline(new Pipeline() {
//				
//				@Override
//				public void process(ResultItems resultItems, Task task) {
////					for(Map.Entry<String, Object> entry : resultItems.getAll().entrySet()){
//					String s = resultItems.get("content_xfs").toString();
//					String p = "<li><a.*?>(.*?)</a>.*</li>";
//					Pattern pattern = Pattern.compile(p);
//					Matcher matcher = pattern.matcher(s);
//					while(matcher.find())
//						System.out.println("res = " + matcher.group(1) );
////					}
//					
//				}
//			})
        	.addUrl(URLs).run();
    }

}
