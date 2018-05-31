package baiduhanyu;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;

public class BaiduHanyuProcessor  implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public void process(Page page) {
//        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
       
        page.putField("content_xfs", page.getHtml().xpath("//div[@class='tab-content']"));
        page.putField("content_xfs1", page.getHtml());
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) throws Exception {
//        Spider.create(new BaiduHanyuProcessor())
//        	.addPipeline(new ConsolePipeline())
//        	.addPipeline(new Pipeline() {
//				
//				@Override
//				public void process(ResultItems resultItems, Task task) {
//					for(Map.Entry<String, Object> entry : resultItems.getAll().entrySet()){
//						System.out.println("key = " + entry.getKey() + " value = " + entry.getValue());
//					}
//					
//				}
//			})
//        	.test("https://hanyu.baidu.com/s?wd=ÉÏ´«");

    }

}
