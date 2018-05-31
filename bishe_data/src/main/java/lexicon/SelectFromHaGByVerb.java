package lexicon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.HashSet;
import java.util.Set;

import util.Util;
/**
 * 判断动词在哈工大词林中是否包含
 * @author xiang
 *
 */
public class SelectFromHaGByVerb {

	public static void main(String[] args) {
		get("verbLexicon_total.txt", "HGD_by_verbs_jiaoji.txt");
	}
	static void get(String filename, String outputPath){
		try {
			String base = Util.getClassPath() + "lexicon/data/";
			BufferedReader br1 = Util.getBufferedReaderByPath(base + filename);
			BufferedReader br2 = Util.getBufferedReaderByPath(base + "source.txt");
			BufferedWriter bw = Util.getBufferedWriterByPath(base + outputPath);
			Set<String> has = new HashSet<>();
			String line = null;
			while((line = br1.readLine()) != null){
				has.add(line.trim());
			}
			br1.close();
			int total = 0;
			while((line = br2.readLine()) != null){
				String[] split = line.split(" ");
				StringBuilder sb = new StringBuilder(split[0]);
				boolean vis = false;
				for(int i = 1; i < split.length; i++){
					if(has.contains(split[i])){
						vis = true;
						has.remove(split[i]);
						sb.append(" " + split[i]);
						total += 1;
					}
				}
				if(vis) bw.write(sb.toString() + "\n");
			}
			br2.close();
			bw.close();
			System.out.println("total = " + total + " remain = " + has.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
