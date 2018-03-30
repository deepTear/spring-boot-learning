package com.deep.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deep.activemq.QueueProduceService;
import com.deep.base.DS;
import com.deep.entity.User;
import com.deep.entity.UserExample;
import com.deep.mapper.UserMapper;
import com.deep.service.api.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private QueueProduceService queueProduceService;

	@DS("datasource1")
	public User findUserd1(Integer id) {
		
		System.out.println(userMapper + "---------------1");
		queueProduceService.send("查询用户" + id + "的信息");
		return userMapper.selectByPrimaryKey(id);
	}
	
	@DS("datasource2")
	public User findUserd2(Integer id) {
		System.out.println(userMapper + "---------------2");
		queueProduceService.send("查询用户" + id + "的信息");
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<User> findUserPage(Integer pageNum, Integer pageSize) {
		
		List<User> list1 = this.findUserPagedb1(pageNum, pageSize);
		if(list1 != null && list1.size() == pageSize){
			return list1;
		}else{
			List<User> list2 = this.findUserPagedb2(pageNum, pageSize);
			if(list1 != null && list2 != null && list2.size() > 0){
				list1.addAll(list2);
				return list1;
			}
		}
		return null;
	}
	
	@Override
	public int findTotalCountdb(UserExample example) {
		int total1 = this.findCountdb1(example);
		total1 += this.findCountdb2(example);
		return total1;
	}

	@DS("datasource1")
	@Override
	public int findCountdb1(UserExample example) {
		return userMapper.countByExample(example);
	}

	@DS("datasource2")
	@Override
	public int findCountdb2(UserExample example) {
		return userMapper.countByExample(example);
	}

	@DS("datasource1")
	@Override
	public List<User> findUserPagedb1(Integer pageNum, Integer pageSize) {
		UserExample example = new UserExample();
		RowBounds rowBounds = new RowBounds((pageNum - 1) * pageSize, pageSize);
		return userMapper.selectByExampleWithRowbounds(example, rowBounds);
	}

	@DS("datasource2")
	@Override
	public List<User> findUserPagedb2(Integer pageNum, Integer pageSize) {
		UserExample example = new UserExample();
		RowBounds rowBounds = new RowBounds((pageNum - 1) * pageSize, pageSize);
		return userMapper.selectByExampleWithRowbounds(example, rowBounds);
	}
}
