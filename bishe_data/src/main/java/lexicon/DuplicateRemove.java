package lexicon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.HashSet;
import java.util.Set;

import util.Util;

public class DuplicateRemove {

	public static void main(String[] args) {
		duplicateRemove("verbLexicon_1.txt", "verbLexicon_2_source.txt", "verbLexicon_2");
	}
	
	static void duplicateRemove(String filename1, String filename2, String outputPath){
		try {
			String base = Util.getClassPath() + "lexicon/data/";
			BufferedReader br1 = Util.getBufferedReaderByPath(base + filename1);
			BufferedReader br2 = Util.getBufferedReaderByPath(base + filename2);
			BufferedWriter bw = Util.getBufferedWriterByPath(base + outputPath);
			Set<String> has = new HashSet<>();
			String line = null;
			while((line = br1.readLine()) != null){
				has.add(line.trim());
			}
			br1.close();
			while((line = br2.readLine()) != null){
				if(!has.contains(line.trim())){
					has.add(line.trim());
					bw.write(line + "\n");
				}
			}
			br2.close();
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
