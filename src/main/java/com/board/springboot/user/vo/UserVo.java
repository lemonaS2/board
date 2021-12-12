package com.board.springboot.user.vo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVo {
//	private String userId;
//	private String userPassword;
//	private String userName;
//	private String userGrade;
//	private String userGender;
//	private String userHobby;
//	private String userHiredate;
	
	private String user_id;
	private String user_password;
	private String user_name;
	private String user_grade;
	private String user_gender;
	private String user_hobby;
	private String user_hiredate;
	private List deleteArr;
	private String search;
	
	private int pageSize; // 보여줄 데이터 수
	private int pageOffset; // 보여줄 데이터 기준
}
