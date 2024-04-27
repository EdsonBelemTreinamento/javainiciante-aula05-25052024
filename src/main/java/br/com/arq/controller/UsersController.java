package br.com.arq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.arq.dto.UsersDTO;
import br.com.arq.service.UsersService;
@ResponseBody
@RestController
@RequestMapping("/api")
public class UsersController {

	@Autowired
	private UsersService service;
	
	
	@PostMapping("/users")
	public ResponseEntity<?> saveController(@RequestBody @Validated UsersDTO dto){
		try {
			return ResponseEntity.status(200).body(service.saveService(dto));
		}catch(Exception ex) {
			return ResponseEntity.status(500).body("Error:" + ex.getMessage());
		}
	}
	
	@GetMapping("/list")
	public ResponseEntity<?>  findAllController(){
		return ResponseEntity.status(200).body(service.findAll());
	}
}
