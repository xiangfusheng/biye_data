package test;



import util.Util;
import baiduhanyu.GetBaiduExplain;

public class TestPath {

	public static void main(String[] args) throws Exception {
		String path = System.getProperty("user.dir");
		try {
			 GetBaiduExplain.getBaiduExplain("a");
		} catch (Exception e) {
			System.out.println( "ERROR");
		}
		System.out.println(Util.getStopwordSetZn().size());
		
	}

}
