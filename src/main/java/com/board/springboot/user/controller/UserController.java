package com.board.springboot.user.controller;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.board.springboot.support.PagingViewInfo;
import com.board.springboot.user.service.CodeService;
import com.board.springboot.user.service.UserService;
import com.board.springboot.user.vo.CodeDtlVo;
import com.board.springboot.user.vo.CodeVo;
import com.board.springboot.user.vo.UserVo;

@RequestMapping("/user")
@Controller
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService service;
	
	@Autowired
	private CodeService codeService;
	
	@GetMapping("/loginForm")
	public String loginForm() {
		
		return "user/loginForm";
	}
	
	@ResponseBody
	@GetMapping("/login")
	public String login(@RequestParam Map<String, String> paramMap, Model model) {
		
		logger.info("login= " + paramMap);
		
		UserVo vo = new UserVo();
		vo.setUser_id(paramMap.get("userId"));
		vo.setUser_password(paramMap.get("userPassword"));
		 
		
		int result = service.getLoginCheck(vo);
		
		logger.info("result= " + result);
		
		if(result == 0) {
			return "실패";
		}else {
			return "성공";
		}
		
	}
	
	@GetMapping("/joinForm")
	public String joinForm() {
		
		return "user/joinForm";
	}
	
	@GetMapping("/joinFormDtl/{id}")
	public String joinFormDtl(@PathVariable String id, Model model) {
		
		UserVo vo = new UserVo();
		vo.setUser_id(id);
		
		UserVo data = service.selectJoinInfo(vo);
		
		logger.info("date " + data.getUser_hiredate());
		
//		SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");
//		data.setSsdt(ymd.format(date));
		
		model.addAttribute("data", data);
		
		return "user/joinFormDtl";
	}
	
	@ResponseBody
	@GetMapping("/checkId")
	public String checkId(@RequestParam Map<String, String> paramMap, Model model) {
		
		logger.info("checkId= " + paramMap);
		
		
		String id = paramMap.get("userId");
		 
		
		int result = service.getCheckId(id);
		
		logger.info("result= " + result);
		
		if(result == 0) {
			return "미존재";
		}else {
			return "존재";
		}
		
	}
	
	@ResponseBody
	@PostMapping("/join")
	public String join(UserVo vo) {
//		
//		UserVo vo2 = new UserVo();
//		vo2.setUser_id(vo.getUser_id());
//		vo2.setUser_password(vo.getUser_password());
//		vo2.setUser_name(vo.getUser_name());
//		vo2.setUser_grade(vo.getUser_grade());
//		vo2.setUser_gender(vo.getUser_gender());
//		vo2.setUser_hobby(vo.getUser_hobby());
//		vo2.setDateAll(vo.getDateAll());
		
		logger.info("join= " + vo.toString());
		
		int result = service.saveUserInfo(vo);
		
		if(result == 1) {
			return "성공";
		}else {
			return "실패";
		}
	}
	
	@GetMapping("/userList")
	public String userList(Model model) {
		
		List<CodeVo> codeList = codeService.getCodeList();
		
		model.addAttribute("codeList", codeList);
		
		return "user/userList";
	}
	
	@GetMapping("/getUserList")
	public String getUserList(@RequestParam Map<String, String> paramMap, Model model) {

		paramMap.put("pageSize", "5");
//		paramMap.put("pageNum", "1");
		
		UserVo paramVo = new UserVo();
		
		paramVo.setPageSize(Integer.parseInt(paramMap.get("pageSize")));
		paramVo.setPageOffset(paramVo.getPageSize() * (Integer.parseInt(paramMap.get("pageNum")) -1 ) );
		
		// 리스트
		List<UserVo> userList = service.getUserList(paramVo);
		
		// 총데이터 수
		int totalCount = service.getUserListCount(paramVo);
		
		
		PagingViewInfo pagingInfo = new PagingViewInfo(totalCount, Integer.parseInt(paramMap.get("pageNum")), Integer.parseInt(paramMap.get("pageSize"))); 
		
		model.addAttribute("userList", userList);
		model.addAttribute("pageSize", paramMap.get("pageSize"));
		model.addAttribute("pageNum", paramMap.get("pageNum"));
		model.addAttribute("pagingInfo", pagingInfo);
		
		return "user/userListAsync";
	}
	
	@PostMapping("getCodeDtlList")
	@ResponseBody
	public List<CodeDtlVo> getCodeDtlList(@RequestBody CodeDtlVo vo){
		
		List<CodeDtlVo> codeDtlList = codeService.getCodeDtlList(vo);
		
		return codeDtlList;
	}
	
	@ResponseBody
	@PostMapping("/deleteUser")
	public Map<String, String> deleteUser(@RequestParam(value="deleteArr[]") String[] deleteArr) {
		
		for(int i = 0; i < deleteArr.length; i++) {
			System.out.println(deleteArr[i]); 
		}
		
		int result = 0;
		
		if(deleteArr.length > 0) {
			result = service.deleteUser(deleteArr);
		}
		
		String resultCode;
		String resultMsg;
		
		Map<String, String> resultMap = new HashMap<String, String>();
		
		if(result > 0) {
			resultCode = "SUCCESS";
			resultMsg = "성공";
		}else {
			resultCode = "FAIL";
			resultMsg = "실패";
		}
		
		resultMap.put("resultCode", resultCode);
		resultMap.put("resultMsg", resultMsg);
		
		return resultMap;
	}
	
	@GetMapping("/openPopUp")
	public String openPopUp(Model model) {
		
		UserVo paramVo = new UserVo();
		paramVo.setPageSize(10);
		paramVo.setPageOffset(0);
		
		List<UserVo> userList = service.getUserList(paramVo);
		
		model.addAttribute("userList", userList);
		
		return "user/popUp";
	}
	
	
}
