package web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import web.domain.Lexicon;
import web.mapper.LexiconMapper;



@Controller
public class TagController {
	@Autowired
	private LexiconMapper lexiconMapper;
	
	@RequestMapping(value = "tag", method = RequestMethod.GET)
	public String test(){
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
	@RequestMapping(value = "setIsFunction", method = RequestMethod.GET)
	public String setIsFunction(String ids){
		System.out.println("参数 = " + ids);
		return "success";
	}
}
