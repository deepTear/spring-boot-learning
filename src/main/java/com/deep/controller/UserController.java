package com.deep.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.deep.entity.User;
import com.deep.entity.UserExample;
import com.deep.service.api.UserService;

@Controller
public class UserController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	@RequestMapping("/user/get/{id}")
	public String info(@PathVariable Integer id,ModelMap modelMap){
		
		User user = null;
		if(id == 1){
			user = userService.findUserd1(id);
		}else{
			user = userService.findUserd2(id);
		}
		
		modelMap.put("user", user);
		return "user";
	}
	
	
	@RequestMapping("/users")
	public String info(@RequestParam(defaultValue="1")Integer pageNum,@RequestParam(defaultValue="10")Integer pageSize,ModelMap modelMap){
		
		List<User> list = userService.findUserPage(pageNum, pageSize);
		int total = userService.findTotalCountdb(new UserExample());
		modelMap.put("users", list);
		modelMap.put("total", total);
		return "userList";
	}
}
