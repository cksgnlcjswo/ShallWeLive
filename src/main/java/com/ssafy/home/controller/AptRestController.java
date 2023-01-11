package com.ssafy.home.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.home.model.dto.AptInfo;
import com.ssafy.home.model.dto.HouseHistory;
import com.ssafy.home.model.dto.HouseInfo;
import com.ssafy.home.model.dto.SidoGugunDongCodeDto;
import com.ssafy.home.model.service.AptService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST} , maxAge = 6000)
@RestController
@RequestMapping("/apt")
@Api("아파트 관련 컨트롤러  API V1")
@Slf4j
public class AptRestController {
	
	@Autowired
	AptService aptService;
	
	@GetMapping
	public ResponseEntity<?> getApt(@RequestParam String aptName) {
		log.info("aptName {}",aptName);
		
		HouseInfo result = aptService.getHouse(aptName);
		
		return new ResponseEntity<HouseInfo>(result,HttpStatus.OK);
	}
	@ApiOperation(value = "아파트 과거이력", notes = "아파트 과거 히스토리가격")
	@GetMapping("/history")
	public ResponseEntity<?> getAptHistory(@RequestParam String aptName) {
		log.info("aptName {}",aptName);
		
		List<HouseHistory> result = aptService.getHouseHistory(aptName);
		
		return new ResponseEntity<List<HouseHistory> >(result,HttpStatus.OK);
	}
	
	//동과 price를 주면 price이하의 아파트 리스트 반환
	@ApiOperation(value = "아파트 리스트", notes = "price 이하의 아파트 반환")
	@GetMapping("/list")
	public ResponseEntity<?> listApt(@RequestParam @ApiParam(value = "아파트 동", required = true)String dong,
									 @RequestParam @ApiParam(value = "가격", required = true) String price) throws SQLException {
		
		log.info("dong {}, price {}",dong, price);
		
		int cost = Integer.parseInt(price);
		
		Map<String,Object> map = new HashMap<>();
		
		map.put("dong", dong);
		map.put("price",cost);
		
		List<AptInfo> result= aptService.searchByDongAndPrice(map);
		
		log.info("result apt-list.size {}",result.size());
		
		if(result.size() > 0) {
			return new ResponseEntity<List<AptInfo>>(result,HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value = "시도 정보", notes = "전국의 시도를 반환한다.", response = List.class)
	@Cacheable(value="sido", key="0")
	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunDongCodeDto>> sido() throws Exception {
		log.info("sido - 호출");
		return new ResponseEntity<List<SidoGugunDongCodeDto>>(aptService.getSido(), HttpStatus.OK);
	}	

	@ApiOperation(value = "구군 정보", notes = "전국의 구군을 반환한다.", response = List.class)
	@GetMapping("/gugun")
	public ResponseEntity<List<SidoGugunDongCodeDto>> gugun(
			@RequestParam("sido") @ApiParam(value = "시도코드.", required = true) String sido) throws Exception {
		log.info("gugun - 호출");
		return new ResponseEntity<List<SidoGugunDongCodeDto>>(aptService.getGugunInSido(sido), HttpStatus.OK);
	}
	
	@ApiOperation(value = "구군 정보", notes = "전국의 동을 반환한다.", response = List.class)
	@GetMapping("/dong")
	public ResponseEntity<List<SidoGugunDongCodeDto>> dong(
			@RequestParam("gugun") @ApiParam(value = "구군코드", required = true) String gugun) throws Exception {
		log.info("dong - 호출");
		return new ResponseEntity<List<SidoGugunDongCodeDto>>(aptService.getDongInGugun(gugun), HttpStatus.OK);
	}
}
