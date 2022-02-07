package com.board.springboot.user.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.board.springboot.user.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession session;
	
	public List<UserVo> selectUserList(UserVo vo){
		return session.selectList("com.board.springboot.user.selectUserList", vo);
	}
	
	public Integer selectListCount(UserVo vo) {
		return session.selectOne("com.board.springboot.user.selectListCount", vo);
	}
	
	public Integer selectLoginCheck(UserVo vo) {
		return session.selectOne("com.board.springboot.user.selectLoginCheck", vo);
	}
	
	public Integer selectCheckId(String id) {
		return session.selectOne("com.board.springboot.user.selectCheckId", id);
	}
	
	public Integer insertUserInfo(UserVo vo) {
		return session.insert("com.board.springboot.user.insertUserInfo", vo);
	}
	
	public Integer deleteUser(String[] deleteArr) {
		return session.delete("com.board.springboot.user.deleteUser", deleteArr);
	}
	
	public UserVo selectJoinInfo(UserVo vo) {
		return session.selectOne("com.board.springboot.user.selectJoinInfo", vo);
	}
}
