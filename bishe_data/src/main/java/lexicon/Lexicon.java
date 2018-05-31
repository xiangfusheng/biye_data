package lexicon;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import util.Util;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

/**
 * 抽取动词
 * @author xiang
 *
 */
public class Lexicon {

	public static void main(String[] args) throws Exception {
		String base = Util.getCLassPath();
		//原词位置
		BufferedReader sourceBr = Util.getBufferedReaderByPath(base + "lexicon/data/source.txt");
		//第一步处理位置
		BufferedWriter verbBw = Util.getBufferedWriterByPath(base + "lexicon/data/verbLexicon.txt");
		//保存标注结果
		BufferedWriter verbTaggedBw = Util.getBufferedWriterByPath(base + "lexicon/data/verbTagged.txt");
		String line = null;
		
		//词性标注
		MaxentTagger tagger = new MaxentTagger(Util.getCLassPath()+"static_resource/models/chinese-distsim.tagger");           
	     
		while((line = sourceBr.readLine()) != null){
			String tagged = tagger.tagString(line);
			if(tagged.contains("#VV")){
				verbBw.write(line + "\n");
				verbTaggedBw.write(tagged + "\n");
			}
		}
		verbBw.close();
		sourceBr.close();
		verbTaggedBw.close();

	}

}
