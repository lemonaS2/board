package com.board.springboot.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.board.springboot.support.PagingViewInfo;
import com.board.springboot.user.service.UserService;
import com.board.springboot.user.vo.UserVo;

@RequestMapping("/user")
@Controller
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService service;
	
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
	public String join(@RequestParam Map<String, String> paramMap) {
		
		logger.info("join= " + paramMap);
		
		UserVo vo = new UserVo();
		vo.setUser_id(paramMap.get("userId"));
		vo.setUser_password(paramMap.get("userPassword"));
		vo.setUser_name(paramMap.get("userName"));
		vo.setUser_grade(paramMap.get("userGrade"));
		vo.setUser_gender(paramMap.get("userGender"));
		vo.setUser_hobby(paramMap.get("userHobby"));
		
		int result = service.saveUserInfo(vo);
		
		if(result == 1) {
			return "성공";
		}else {
			return "실패";
		}
	}
	
	@GetMapping("/userList")
	public String userList(Model model, Map<String, String> paramMap) {
		UserVo paramVo = new UserVo();
		
		paramVo.setPageSize(10);
		paramVo.setPageOffset(0);
//		paramVo.setPageOffset(paramVo.getPageSize() * (Integer.parseInt(paramMap.get("pageNum")) -1));
		
		// 리스트
		List<UserVo> userList = service.getUserList(paramVo);
		
		// 총데이터 수
		int totalCount = service.getUserListCount(paramVo);
		
		
		PagingViewInfo pagingInfo = new PagingViewInfo(totalCount, 1, 10); 
//		PagingViewInfo pagingInfo = new PagingViewInfo(totalCount, Integer.parseInt(paramMap.get("pageNum")), Integer.parseInt(paramMap.get("pageSize"))); 
		
		model.addAttribute("userList", userList);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("pagingInfo", pagingInfo);
		
		return "user/userList";
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
	
}
