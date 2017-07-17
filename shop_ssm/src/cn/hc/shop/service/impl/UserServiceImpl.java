package cn.hc.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hc.shop.dao.UserMapper;
import cn.hc.shop.entities.User;
import cn.hc.shop.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper;

	@Override
	public String isExistUsername(String username) {
		User user = userMapper.checkedUsername(username);
		if(user != null){
			//用户名已经存在了
			return "yes";
		}
		return "no";
	}

	@Override
	public int insertUser(User user) {
		int length = userMapper.insertSelective(user);
		return length;
	}

	@Override
	public User selectUserByCode(String code) {
		User user = userMapper.selectByCode(code);
		return user;
	}

	@Override
	public int modifyUser(User user) {
		return  userMapper.updateByPrimaryKey(user);
		
	}

}
