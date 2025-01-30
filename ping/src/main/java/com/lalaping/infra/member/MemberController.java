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
		memberVo.setShDateStart(
				memberVo.getShDateStart() == null || memberVo.getShDateStart().isEmpty() ? null
						: UtilDateTime.add00TimeString(memberVo.getShDateStart()));
		memberVo.setShDateEnd(memberVo.getShDateEnd() == null || memberVo.getShDateEnd().isEmpty() ? null
				: UtilDateTime.add59TimeString(memberVo.getShDateEnd()));
		memberVo.setParamsPaging(memberService.listCount(memberVo));
		model.addAttribute("list", memberService.selectList(memberVo));
		model.addAttribute("formLink", "memberXdmForm");
		return "xdm/v1/infra/member/memberXdmList";
	}

	@RequestMapping(value = "/v1/member/memberXdmForm")
	public String memberXdmForm(Model model) {
		model.addAttribute("listLink", "memberXdmList");
		return "xdm/v1/infra/member/memberXdmForm";
	}
	@RequestMapping(value = "/v1/member/memberXdmMFom")
	public String memberXdmMfom(Model model,MemberDto memberDto) {
		model.addAttribute("item", memberService.selectOne(memberDto));
		model.addAttribute("listLink", "memberXdmList");
		return "xdm/v1/infra/member/memberXdmMFom";
	}
	@RequestMapping(value = "/v1/member/resetPassword")
	public String resetPassword(Model model) {
		model.addAttribute("listLink", "memberXdmList");
		return "xdm/v1/infra/base/resetPassword";
	}
	@RequestMapping(value = "/v1/member/forgotPassword")
	public String forgotPassword(Model model) {
		model.addAttribute("listLink", "memberXdmList");
		return "xdm/v1/infra/base/forgotPassword";
	}

	// CRUD
	@RequestMapping(value = "/v1/member/memberXdmInst")
	public String memberXdmInst(MemberDto memberDto) {
		memberDto.setMmPasswd(encodeBcrypt(memberDto.getMmPasswd(), 10));
		int inst = memberService.insertMember(memberDto);
		return "redirect:/v1/member/memberXdmList";
	}
	@RequestMapping(value = "/v1/member/staffMemberXdmInst")
	public String staffMemberXdmInst(MemberDto memberDto) {
		memberDto.setMmPasswd(encodeBcrypt(memberDto.getMmPasswd(), 10));
		int inst = memberService.staffMemberXdmInst(memberDto);
		return "redirect:/v1/loginXdm";
	}
	@RequestMapping(value = "/v1/member/memberXdmUpdate")
	public String memberXdmUpdate(MemberDto memberDto) {
		System.out.println("생일:"+memberDto.getMmBirthDay());
		int updt = memberService.updateMember(memberDto);
		return "redirect:/v1/member/memberXdmList";
	}
	@RequestMapping(value = "/v1/member/memberXdmDelete")
	public String memberXdmDelete(MemberDto memberDto) {
		int delt = memberService.deleteMember(memberDto);
		return "redirect:/v1/member/memberXdmList";
	}
	@RequestMapping(value = "/v1/member/memberXdmUelete")
	public String memberXdmUelete(MemberDto memberDto) {
		int uelt = memberService.ueleteMember(memberDto);
		return "redirect:/v1/member/memberXdmList";
	}
	@RequestMapping(value = "/v1/member/resetPW")
	public String resetPW(MemberDto memberDto) {
		int uelt = memberService.ueleteMember(memberDto);
		return "redirect:/v1/loginXdm";
	}
	@RequestMapping(value = "/v1/member/searchUser")
	public String searchUser(MemberDto memberDto) {
		int uelt = memberService.ueleteMember(memberDto);
		return "redirect:/v1/loginXdm";
	}
	//로그인
	@RequestMapping(value = "/v1/loginXdm")
	public String loginXdm() {
		return "xdm/v1/infra/base/loginXdm";
	}
	@RequestMapping(value = "/v1/signupXdm")
	public String signupXdm() {
		return "xdm/v1/infra/base/signupXdm";
	}

	@ResponseBody
	@RequestMapping(value = "/v1/infra/loginXdmProc")
	public Map<String, Object> loginXdmProc(MemberDto memberDto, HttpSession httpSession) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>(); 
		MemberDto rtLogin = memberService.selectOneLogin(memberDto); 

		if(rtLogin != null) { 
			if(matchesBcrypt(memberDto.getMmPasswd(), rtLogin.getMmPasswd(), 10)) {
				httpSession.setMaxInactiveInterval(60 * 30); 
				httpSession.setAttribute("sessIdXdm", rtLogin.getMmEmail()); 
				httpSession.setAttribute("sessSeqXdm", rtLogin.getMmSeq()); 
				httpSession.setAttribute("sessNameXdm", rtLogin.getMmName());
	
				returnMap.put("rt", "success"); 
			}else {
				returnMap.put("rt", "fail");
			}
		}else{ 
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
		return "usr/v1/etc/ping_login";
	}
	@RequestMapping(value = "/v1/register")
	public String register() {
		return "usr/v1/etc/ping_register";
	}
	@RequestMapping(value = "registerInst")
	@ResponseBody
	public Map<String, Object> registerInst(MemberDto memberDto) {
		Map<String, Object> returnMap = new HashMap<>();
		memberDto.setMmPasswd(encodeBcrypt(memberDto.getMmPasswd(), 10));
		memberService.insertMember(memberDto);
		returnMap.put("rt", "success");
		return returnMap;
	}
	@RequestMapping(value = "/v1/member/editMember")
	public String editMember(Model model, MemberDto memberDto, HttpSession session) {
		String sessSeqUsr = String.valueOf(session.getAttribute("sessSeqUsr"));
		memberDto.setMmSeq(sessSeqUsr);
		model.addAttribute("item", memberService.selectOne(memberDto));
		return "usr/v1/member/ping_editMember";
	}
	@RequestMapping(value = "memberUpdate")
	public String memberUpdate(MemberDto memberDto) {
		memberService.updateMember(memberDto);
		return "redirect:/v1/member/editMember";
	}
	@RequestMapping(value = "passWdUpdate")
	@ResponseBody
	public Map<String, Object> passWdUpdate(MemberDto memberDto ,HttpSession session) {
		Map<String, Object> returnMap = new HashMap<>();
		String sessIdUsr = String.valueOf(session.getAttribute("sessIdUsr"));
		String sessSeqUsr = String.valueOf(session.getAttribute("sessSeqUsr"));
		memberDto.setMmEmail(sessIdUsr);
		memberDto.setMmSeq(sessSeqUsr);
		MemberDto rtMember = memberService.selectOneLogin(memberDto);
		if(matchesBcrypt(memberDto.getMmPasswdChk(), rtMember.getMmPasswd(), 10)) {
			memberDto.setMmPasswd(encodeBcrypt(memberDto.getMmPasswd(), 10));
			memberService.updatePasswd(memberDto);
			returnMap.put("rt", "success");
		}else {
			returnMap.put("rt", "fail");
		}
		
		return returnMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "loginUsrProc")
	public Map<String, Object> loginUsrProc(MemberDto memberDto, HttpSession httpSession,HttpServletRequest request) throws Exception {
		Map<String, Object> returnMap = new HashMap<>();
		MemberDto rtMember = memberService.selectOneLogin(memberDto);
		if (rtMember != null) {
			if(matchesBcrypt(memberDto.getMmPasswd(), rtMember.getMmPasswd(), 10)) {
				httpSession.setMaxInactiveInterval(60 * Constants.SESSION_MINUTE); // 60second * 30 = 30minute
				httpSession.setAttribute("sessSeqUsr", rtMember.getMmSeq());
				httpSession.setAttribute("sessIdUsr", rtMember.getMmEmail());
				httpSession.setAttribute("sessNameUsr", rtMember.getMmName());
				String prevPage = (String) httpSession.getAttribute("prevPage");
				httpSession.removeAttribute("prevPage"); 
				returnMap.put("redirectUrl", prevPage != null ? prevPage : "/v1/index");
				returnMap.put("rt", "success");
			}else {
				returnMap.put("rt", "fail");
			}
			
		} else {
			returnMap.put("rt", "fail");
		}
		return returnMap;
	}
	@ResponseBody
	@RequestMapping(value = "logoutUsrProc")
	public Map<String, Object> logoutUsrProc(HttpSession httpSession) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		httpSession.invalidate();
		returnMap.put("rt", "success");
		return returnMap;
	}
	
	

}