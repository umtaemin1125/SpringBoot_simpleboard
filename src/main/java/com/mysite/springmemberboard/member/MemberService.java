package com.mysite.springmemberboard.member;

import java.util.ArrayList;

public interface MemberService {
	public int insertMember(MemberVO memberVO);
	public MemberVO selectMember(MemberVO memberVO); 
	public int userCheck(MemberVO memberVO);
	public ArrayList<MemberVO> getMemberlist();
	public int deleteMember(MemberVO memberVO);
	public int updateMember(MemberVO memberVO);
}
