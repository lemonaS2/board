package com.board.springboot.user.vo;

import java.security.Timestamp;
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
	private Timestamp user_hiredate;
	private List deleteArr;
	private String search;
	private String dateAll;
	private String ssdt;
	private String hour;
	private String minute;
	
	private int pageSize; // 보여줄 데이터 수
	private int pageOffset; // 보여줄 데이터 기준
	
	@Override
	public String toString() {
		return "UserVo [user_id=" + user_id + ", user_password=" + user_password + ", user_name=" + user_name
				+ ", user_grade=" + user_grade + ", user_gender=" + user_gender + ", user_hobby=" + user_hobby
				+ ", user_hiredate=" + user_hiredate + ", deleteArr=" + deleteArr + ", search=" + search + ", pageSize="
				+ pageSize + ", pageOffset=" + pageOffset + "]";
	}
	
	
}
