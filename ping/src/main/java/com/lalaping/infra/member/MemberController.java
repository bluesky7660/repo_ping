package com.lalaping.infra.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lalaping.common.util.Constants;
import com.lalaping.common.util.UtilDateTime;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;

	//인코딩  
	public String encodeBcrypt(String planeText, int strength) {
		  return new BCryptPasswordEncoder(strength).encode(planeText);
	}

			
	public boolean matchesBcrypt(String planeText, String hashValue, int strength) {
	  BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(strength);
	  return passwordEncoder.matches(planeText, hashValue);
	}
	
	// Member 영역
	@RequestMapping(value = "/v1/member/memberXdmList")
	public String memberXdmList(@ModelAttribute("vo") MemberVo memberVo, Model model) {
		System.out.println("시작날: " + memberVo.getShDateStart());
		System.out.println("마감날: " + memberVo.getShDateEnd());

		memberVo.setShDateStart(
				memberVo.getShDateStart() == null || memberVo.getShDateStart().isEmpty() ? null
						: UtilDateTime.add00TimeString(memberVo.getShDateStart()));
		memberVo.setShDateEnd(memberVo.getShDateEnd() == null || memberVo.getShDateEnd().isEmpty() ? null
				: UtilDateTime.add59TimeString(memberVo.getShDateEnd()));
		memberVo.setParamsPaging(memberService.listCount(memberVo));
		model.addAttribute("list", memberService.selectList(memberVo));
		model.addAttribute("formLink", "memberXdmForm");
		return "/xdm/v1/infra/member/memberXdmList";
	}

	@RequestMapping(value = "/v1/member/memberXdmForm")
	public String memberXdmForm(Model model) {
		model.addAttribute("listLink", "memberXdmList");
		return "/xdm/v1/infra/member/memberXdmForm";
	}
	@RequestMapping(value = "/v1/member/memberXdmMfom")
	public String memberXdmMfom(Model model,MemberDto memberDto) {
		model.addAttribute("item", memberService.selectOne(memberDto));
		model.addAttribute("listLink", "memberXdmList");
		return "/xdm/v1/infra/member/memberXdmMfom";
	}

	// CRUD
	@RequestMapping(value = "/v1/member/memberXdmInst")
	public String memberXdmInst(MemberDto memberDto) {
		System.out.println("MemberDto.getsfName(): " + memberDto.getMmSeq());
		memberDto.setMmPasswd(encodeBcrypt(memberDto.getMmPasswd(), 10));
		int inst = memberService.insertMember(memberDto);
		System.out.println("memberService.insertMember(memberDto): " + inst);
		return "redirect:/v1/member/memberXdmList";
	}
	@RequestMapping(value = "/v1/member/memberXdmUpdate")
	public String memberXdmUpdate(MemberDto memberDto) {
		System.out.println("MemberDto.getsfSeq(): " + memberDto.getMmSeq());
		int updt = memberService.updateMember(memberDto);
		System.out.println("memberService.updateMember(memberDto): " + updt);
		return "redirect:/v1/member/memberXdmList";
	}
	@RequestMapping(value = "/v1/member/memberXdmDelete")
	public String memberXdmDelete(MemberDto memberDto) {
		System.out.println("MemberDto.getsfSeq(): " + memberDto.getMmSeq());
		int delt = memberService.deleteMember(memberDto);
		System.out.println("memberService.deleteMember(memberDto): " + delt);
		return "redirect:/v1/member/memberXdmList";
	}
	@RequestMapping(value = "/v1/member/memberXdmUelete")
	public String memberXdmUelete(MemberDto memberDto) {
		System.out.println("MemberDto.getsfSeq(): " + memberDto.getMmSeq());
		int uelt = memberService.ueleteMember(memberDto);
		System.out.println("memberService.ueleteMember(memberDto): " + uelt);
		return "redirect:/v1/member/memberXdmList";
	}
	//로그인
	@RequestMapping(value = "/v1/loginXdm")
	public String loginXdm() {
		return "/xdm/v1/infra/base/loginXdm";
	}
	@RequestMapping(value = "/v1/signupXdm")
	public String signupXdm() {
		return "/xdm/v1/infra/base/signupXdm";
	}

	@ResponseBody
	@RequestMapping(value = "/v1/infra/loginXdmProc")
	public Map<String, Object> loginXdmProc(MemberDto memberDto, HttpSession httpSession) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>(); 
		System.out.println("로그인 요청 - ID: " + memberDto.getMmEmail() + ", PW: " + memberDto.getMmPasswd());

		MemberDto rtLogin = memberService.selectOneLogin(memberDto); 
		System.out.println("rtLogin : " +  memberService.selectOneLogin(memberDto));
		System.out.println("sessIdXdm : " +  rtLogin.getMmEmail());
		System.out.println("sessSeqXdm : " +  rtLogin.getMmSeq());
		System.out.println("sessNameXdm : " +  rtLogin.getMmName());
		System.out.println("로그인 정보 조회 결과: " + rtLogin);

		if (rtLogin != null) { 
			if(matchesBcrypt(memberDto.getMmPasswd(), rtLogin.getMmPasswd(), 10)) {
				httpSession.setMaxInactiveInterval(60 * 30); 
				httpSession.setAttribute("sessIdXdm", rtLogin.getMmEmail()); 
				httpSession.setAttribute("sessSeqXdm", rtLogin.getMmSeq()); 
				httpSession.setAttribute("sessNameXdm", rtLogin.getMmName());
	
				returnMap.put("rt", "success"); 
			}else {
				returnMap.put("rt", "fail");
			}
//			httpSession.setMaxInactiveInterval(60 * 30); 
//			httpSession.setAttribute("sessIdXdm", rtLogin.getMmEmail()); 
//			httpSession.setAttribute("sessSeqXdm", rtLogin.getMmSeq()); 
//			httpSession.setAttribute("sessNameXdm", rtLogin.getMmName());
//
//			returnMap.put("rt", "success"); 
		} else { 
			System.out.println("로그인 실패: " + memberDto.getMmEmail()); 
			returnMap.put("rt", "fail"); 
		} 
		return returnMap; 
	} 

	@ResponseBody 
	@RequestMapping(value = "/v1/infra/logoutXdmProc") 
	public Map<String, Object> logoutXdmProc(HttpSession httpSession) throws Exception { 
		Map<String, Object> returnMap = new HashMap<>(); 
		httpSession.invalidate(); 
		System.out.println("Login Session 무효화 성공"); 
		returnMap.put("rt", "success"); 
		returnMap.put("redirectUrl", "/v1/loginXdm"); 
		return returnMap; 
	}
	
	//usr
	@RequestMapping(value = "/v1/login")
	public String login() {
		return "/usr/v1/etc/ping_login";
	}
	@RequestMapping(value = "/v1/register")
	public String register() {
		return "/usr/v1/etc/ping_register";
	}
	
	@ResponseBody
	@RequestMapping(value = "loginUsrProc")
	public Map<String, Object> loginUsrProc(MemberDto memberDto, HttpSession httpSession,HttpServletRequest request) throws Exception {
		
		System.out.println("loginUsrProc");
		Map<String, Object> returnMap = new HashMap<>();
		
		MemberDto rtMember = memberService.selectOne(memberDto);
		System.out.println("rtMember: " + rtMember);
		if (rtMember != null) {
			if(matchesBcrypt(memberDto.getMmPasswd(), rtMember.getMmPasswd(), 10)) {
//			if(true) {
				httpSession.setMaxInactiveInterval(60 * Constants.SESSION_MINUTE_XDM); // 60second * 30 = 30minute
				httpSession.setAttribute("sessSeqXdm", rtMember.getMmSeq());
				httpSession.setAttribute("sessIdXdm", rtMember.getMmEmail());
				httpSession.setAttribute("sessNameXdm", rtMember.getMmName());
				
				String prevPage = (String) httpSession.getAttribute("prevPage");
				System.out.println("주소테스트: "+prevPage);
				httpSession.removeAttribute("prevPage"); 
				returnMap.put("redirectUrl", prevPage != null ? prevPage : "/index");
				
				System.out.println("성공");
				
				returnMap.put("rt", "success");
			}else {
				returnMap.put("rt", "fail");
			}
			
		} else {
			System.out.println("실패");
			returnMap.put("rt", "fail");
		}
		return returnMap;
	}
	@ResponseBody
	@RequestMapping(value = "logoutUsrProc")
	public Map<String, Object> logoutUsrProc(HttpSession httpSession) throws Exception {
		System.out.println("logoutUsrProc");
		Map<String, Object> returnMap = new HashMap<String, Object>();
		httpSession.invalidate();
		returnMap.put("rt", "success");
		return returnMap;
	}
	
	

}