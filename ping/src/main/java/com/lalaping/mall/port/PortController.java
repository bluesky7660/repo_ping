package com.lalaping.mall.port;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lalaping.common.util.UtilDateTime;
import com.lalaping.mall.ship.ShipDto;
import com.lalaping.mall.ship.ShipService;
import com.lalaping.mall.ship.ShipVo;

@Controller
public class PortController {
	@Autowired
	public PortService portService;
	
	@Autowired
	ShipService shipService;
	
	@RequestMapping(value = "/v1/port/portXdmList")
	public String portXdmList(Model model,@ModelAttribute("vo") PortVo vo){
		model.addAttribute("list",portService.selectList(vo));
		model.addAttribute("formLink", "portXdmForm");
		return "/xdm/v1/port/portXdmList";
	}
	@RequestMapping(value = "/v1/port/portXdmForm")
	public String portXdmForm(Model model) {
		model.addAttribute("listLink", "portXdmList");
		return "/xdm/v1/port/portXdmForm";
	}
	@RequestMapping(value = "/v1/port/portXdmInst")
	public String portXdmInst(PortDto portDto) {
		portService.insert(portDto);
		return "redirect:/v1/port/portXdmList";
	}
	@RequestMapping(value = "/v1/port/portXdmMFom")
	public String portXdmMFom(Model model,PortDto portDto) {
		model.addAttribute("item",portService.selectOne(portDto));
		return "/xdm/v1/port/portXdmMFom";
	}
	@RequestMapping(value="/v1/port/portXdmUpdt")
	public String portXdmUpdt(PortDto portDto) {
		portService.update(portDto);
		return "redirect:/v1/port/portXdmList";
	}
	@RequestMapping(value="/v1/port/portXdmUelt")
	public String portXdmUelt(PortDto portDto) {
		portService.uelete(portDto);
		return "redirect:/v1/port/portXdmList";
	}
	@RequestMapping(value="/v1/port/portXdmDelt")
	public String portXdmDelt(PortDto portDto) {
		portService.delete(portDto);
		return "redirect:/v1/port/portXdmList";
	}
	
	/*usr*/
//	@RequestMapping(value = "/v1/port/portList3")
//	public String portList3(Model model,@ModelAttribute("vo") PortVo vo){
//		vo.setParamsPaging(portService.selectOneCount(vo));
//		System.out.println("thisPage:"+vo.getThisPage());
//		System.out.println("startPage:"+vo.getStartPage());
//		System.out.println("endPage:"+vo.getEndPage());
//		System.out.println("totalPages:"+vo.getTotalPages());
//		System.out.println("totalRows:"+vo.getTotalRows());
//		model.addAttribute("list",portService.selectList(vo));
//		return "/usr/v1/port/ping_portList3";
//	}
	
	@RequestMapping(value = "/v1/port/portList")
	public String portList(Model model,@ModelAttribute("vo") PortVo vo) {
		vo.setRowNumToShow(6);
		vo.setParamsPaging(portService.selectOneCount(vo));
		model.addAttribute("list2", portService.selectList(vo));
		return "/usr/v1/port/ping_portList";
	}
	@RequestMapping(value = "/v1/port/portDetail")
	public String portDetail(Model model, PortDto portDto, ShipVo shipVo){	
		shipVo.setShDateStart(shipVo.getShDateStart() == null || shipVo.getShDateStart() == "" ? null : UtilDateTime.add00TimeString(shipVo.getShDateStart()));
		shipVo.setShDateEnd(shipVo.getShDateEnd() == null || shipVo.getShDateEnd() == "" ? null : UtilDateTime.add59TimeString(shipVo.getShDateEnd()));
		PortDto item = portService.selectOne(portDto);
		shipVo.setPort_ptSeq(item.getPtSeq());
		shipVo.setParamsPaging(shipService.portListCount(shipVo));
		System.out.println("thisPage:"+shipVo.getThisPage());
		System.out.println("startPage:"+shipVo.getStartPage());
		System.out.println("endPage:"+shipVo.getEndPage());
		System.out.println("totalPages:"+shipVo.getTotalPages());
		System.out.println("totalRows:"+shipVo.getTotalRows());
		model.addAttribute("item",item);
		model.addAttribute("shipCount", shipService.portListCount(shipVo));
		model.addAttribute("ships", shipService.portSelectList(shipVo));
		return "/usr/v1/port/ping_portDetail";
	}
	
	@ResponseBody
	@RequestMapping(value = "portShipList")
    public Map<String, Object> portShipList(@RequestBody ShipVo shipVo) {
		System.out.println("portShipList");
		shipVo.setPort_ptSeq(shipVo.getPort_ptSeq());
		shipVo.setParamsPaging(shipService.portListCount(shipVo));
		System.out.println("seq:"+shipVo.getPort_ptSeq());
		System.out.println("thisPage:"+shipVo.getThisPage());
		System.out.println("startPage:"+shipVo.getStartPage());
		System.out.println("endPage:"+shipVo.getEndPage());
		System.out.println("totalPages:"+shipVo.getTotalPages());
		System.out.println("totalRows:"+shipVo.getTotalRows());
		List<ShipDto> ships = shipService.portSelectList(shipVo);

	    Map<String, Object> responseMap = new HashMap<>();
	    responseMap.put("shipList", ships); // 리뷰 목록 추가
	    responseMap.put("thisPage", shipVo.getThisPage()); // 현재 페이지
	    responseMap.put("totalPages", shipVo.getTotalPages()); // 총 페이지 수
        return responseMap; // 제품에 대한 리뷰 목록 반환
    }

}