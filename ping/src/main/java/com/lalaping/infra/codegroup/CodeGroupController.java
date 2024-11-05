package com.lalaping.infra.codegroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lalaping.common.util.UtilDateTime;
import com.lalaping.infra.code.CodeService;

@Controller
public class CodeGroupController {
	@Autowired
	CodeGroupService codeGroupService;
	
	@RequestMapping(value = "/v1/infra/codegroup/codeGroupXdmList")
	public String codeGroupXdmList(@ModelAttribute("vo") CodeGroupVo codeGroupVo, Model model) {
		System.out.println("시작날:"+codeGroupVo.getShDateStart());
		System.out.println("마감날:"+codeGroupVo.getShDateEnd());
		codeGroupVo.setShDateStart(codeGroupVo.getShDateStart() == null || codeGroupVo.getShDateStart() == "" ? null : UtilDateTime.add00TimeString(codeGroupVo.getShDateStart()));
		codeGroupVo.setShDateEnd(codeGroupVo.getShDateEnd() == null || codeGroupVo.getShDateEnd() == "" ? null : UtilDateTime.add59TimeString(codeGroupVo.getShDateEnd()));
		codeGroupVo.setParamsPaging(codeGroupService.listCount(codeGroupVo));
		System.out.println("nqonoifqs");
		model.addAttribute("list", codeGroupService.selectList(codeGroupVo));
//		for(CodeGroupDto item : codeGroupService.selectList(codeGroupVo)) {
//			System.out.println("리스트seq: " + item.get );
//		}
		model.addAttribute("formLink", "codeGroupXdmForm");
		return "/xdm/v1/infra/codegroup/codeGroupXdmList";
	}
	
	@RequestMapping(value = "/v1/infra/codegroup/codeGroupXdmForm")
	public String codeGroupXdmForm(Model model) {
		model.addAttribute("listLink", "codeGroupXdmList");
		return "/xdm/v1/infra/codegroup/codeGroupXdmForm";
	}
	 
	@RequestMapping(value = "/v1/infra/codegroup/codeGroupXdmInst")
	public String codeGroupXdmInst(CodeGroupDto codeGroupDto) {
		codeGroupService.insert(codeGroupDto);
		return "redirect:/v1/infra/codegroup/codeGroupXdmList";
	}
	
	@RequestMapping(value = "/v1/infra/codegroup/codeGroupXdmMfom")
	public String codeGroupXdmMfom(Model model,CodeGroupDto codeGroupDto) {
		model.addAttribute("item", codeGroupService.selectOne(codeGroupDto));
		model.addAttribute("listLink", "codeGroupXdmList");
		return "xdm/v1/infra/codegroup/codeGroupXdmMfom";
	}
	@RequestMapping(value = "/v1/infra/codegroup/codeGroupXdmUpdt")
	public String codeGroupXdmUpdt(CodeGroupDto codeGroupDto) {
		codeGroupService.update(codeGroupDto);
		return "redirect:/v1/infra/codegroup/codeGroupXdmList";
	}
	@RequestMapping(value = "/v1/infra/codegroup/codeGroupXdmUelt")
	public String codeGroupXdmUelt(CodeGroupDto codeGroupDto) {
		codeGroupService.uelete(codeGroupDto);
		return "redirect:/v1/infra/codegroup/codeGroupXdmList";
	}
	@RequestMapping(value = "/v1/infra/codegroup/codeGroupXdmDelt")
	public String codeGroupXdmDelt(CodeGroupDto codeGroupDto) {
		codeGroupService.delete(codeGroupDto);
		return "redirect:/v1/infra/codegroup/codeGroupXdmList";
	}
}
