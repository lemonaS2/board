package com.board.springboot.user.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.board.springboot.user.vo.CodeDtlVo;
import com.board.springboot.user.vo.CodeVo;

@Repository
public class CodeDao {
	
	@Autowired
	private SqlSession session;
	
	public List<CodeVo> selectCodeList(){
		return session.selectList("com.board.springboot.code.selectCodeList");
	}
	
	public List<CodeDtlVo> selectCodeDtlList(CodeDtlVo vo){
		return session.selectList("com.board.springboot.code.selectCodeDtlList", vo);
	}
	
}
