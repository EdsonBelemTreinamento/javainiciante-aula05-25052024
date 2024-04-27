package br.com.arq.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.arq.dto.UsersDTO;
import br.com.arq.entity.Users;
import br.com.arq.repository.UsersRepository;

@Service
public class UsersService {
	@Autowired
	private CriptografiaService service;
	
	@Autowired
	private UsersRepository repository;

	public Object saveService(UsersDTO dto) throws Exception {
		try {
			Users users = dto.toEntity();
			users.setStatus("created");
			users.setPassword(service.criptografarSenha(users.getPassword()));
			Users resposta = repository.save(users);
			if (resposta == null) {
				throw new IllegalArgumentException("Gravacao invalida");
			}
			return resposta;
		} catch (Exception ex) {
			throw new Exception("error:" + ex.getMessage()); // serviço eu lanço error
		}
	}

	public List<Users> findAll() {
		return repository.findAll();
	}
}
