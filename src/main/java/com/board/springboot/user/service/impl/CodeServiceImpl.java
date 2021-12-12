package com.board.springboot.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.springboot.user.dao.CodeDao;
import com.board.springboot.user.service.CodeService;
import com.board.springboot.user.vo.CodeDtlVo;
import com.board.springboot.user.vo.CodeVo;
import com.board.springboot.user.vo.UserVo;

@Service
public class CodeServiceImpl implements CodeService {

	@Autowired
	private CodeDao CodeDao;

	@Override
	public List<CodeVo> getCodeList() {
		return CodeDao.selectCodeList();
	}

	@Override
	public List<CodeDtlVo> getCodeDtlList(CodeDtlVo vo) {
		return CodeDao.selectCodeDtlList(vo);
	}
	
	
	
}
