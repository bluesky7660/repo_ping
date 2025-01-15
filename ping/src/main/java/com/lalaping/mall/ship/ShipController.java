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
import com.lalaping.mall.port.PortService;
import com.lalaping.mall.port.PortVo;

@Controller
public class ShipController {
	@Autowired
	public ShipService shipService;
	@Autowired
	public PortService portService;
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
	public String shipXdmForm(Model model,PortVo portVo) {
		model.addAttribute("list", portService.selectList(portVo));
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
		model.addAttribute("port",portService.allList());
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
		switch (vo.getSortOrder()) {
	        case 4: {
	            vo.setSortOrderString("spPrice ASC");
	            break;
	        }
	        case 5: {
	            vo.setSortOrderString("spPrice DESC");
	            break;
	        }
	        case 6: {
	            vo.setSortOrderString("spStart ASC");
	            break;
	        }
	        default: {
	            vo.setSortOrderString("spPrice ASC"); 
	            break;
	        }
	    }
		vo.setParamsPaging(shipService.listCount(vo));
		vo.setShDateStart(vo.getShDateStart() == null || vo.getShDateStart() == "" ? null : UtilDateTime.add00TimeString(vo.getShDateStart()));
		vo.setShDateEnd(vo.getShDateEnd() == null || vo.getShDateEnd() == "" ? null : UtilDateTime.add59TimeString(vo.getShDateEnd()));
		model.addAttribute("list",shipService.selectUsrList3(vo));	
		List<ShipDto> shipList = shipService.selectUsrList3(vo);

		model.addAttribute("fishList",fishService.allOneList(fishVo));
		return "usr/v1/ship/ping_shipList";
	}
	@RequestMapping(value = "/v1/ship/shipDetail")
	public String shipDetail(Model model,ShipDto shipDto,ShipVo shipVo){
		model.addAttribute("list", shipService.selectUsrList2(shipVo));	
		model.addAttribute("item",shipService.selectOne(shipDto));
		shipVo.setBaseSpSeq(shipService.selectOne(shipDto).getSpSeq());
		shipVo.setPort_ptSeq(shipService.selectOne(shipDto).getPort_ptSeq());
		model.addAttribute("other", shipService.otherPortSelectList(shipVo));
		return "usr/v1/ship/ping_shipDetail";
	}

}