package web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import web.domain.BaseFunction;
import web.domain.Lexicon;
import web.mapper.BaseFunctionMapper;
import web.mapper.LexiconMapper;
import web.util.Util;



@Controller
public class TagController {
	@Autowired
	private LexiconMapper lexiconMapper;
	@Autowired
	private BaseFunctionMapper baseFunctionMapper;
	@RequestMapping(value = "tag", method = RequestMethod.GET)
	public String tag(){
		return "tag";
	}

	@ResponseBody
	@RequestMapping(value = "getLexicons", method = RequestMethod.GET)
	public Map<Integer, String> getLexicons(){
		Map<Integer, String> map = new HashMap<>();
		for(Lexicon lexicon : lexiconMapper.getAll()){
			if(lexicon.getIsFunction() == 1){
				map.put(lexicon.getId(), lexicon.getLexicon());
			}
		}
		return map;
	}
	@ResponseBody
	@RequestMapping(value = "getPageLexicons", method = RequestMethod.GET)
	public Map<String, Object> getPageLexicons(int from, int flag){
		Map<String, Object> map = new HashMap<>();
		int total = lexiconMapper.count(1); 
		
		Map<Integer, Lexicon> lexiconMap =  null;
		switch (flag) {
			case 1:
				total += lexiconMapper.count(0);
				lexiconMap = getPageLexiconsByPageNum((from-1) * Util.pageNums);
				break;
			case 2:
				lexiconMap = getPageLexiconsIsFuncByPageNum((from-1) * Util.pageNums);
				break;
			default:
				break;
		}
		int totalPages = total / Util.pageNums;
		if(total % Util.pageNums != 0) totalPages++;
		map.put("totalPages", totalPages);
		map.put("currentPage", from);
		map.put("lexiconMap", lexiconMap);
		return map;
	}
	
	public Map<Integer, Lexicon> getPageLexiconsIsFuncByPageNum(int from){
		Map<Integer, Lexicon> map = new HashMap<>();
		for(Lexicon lexicon : lexiconMapper.getPageIsFunction(from, Util.pageNums)){
			map.put(lexicon.getId(), lexicon);
		}
		return map;
	}
	public Map<Integer, Lexicon> getPageLexiconsByPageNum(int from){
		Map<Integer, Lexicon> map = new HashMap<>();
		for(Lexicon lexicon : lexiconMapper.getPage(from, Util.pageNums)){
			map.put(lexicon.getId(), lexicon);
		}
		return map;
	}
	@ResponseBody
	@RequestMapping(value = "setIsFunction", method = RequestMethod.GET)
	public String setIsFunction(String ids, String idsChecked){
		for(String id : idsChecked.split(",")){
			Lexicon lexicon = lexiconMapper.getById(Integer.valueOf(id));
			lexicon.setIsFunction(0);
			lexiconMapper.update(lexicon);
		}
		for(String id : ids.split(",")){
			Lexicon lexicon = lexiconMapper.getById(Integer.valueOf(id));
			lexicon.setIsSet(1);
			lexiconMapper.update(lexicon);
		}
		return "success";
	}
	
	
	@RequestMapping(value = "classification", method = RequestMethod.GET)
	public String classification(){
		return "classification";
	}
	
	@ResponseBody
	@RequestMapping(value = "getBaseFunctions", method = RequestMethod.GET)
	public Map<Integer, String> getBaseFunctions(){
		Map<Integer, String> map = new HashMap<>();
		for(BaseFunction baseFunction : baseFunctionMapper.getAll()){
			map.put(baseFunction.getId(), baseFunction.getBaseFunction());
		}
		return map;
	}
	
	
}
