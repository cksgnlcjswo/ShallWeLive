package com.ssafy.home.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.home.model.dto.RoommateDto;
import com.ssafy.home.model.dto.SeekDto;
import com.ssafy.home.model.service.RoommateService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/roommate")
@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST} , maxAge = 6000)
public class RoommateRestController {
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	RoommateService roommateService;
	
	@ApiOperation(value="하우스메이트들",notes="하우스메이트 보여주기",response=List.class)
	@GetMapping
	public ResponseEntity<?> getSeeker(@RequestParam String aptName) {
		
		List<RoommateDto> result = roommateService.getSeeker(aptName);
		
		return new ResponseEntity<List<RoommateDto>> (result,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> insertSeeker(@RequestBody SeekDto seekDto) throws Exception {
		
		int result = roommateService.insertSeeker(seekDto);
        
		if(result > 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
        
    }
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> delete(@PathVariable String userId) throws Exception {
		
		int result = roommateService.deleteSeeker(userId);
        
		if(result > 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
        
    }
}
