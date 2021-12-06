package com.board.springboot.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/board")
@Controller
public class BoardController {

	@GetMapping("/boardList")
	public String boardList() {
		return "board/boardList";
	}
	
	
	
}
