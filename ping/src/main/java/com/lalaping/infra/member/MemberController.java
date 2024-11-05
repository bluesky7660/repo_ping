package com.lalaping.infra.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lalaping.common.util.UtilDateTime;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;

	// Member 영역
	@RequestMapping(value = "/v1/member/mmberXdmList")
	public String memberXdmList(@ModelAttribute("vo") MemberVo memberVo, Model model) {
		System.out.println("시작날: " + memberVo.getShDateStart());
		System.out.println("마감날: " + memberVo.getShDateEnd());

		memberVo.setShDateStart(
				memberVo.getShDateStart() == null || memberVo.getShDateStart().isEmpty() ? null
						: UtilDateTime.add00TimeString(memberVo.getShDateStart()));
		memberVo.setShDateEnd(memberVo.getShDateEnd() == null || memberVo.getShDateEnd().isEmpty() ? null
				: UtilDateTime.add59TimeString(memberVo.getShDateEnd()));
		memberVo.setParamsPaging(memberService.listCountMember(memberVo));
		model.addAttribute("list", memberService.selectMemberList(memberVo));
		model.addAttribute("formLink", "memberXdmForm");
		return "/xdm/v1/member/stf/memberXdmList";
	}

	@RequestMapping(value = "/v1/member/memberXdmForm")
	public String memberXdmForm(Model model) {
		model.addAttribute("listLink", "memberXdmList");
		return "/xdm/v1/member/stf/memberXdmForm";
	}
	@RequestMapping(value = "/v1/member/memberXdmMfom")
	public String memberXdmMfom(Model model,MemberDto memberDto) {
		model.addAttribute("item", memberService.selectOne(memberDto));
		model.addAttribute("listLink", "memberXdmList");
		return "/xdm/v1/member/stf/memberXdmMfom";
	}

	// CRUD
	@RequestMapping(value = "/v1/member/memberXdmInst")
	public String memberXdmInst(MemberDto memberDto) {
		System.out.println("MemberDto.getsfName(): " + memberDto.getSfSeq());
		int inst = memberService.insertMember(memberDto);
		System.out.println("memberService.insertMember(memberDto): " + inst);
		return "redirect:/v1/member/memberXdmList";
	}
	@RequestMapping(value = "/v1/member/memberXdmUpdate")
	public String memberXdmUpdate(MemberDto memberDto) {
		System.out.println("MemberDto.getsfSeq(): " + memberDto.getSfSeq());
		int updt = memberService.updateMember(memberDto);
		System.out.println("memberService.updateMember(memberDto): " + updt);
		return "redirect:/v1/member/memberXdmList";
	}
	@RequestMapping(value = "/v1/member/memberXdmDelete")
	public String memberXdmDelete(MemberDto memberDto) {
		System.out.println("MemberDto.getsfSeq(): " + memberDto.getSfSeq());
		int delt = memberService.deleteMember(memberDto);
		System.out.println("memberService.deleteMember(memberDto): " + delt);
		return "redirect:/v1/member/memberXdmList";
	}
	@RequestMapping(value = "/v1/member/memberXdmUelete")
	public String memberXdmUelete(MemberDto memberDto) {
		System.out.println("MemberDto.getsfSeq(): " + memberDto.getSfSeq());
		int uelt = memberService.ueleteMember(memberDto);
		System.out.println("memberService.ueleteMember(memberDto): " + uelt);
		return "redirect:/v1/member/memberXdmList";
	}
	//---------------------------------------------------------------------------------------
	// UserMember 영역
//	@RequestMapping(value = "/v1/member/userMemberXdmList")
//	public String userMemberXdmList(@ModelAttribute("vo") UserMemberVo userMemberVo, Model model) {
//	    System.out.println("시작날: " + userMemberVo.getShDateStart());
//	    System.out.println("마감날: " + userMemberVo.getShDateEnd());
//
//	    userMemberVo.setShDateStart(
//	        userMemberVo.getShDateStart() == null || userMemberVo.getShDateStart().isEmpty() ? null
//	            : UtilDateTime.add00TimeString(userMemberVo.getShDateStart()));
//	    userMemberVo.setShDateEnd(userMemberVo.getShDateEnd() == null || userMemberVo.getShDateEnd().isEmpty() ? null
//	            : UtilDateTime.add59TimeString(userMemberVo.getShDateEnd()));
//	    userMemberVo.setParamsPaging(memberService.listCountUserMember(userMemberVo));
//	    System.out.println("값없으면 나옴:"+memberService.selectUserMemberList(userMemberVo)+"@@@@@@@@@@@@");
//	    model.addAttribute("list", memberService.selectUserMemberList(userMemberVo));
//	    model.addAttribute("formLink", "userMemberXdmForm");
//	    return "/xdm/v1/member/usr/userMemberXdmList";
//	}
//
//	@RequestMapping(value = "/v1/member/userMemberXdmForm")
//	public String userMemberXdmForm(Model model) {
////		model.addAttribute("userItem", memberService.selectUserMemberList(userMemberDto));
//		model.addAttribute("listLink", "userMemberXdmList");
//	    return "/xdm/v1/member/usr/userMemberXdmForm";
//	}
//	@RequestMapping(value = "/v1/member/userMemberXdmMfom")
//	public String userMemberXdmMfom(Model model, UserMemberDto userMemberDto) {
//		model.addAttribute("item", memberService.userSelectOne(userMemberDto));
//		model.addAttribute("listLink", "userMemberXdmList");
//	    return "/xdm/v1/member/usr/userMemberXdmMfom";
//	}
//
//	// CRUD
//	@RequestMapping(value = "/v1/member/userMemberXdmInst")
//	public String userMemberXdmInst(UserMemberDto userMemberDto) {
//	    System.out.println("UserMemberDto.getUmName(): " + userMemberDto.getUmName());
//	    int inst = memberService.insertUserMember(userMemberDto);
//	    System.out.println("memberService.insertUserMember(userMemberDto): " + inst);
//	    return "redirect:/v1/member/userMemberXdmList";
//	}
//	@RequestMapping(value = "/v1/member/userMemberXdmUpdate")
//	public String userMemberXdmUpdate(UserMemberDto userMemberDto) {
//	    System.out.println("UserMemberDto.getUmSeq(): " + userMemberDto.getUmSeq());
//	    int updt = memberService.updateUserMember(userMemberDto);
//	    System.out.println("memberService.updateUserMember(userMemberDto): " + updt);
//	    return "redirect:/v1/member/userMemberXdmList";
//	}
//	@RequestMapping(value = "/v1/member/userMemberXdmDelete")
//	public String userMemberXdmDelete(UserMemberDto userMemberDto) {
//	    System.out.println("UserMemberDto.getUmSeq(): " + userMemberDto.getUmSeq());
//	    int delt = memberService.deleteUserMember(userMemberDto);
//	    System.out.println("memberService.deleteUserMember(userMemberDto): " + delt);
//	    return "redirect:/v1/member/userMemberXdmList";
//	}
//	@RequestMapping(value = "/v1/member/userMemberXdmUelete")
//	public String userMemberXdmUelete(UserMemberDto userMemberDto) {
//	    System.out.println("UserMemberDto.getUmSeq(): " + userMemberDto.getUmSeq());
//	    int uelt = memberService.ueleteUserMember(userMemberDto);
//	    System.out.println("memberService.ueleteUserMember(userMemberDto): " + uelt);
//	    return "redirect:/v1/member/userMemberXdmList";
//	}

}