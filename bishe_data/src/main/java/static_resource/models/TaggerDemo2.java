package static_resource.models;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import edu.stanford.nlp.util.logging.Redwood;

/**
 * 成功
 * @author xiang
 *
 */
public class TaggerDemo2  {

  /** A logger for this class */
  private static Redwood.RedwoodChannels log = Redwood.channels(TaggerDemo2.class);

  private TaggerDemo2() {}

  public static void main(String[] args) throws Exception {
	  // Initialize the tagger  载入已经训练好的模型        
      MaxentTagger tagger = new MaxentTagger("G:/工程/workspace/毕设/jd-spider/src/main/java/static_resource/models/chinese-distsim.tagger");           
      // The sample string          
      String sample = "电冰箱 能够 冷冻";  
//      String sample = "电冰箱 洗衣机 制冷 冷冻 冷藏 传递 制造 好的 查询 设置";
      // The tagged string          
      String tagged = tagger.tagString(sample);   

      // Output the result          
      System.out.println(tagged);
  }

}
