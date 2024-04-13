package com.mysite.springmemberboard.member;

import java.io.PrintWriter;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller // 컨트롤러는 기본적으로 뷰(view)를 반환한다.
public class MemberController {
	private MemberService memberService; //private MemberServiceImpl memberService
	//MemberServiceImpl로 지정받아도 됨. (자식 타입을 부모타입에 대입하는거라 상관 X)
	public MemberController(MemberService memberService) { //(MemberServiceImpl memberService)
		this.memberService = memberService; //의존성 주입
	}
	
	@RequestMapping("/")
	public String startPage() {
		return "member/loginForm";
	}
	
	@RequestMapping("/loginform.me")
	public String loginPage() {
		return "member/loginForm";
	}
	
	@RequestMapping("/joinform.me")
	public String joinForm() {
		return "member/joinForm";
	}
	
	@RequestMapping("/joinprocess.me")
	public void insertMember(MemberVO memberVO, HttpServletResponse response) throws Exception {
		int res = memberService.insertMember(memberVO);
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		if(res == 1) 
		{
		writer.write("<script>alert('회원 가입 성공!');" +
		"location.href='./loginform.me';</script>");	
		}
		else {
		writer.write("<script>alert('회원 가입 실패!');" + 
		"location.href='./joinform.me';</script>");
		}
	}
	
	@RequestMapping("/login.me")
	public String /*void*/ userCheck(MemberVO memberVO, HttpSession session, HttpServletResponse response) throws Exception {
		int res = memberService.userCheck(memberVO);
		System.out.println("res : " + res);
//		response.setCharacterEncoding("utf-8");
//		response.setContentType("text/html; charset=utf-8");
//		PrintWriter writer = response.getWriter();
		if(res == 1) {
			session.setAttribute("id", memberVO.getId());
			System.out.println("session.getAttribute(id) : " + session.getAttribute("id"));
//			writer.write("<script>alert('로그인 성공!');" + 
//					"location.href='./main.me';</script>");
//			return "redirect:/main.me";
			return "redirect:/boardlist.bo";
		}else {
//			writer.write("<script>alert('로그인 실패!');" + 
//		"location.href='./loginform.me';</script>");
			return "redirect:/loginform.me";
		}
		
	}
	
	@RequestMapping("/main.me")
	public String mainPage(){
		return "member/main";
	}
	
	@RequestMapping("/memberlist.me")
	public String getMemberlist(Model model){ 
		// Model : 뷰에게 전달할 데이터를 저장, 뷰에서는 request.getAttribute("member_list")로 데이터를 가져옴.
		// ModelAndView : 뷰와 데이터를 저장해서 전달.
		ArrayList<MemberVO> member_list = memberService.getMemberlist();
		model.addAttribute("member_list", member_list);
		
		return "member_list";
	}
	
//	@RequestMapping("/memberlist.me")
//	public ModelAndView getMemberlist(ModelAndView mv){ 
//		ArrayList<MemberVO> member_list = memberService.getMemberlist();
//		mv.addObject("member_list", member_list);
//		mv.setViewName("member_list");
//		
//		return mv;
//	}
	
	@RequestMapping("/memberinfo.me")
	public String selectMember(MemberVO memberVO, Model model){ 
		MemberVO vo = memberService.selectMember(memberVO);
		model.addAttribute("memberVO", vo);
		
		return "member/member_info";
	}
	
	@RequestMapping("/updateform.me")
	public String updateMember(MemberVO memberVO, Model model){ 
		MemberVO vo = memberService.selectMember(memberVO);
		model.addAttribute("memberVO", vo);
		
		return "member/member_updateForm";
	}
	
	@RequestMapping("/update.me")
	public String updateMember(MemberVO memberVO){ 
		memberService.updateMember(memberVO);
		return "redirect:/memberlist.me"; //수정하면 바로 memberlist로 이동.
	}
	
	@RequestMapping("/memberdelete.me")
	public String deleteMember(MemberVO memberVO){ 
		memberService.deleteMember(memberVO);
		return "redirect:/memberlist.me"; //수정하면 바로 memberlist로 이동.
	}
	
	
	
}
