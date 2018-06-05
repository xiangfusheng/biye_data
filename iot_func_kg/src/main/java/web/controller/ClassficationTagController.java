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
public class ClassficationTagController {
	@Autowired
	private LexiconMapper lexiconMapper;
	@Autowired
	private BaseFunctionMapper baseFunctionMapper;
	
	public Map<Integer, Lexicon> getPageLexiconsIsFuncByPageNum(int from){
		Map<Integer, Lexicon> map = new HashMap<>();
		for(Lexicon lexicon : lexiconMapper.getPageIsFunction(from, Util.pageNums)){
			map.put(lexicon.getId(), lexicon);
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "setBaseFunctionId", method = RequestMethod.GET)
	public Map<String, String> setIsFunction(String idsChecked, int baseFunctionId){
		for(String id : idsChecked.split(",")){
			Lexicon lexicon = lexiconMapper.getById(Integer.valueOf(id));
			lexicon.setBaseFunctionId(baseFunctionId);
			lexiconMapper.update(lexicon);
		}
		
		
		return Util.getResMsg("success");
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
