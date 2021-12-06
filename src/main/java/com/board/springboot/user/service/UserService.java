package com.board.springboot.user.service;

import java.util.List;

import com.board.springboot.user.vo.UserVo;

public interface UserService {
	
	public List<UserVo> getUserList(UserVo vo);
	
	public Integer getLoginCheck(UserVo vo);
	
	public Integer getCheckId(String id);
	
	public Integer saveUserInfo(UserVo vo); 
	
	public Integer getUserListCount(UserVo vo); 
	
	public Integer deleteUser(String[] deleteArr);
}
