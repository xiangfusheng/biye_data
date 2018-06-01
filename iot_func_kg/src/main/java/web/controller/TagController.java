package web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import web.mapper.LexiconMapper;

@RestController
public class TagController {
	@Autowired
	private LexiconMapper lexiconMapper;
	@ResponseBody
	@RequestMapping("test")
	public List test(){
		return lexiconMapper.getAll();
	}
}
