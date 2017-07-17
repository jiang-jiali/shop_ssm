package cn.hc.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.hc.shop.dao.CategoryMapper;
import cn.hc.shop.entities.Category;


@Controller
public class TestController {
	
	@Autowired
	private CategoryMapper cg;
	
	@RequestMapping("/test")
	public String test(){
		return "success";
	}
	
	@RequestMapping("/index")
	public String index(){
		return "index";
	}
	
	@RequestMapping("/jdmsg")
	public String jdmsg(){
		Category category = cg.selectByPrimaryKey(1);
		System.out.println(category);
		return "success";
	}
}
