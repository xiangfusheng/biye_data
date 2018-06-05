package web.util;

import java.util.HashMap;
import java.util.Map;

public class Util {
	public static int pageNums = 100;
	public static Map<String, String> getResMsg(String msg){
		Map<String, String> map = new HashMap<>();
		map.put("msg", msg);
		return map;
	}
}
