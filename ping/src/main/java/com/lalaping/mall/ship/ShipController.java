package com.lalaping.mall.ship;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lalaping.common.util.UtilDateTime;
import com.lalaping.mall.fish.FishService;
import com.lalaping.mall.fish.FishVo;

@Controller
public class ShipController {
	@Autowired
	public ShipService shipService;
	@Autowired
	FishService fishService;
	
	@RequestMapping(value = "/v1/ship/shipXdmList")
	public String shipXdmList(Model model,@ModelAttribute("vo") ShipVo vo){
		vo.setShDateStart(vo.getShDateStart() == null || vo.getShDateStart() == "" ? null : UtilDateTime.add00TimeString(vo.getShDateStart()));
		vo.setShDateEnd(vo.getShDateEnd() == null || vo.getShDateEnd() == "" ? null : UtilDateTime.add59TimeString(vo.getShDateEnd()));
		vo.setParamsPaging(shipService.listCount(vo));
		model.addAttribute("list",shipService.selectList(vo));
		model.addAttribute("formLink", "shipXdmForm");
		return "xdm/v1/ship/shipXdmList";
	}
	@RequestMapping(value = "/v1/ship/shipXdmForm")
	public String shipXdmForm(Model model) {
		model.addAttribute("listLink", "shipXdmList");
		return "xdm/v1/ship/shipXdmForm";
	}
	@RequestMapping(value = "/v1/ship/shipXdmInst")
	public String shipXdmInst(ShipDto shipDto) {
		shipService.insert(shipDto);
		return "redirect:/v1/ship/shipXdmList";
	}
	@RequestMapping(value = "/v1/ship/shipXdmMFom")
	public String shipXdmMFom(Model model,ShipDto shipDto) {
		model.addAttribute("item",shipService.selectOne(shipDto));
		model.addAttribute("listLink", "shipXdmList");
		return "xdm/v1/ship/shipXdmMFom";
	}
	@RequestMapping(value="/v1/ship/shipXdmUpdt")
	public String shipXdmUpdt(ShipDto shipDto)throws Exception {
		shipService.update(shipDto);
		return "redirect:/v1/ship/shipXdmList";
	}
	@RequestMapping(value="/v1/ship/shipXdmUelt")
	public String shipXdmUelt(ShipDto shipDto) {
		shipService.uelete(shipDto);
		return "redirect:/v1/ship/shipXdmList";
	}
	@RequestMapping(value="/v1/ship/shipXdmDelt")
	public String shipXdmDelt(ShipDto shipDto) {
		shipService.delete(shipDto);
		return "redirect:/v1/ship/shipXdmList";
	}
	
	/*usr*/
	@RequestMapping(value = "/v1/ship/shipList")
	public String shipList(Model model,@ModelAttribute("vo") ShipVo vo ,@ModelAttribute("fishVo") FishVo fishVo){
		System.out.println("테스트" + vo.getSortOrder());
		switch (vo.getSortOrder()) {
			case 1: {
			
				vo.setSortOrderString("spStart Desc");
				break;
			}
			case 2:{
				vo.setSortOrderString("spPrice Desc");
				break;
			}
		}
		vo.setParamsPaging(shipService.listCount(vo));
		System.out.println(vo.getStartPage());
		System.out.println(vo.getThisPage());
		System.out.println(vo.getPageNumToShow());
		System.out.println(vo.getTotalRows());
		System.out.println(vo.getTotalPages());
		vo.setShDateStart(vo.getShDateStart() == null || vo.getShDateStart() == "" ? null : UtilDateTime.add00TimeString(vo.getShDateStart()));
		vo.setShDateEnd(vo.getShDateEnd() == null || vo.getShDateEnd() == "" ? null : UtilDateTime.add59TimeString(vo.getShDateEnd()));
		model.addAttribute("list",shipService.selectUsrList(vo));	
		List<ShipDto> shipList = shipService.selectUsrList(vo);
		for (ShipDto ship : shipList) {
		    System.out.println("Ship Name: " + ship.getSpName());
		    System.out.println("Ship Capacity: " + ship.getSpTotal());
		    System.out.println("Fish: " + ship.getFsNameList());

		    // fsSeqList와 fsNameList가 null이거나 비어있는지 체크
		    if (ship.getFsSeqList() != null && !ship.getFsSeqList().isEmpty()) {
		        System.out.println("Fish Sequence: " + ship.getFsSeqList());
		    } else {
		        System.out.println("No Fish Available");
		    }

		    // fsNameList가 null이거나 비어있는지 체크
		    if (ship.getFsNameList() != null && !ship.getFsNameList().isEmpty()) {
		        System.out.println("Fish Names: " + ship.getFsNameList());
		    } else {
		        System.out.println("No Fish Available");
		    }
		}

		model.addAttribute("fishList",fishService.allOneList(fishVo));
		return "usr/v1/ship/ping_shipList";
	}
	@RequestMapping(value = "/v1/ship/shipDetail")
	public String shipDetail(Model model,ShipDto shipDto,ShipVo shipVo){
		System.out.println("sadfgfgdfgfdssss");
		model.addAttribute("list", shipService.selectUsrList2(shipVo));	
		model.addAttribute("item",shipService.selectOne(shipDto));
		shipVo.setBaseSpSeq(shipService.selectOne(shipDto).getSpSeq());
		shipVo.setPort_ptSeq(shipService.selectOne(shipDto).getPort_ptSeq());
		model.addAttribute("other", shipService.otherPortSelectList(shipVo));
		return "usr/v1/ship/ping_shipDetail";
	}

}