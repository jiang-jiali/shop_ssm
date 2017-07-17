package cn.hc.shop.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.hc.shop.entities.User;
import cn.hc.shop.service.UserService;
import cn.hc.shop.utils.MailUitls;
import cn.hc.shop.utils.UUIDUtils;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	// user_registPage
	@RequestMapping("/user_registPage")
	public String toUserRegistPage() {
		return "regist";
	}

	@ResponseBody
	@RequestMapping("/checkRegisterUsername")
	public String checkRegisterUsername(@RequestParam("username") String username) {
		return userService.isExistUsername(username);
	}

	@RequestMapping("/user_regist")
	public ModelAndView userRegist(User user, @RequestParam("checkcode") String checkcode, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		String validateCode = (String) session.getAttribute("validateCode");
		if (!validateCode.equalsIgnoreCase(checkcode)) {
			// 验证码错误
			modelAndView.setViewName("regist");
			modelAndView.addObject("checkCodeError", "验证码填写错误");
			return modelAndView;
		}
		modelAndView.setViewName("msg");
		user.setState(0);
		user.setCode(UUIDUtils.getUUID());
		int length = userService.insertUser(user);
		if(length==1){
		modelAndView.addObject("registMsg", "恭喜注册成功，请到邮箱里激活");
		MailUitls.sendMail(user.getEmail(), user.getCode());
		}else{
			modelAndView.setViewName("msg");
			modelAndView.addObject("registMsg", "注册失败");
		}
		return modelAndView;

	}
	@RequestMapping("/user_active")
	public String userActive(@RequestParam("code") String code,Map<String,Object>map){
		User user = userService.selectUserByCode(code);
		if(user==null){
			//用户不存在，或者激活码错误，激活失败
			map.put("activeMsg", "用户不存在或者激活码错误");
			return "msg";
		}
		user.setCode(null);
		user.setState(1);
		int len = userService.modifyUser(user);
		//更新数据库失败
		if(len != 1){
			map.put("activeMsg", "用户不存在或者激活码错误");
			return "msg";
		}
		//更新数据库成功
		map.put("activeMsg", "激活成功，可以登录了");
		return "msg";
	}
	

}
