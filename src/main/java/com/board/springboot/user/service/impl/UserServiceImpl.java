package com.board.springboot.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.springboot.user.dao.UserDao;
import com.board.springboot.user.service.UserService;
import com.board.springboot.user.vo.UserVo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public List<UserVo> getUserList(UserVo vo) {
		
		return userDao.selectUserList(vo);
	}
	
	@Override
	public Integer getUserListCount(UserVo vo) {
		return userDao.selectListCount(vo);
	}

	@Override
	public Integer getLoginCheck(UserVo vo) {
		
		return userDao.selectLoginCheck(vo);
	}

	@Override
	public Integer getCheckId(String id) {
		return userDao.selectCheckId(id);
	}

	@Override
	public Integer saveUserInfo(UserVo vo) {
		return userDao.insertUserInfo(vo);
	}

	@Override
	public Integer deleteUser(String[] deleteArr) {
		return userDao.deleteUser(deleteArr);
	}

	@Override
	public UserVo selectJoinInfo(UserVo vo) {
		return userDao.selectJoinInfo(vo);
	}

	
}
