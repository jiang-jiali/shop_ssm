package cn.hc.shop.service;

import cn.hc.shop.entities.User;

public interface UserService {

	String isExistUsername(String username);

	int insertUser(User user);

	User selectUserByCode(String code);

	int modifyUser(User user);

}
