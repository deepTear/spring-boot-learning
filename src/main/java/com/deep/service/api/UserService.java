package com.deep.service.api;

import java.util.List;

import com.deep.entity.User;
import com.deep.entity.UserExample;

public interface UserService {

	public User findUserd1(Integer id);
	
	public User findUserd2(Integer id);
	
	public List<User> findUserPage(Integer pageNum,Integer pageSize);
	
	public List<User> findUserPagedb1(Integer pageNum,Integer pageSize);
	
	public List<User> findUserPagedb2(Integer pageNum,Integer pageSize);
	
	public int findTotalCountdb(UserExample example);
	
	public int findCountdb1(UserExample example);
	
	public int findCountdb2(UserExample example);
}
