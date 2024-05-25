package br.com.arq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.arq.dto.UsersDTO;
import br.com.arq.entity.Users;
import br.com.arq.service.UsersService;
import br.com.arq.service.UsersServiceToken;
@ResponseBody
@RestController
@RequestMapping("/demo")
public class UsersController {

 
	@Autowired
	private UsersService service;
	@Autowired
	private UsersServiceToken serviceToken;
	
	@PostMapping("/users")
	public ResponseEntity<?> saveController(@RequestBody @Validated UsersDTO dto){
		try {
			return ResponseEntity.status(200).body(service.saveService(dto));
		}catch(Exception ex) {
			return ResponseEntity.status(500).body("Error:" + ex.getMessage());
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> loginController(@RequestBody Users users){
		try {
		 return ResponseEntity.status(200).body(service.login(users));
		}catch(Exception ex) {
		 return ResponseEntity.status(500).body("Error:" + ex.getMessage());
		}
	}
	
	
	@GetMapping("/list")
	public ResponseEntity<?>  findAllController(@RequestHeader("Authorization") String authorization){
		
		if(serviceToken.validateToken(this.extractToken(authorization))) {
		return ResponseEntity.status(200).body(service.findAll());
		}else {
	    return ResponseEntity.status(401).body("Token invalido");
		}
	}
	

	 private String extractToken(String authorizationHeader) {
	 if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
	            return authorizationHeader.substring(7);
	   }
	  return null;
	  } 
}
