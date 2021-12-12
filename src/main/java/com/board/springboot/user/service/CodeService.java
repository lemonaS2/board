package com.board.springboot.user.service;

import java.util.List;

import com.board.springboot.user.vo.CodeDtlVo;
import com.board.springboot.user.vo.CodeVo;

public interface CodeService {
	
	public List<CodeVo> getCodeList();
	
	public List<CodeDtlVo> getCodeDtlList(CodeDtlVo vo);

}
