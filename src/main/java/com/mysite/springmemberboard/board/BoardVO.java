package com.mysite.springmemberboard.board;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class BoardVO {
	private int num; //글번호
	private String id; //계정
	private String subject;
	private String content;
	private MultipartFile uploadfile; // 파일 업로드를 위한 필드
	private String org_file;
	private String up_file;
	private int re_ref; //원글, 답글 그룹화
	private int re_lev; // 들여쓰기 효과
	private int re_seq; // 답글의 순서
	private int readcount;
	private Date boarddate;
	
}
