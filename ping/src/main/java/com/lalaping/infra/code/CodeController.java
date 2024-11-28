package com.lalaping.infra.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lalaping.common.util.UtilDateTime;
import com.lalaping.infra.codegroup.CodeGroupDto;
import com.lalaping.infra.codegroup.CodeGroupService;
import com.lalaping.infra.codegroup.CodeGroupVo;

@Controller
public class CodeController {
	@Autowired
	CodeService codeService;
	@Autowired
	CodeGroupService codeGroupService;
	
	@RequestMapping(value = "/v1/infra/code/codeXdmList")
	public String CodeXdmList(Model model,@ModelAttribute("vo") CodeVo codeVo) {
		codeVo.setShDateStart(codeVo.getShDateStart() == null || codeVo.getShDateStart() == "" ? null : UtilDateTime.add00TimeString(codeVo.getShDateStart()));
		codeVo.setShDateEnd(codeVo.getShDateEnd() == null || codeVo.getShDateEnd() == "" ? null : UtilDateTime.add59TimeString(codeVo.getShDateEnd()));
		codeVo.setParamsPaging(codeService.listCount(codeVo));
		model.addAttribute("list", codeService.selectList(codeVo));
		model.addAttribute("formLink", "codeXdmForm");
		System.out.println("sdsad");
		return "/xdm/v1/infra/code/codeXdmList";
	}
	@RequestMapping(value = "/v1/infra/code/codeXdmForm")
	public String CodeXdmForm(Model model , CodeGroupVo codeGroupVo) {
		model.addAttribute("codeGroups", codeGroupService.selectList(codeGroupVo));
		model.addAttribute("listLink", "codeXdmList");
		return "/xdm/v1/infra/code/codeXdmForm";
	}
	@RequestMapping(value = "/v1/infra/code/codeXdmInst")
	public String CodeXdmInst(CodeDto codeDto) {
		codeService.insert(codeDto);	
		return "redirect:/v1/infra/code/codeXdmList";
	}
	@RequestMapping(value = "/v1/infra/code/codeXdmMfom")
	public String CodeXdmMFom(Model model,CodeDto codeDto,CodeGroupVo codeGroupVo) {
		model.addAttribute("item", codeService.selectOne(codeDto));
		model.addAttribute("codeGroups", codeGroupService.selectList(codeGroupVo));
		model.addAttribute("listLink", "codeXdmList");
		return "/xdm/v1/infra/code/codeXdmMfom";
	}
	@RequestMapping(value = "/v1/infra/code/codeXdmUpdt")
	public String codeXdmUpdt(CodeDto codeDto) {
		codeService.update(codeDto);
		return "redirect:/v1/infra/code/codeXdmList";
	}
	@RequestMapping(value = "/v1/infra/code/codeXdmUete")
	public String codeXdmUete(CodeDto codeDto) {
		codeService.uelete(codeDto);
		return "redirect:/v1/infra/code/codeXdmList";
	}
	@RequestMapping(value = "/v1/infra/code/codeXdmDelt")
	public String codeXdmDelt(CodeDto codeDto) {
		codeService.delete(codeDto);
		return "redirect:/v1/infra/code/codeXdmList";
	}

}
