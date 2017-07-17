package cn.hc.shop.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.hc.shop.dao.UserMapper;
import cn.hc.shop.entities.User;

public class TestSpringMybatis {

	@Test
	public void test() {
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring/spring-mybatis.xml");
		UserMapper um =  (UserMapper) ac.getBean("userMapper");
		List<User> list = um.selectByExample(null);
		System.out.println(list);
	}

}
