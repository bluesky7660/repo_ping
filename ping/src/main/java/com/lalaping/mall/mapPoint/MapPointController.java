package com.lalaping.mall.mapPoint;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lalaping.common.util.UtilDateTime;
import com.lalaping.mall.fish.FishDto;
import com.lalaping.mall.fish.FishService;
import com.lalaping.mall.fish.FishVo;
import com.lalaping.mall.fishMappoint.FishMappointDao;
import com.lalaping.mall.fishMappoint.FishMappointDto;
import com.lalaping.mall.fishMappoint.FishMappointService;
import com.lalaping.mall.review.ReviewDto;
import com.lalaping.mall.review.ReviewService;
import com.lalaping.mall.review.ReviewVo;
import com.lalaping.mall.ship.ShipDto;

@Controller
public class MapPointController {
	@Autowired
	public MapPointService mapPointService;
	@Autowired
	FishService fishService;
	@Autowired
	ReviewService reviewService;
	@Autowired
	FishMappointService fishMappointService;
	
	@RequestMapping(value = "/v1/mapPoint/mapPointXdmList")
	public String mapPointXdmList(Model model,@ModelAttribute("vo") MapPointVo vo){
		vo.setShDateStart(vo.getShDateStart() == null || vo.getShDateStart() == "" ? null : UtilDateTime.add00TimeString(vo.getShDateStart()));
		vo.setShDateEnd(vo.getShDateEnd() == null || vo.getShDateEnd() == "" ? null : UtilDateTime.add59TimeString(vo.getShDateEnd()));
		vo.setParamsPaging(mapPointService.listCount(vo));
		model.addAttribute("list",mapPointService.selectList(vo));
		model.addAttribute("formLink", "mapPointXdmForm");
		return "xdm/v1/mapPoint/mapPointXdmList";
	}
	@RequestMapping(value = "/v1/mapPoint/mapPointXdmForm")
	public String mapPointXdmForm(Model model, FishVo fishVo) {
		model.addAttribute("fishList",fishService.allList(fishVo));
		model.addAttribute("listLink", "mapPointXdmList");
		return "xdm/v1/mapPoint/mapPointXdmForm";
	}
	@RequestMapping(value = "/v1/mapPoint/mapPointXdmMFom")
	public String mapPointXdmMFom(Model model,MapPointDto mapPointDto, FishVo fishVo) {
		model.addAttribute("item",mapPointService.selectOne(mapPointDto));
		model.addAttribute("fishList",fishService.allList(fishVo));
		model.addAttribute("listLink", "mapPointXdmList");
		return "xdm/v1/mapPoint/mapPointXdmMFom";
	}
	@RequestMapping(value="/v1/mapPoint/mapPointXdmUpdt")
	public String mapPointXdmUpdt(MapPointDto mapPointDto,FishMappointDto fishMappointDto) throws Exception{
		mapPointService.update(mapPointDto, fishMappointDto);
		return "redirect:/v1/mapPoint/mapPointXdmList";
	}
	
	@RequestMapping(value="/v1/mapPoint/mapPointXdmInst")
	public String mapPointXdmInst(MapPointDto mapPointDto ,FishMappointDto fishMappointDto) throws Exception{
		mapPointService.insert(mapPointDto, fishMappointDto);
		return "redirect:/v1/mapPoint/mapPointXdmList";
	}
	@RequestMapping(value="/v1/mapPoint/mapPointXdmUelt")
	public String mapPointXdmUelt(MapPointDto mapPointDto) {
		mapPointService.uelete(mapPointDto);
		return "redirect:/v1/mapPoint/mapPointXdmList";
	}
	@RequestMapping(value="/v1/mapPoint/mapPointXdmDelt")
	public String mapPointXdmDelt(MapPointDto mapPointDto) {
		mapPointService.delete(mapPointDto);
		return "redirect:/v1/mapPoint/mapPointXdmList";
	}
	
	/*usr*/
	@RequestMapping(value = "/v1/mapPoint/mapPointDetail")
	public String mapPointDetail(Model model,ReviewDto reviewDto,MapPointDto mapPointDto,@ModelAttribute("mapPointVo") MapPointVo mapPointVo){
		model.addAttribute("item",mapPointService.selectUsrOne(mapPointDto));
		model.addAttribute("rvList", reviewService.rvSelectListUsr(reviewDto));
		mapPointVo.setRowNumToShow(2);
		mapPointVo.setBaseMpLatitude(mapPointService.selectUsrOne(mapPointDto).getMpLatitude());
		mapPointVo.setBaseMpLongitude(mapPointService.selectUsrOne(mapPointDto).getMpLongitude());
		mapPointVo.setParamsPaging(mapPointService.nearCount(mapPointVo));
		model.addAttribute("nearList", mapPointService.nearList(mapPointVo));
		return "usr/v1/mapPoint/ping_mapPointDetail";
	}
	@RequestMapping(value = "/v1/mapPoint/mapPointAdd")
	public String mapPointAdd(Model model,FishVo fishVo){
		model.addAttribute("fishList",fishService.allOneList(fishVo));
		return "usr/v1/mapPoint/ping_mapPoint_add";
	}
	
	@RequestMapping(value = "/v1/mapPoint/mapPointSearchList")
	@ResponseBody
	public Map<String, Object> mapPointSearchList(MapPointDto mapPointDto,MapPointVo mapPointVo){
		Map<String, Object> returnMap = new HashMap<String, Object>();
		List<MapPointDto> rtPoint = mapPointService.selectSearchList(mapPointVo);
		
	    returnMap.put("data", rtPoint);
		return returnMap;
	}
	
	@RequestMapping(value = "/nearMapPointList")
	@ResponseBody
	public Map<String, Object> nearMapPointList(MapPointDto mapPointDto,@RequestBody MapPointVo mapPointVo){
		mapPointVo.setRowNumToShow(2);
		mapPointDto.setMpSeq(mapPointVo.getMpSeq());
		mapPointVo.setBaseMpLatitude(mapPointService.selectUsrOne(mapPointDto).getMpLatitude());
		mapPointVo.setBaseMpLongitude(mapPointService.selectUsrOne(mapPointDto).getMpLongitude());
		mapPointVo.setParamsPaging(mapPointService.nearCount(mapPointVo));
		List<MapPointDto> rtPoint = mapPointService.nearList(mapPointVo);
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
	    returnMap.put("mapPointList", rtPoint);
	    returnMap.put("thisPage", mapPointVo.getThisPage()); 
	    returnMap.put("totalPages", mapPointVo.getTotalPages());
		return returnMap;
	}
	
	@RequestMapping(value = "/mapPointInst")
	public String mapPointInst(MapPointDto mapPointDto,FishMappointDto fishMappointDto){
		try {
			mapPointService.insert(mapPointDto,fishMappointDto);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/v1/mapPoint/mapPointDetail?mpSeq="+mapPointDto.getMpSeq();
	}
	
	@RequestMapping(value = "reviewList")
	@ResponseBody
	public Map<String, Object> reviewList(Model model,MapPointDto mapPointDto,ReviewDto reviewDto){
		List<ReviewDto> rvList = reviewService.rvSelectListUsr(reviewDto);
		Map<String, Object> response = new HashMap<>();
		response.put("rvList", rvList);
		return response;
	}

}
