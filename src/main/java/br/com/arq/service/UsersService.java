package br.com.arq.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	@Autowired
   private UsersServiceToken serviceToken;

	
	public Object saveService(UsersDTO dto) throws Exception {
		try {
			Users users = dto.toEntity();
			users.setStatus("ativo");
			if (users.getUsername().equals("luciana")) {
			users.setRole("admin");
			}else {
			 users.setRole("user");
			}
			users.setPassword(service.criptografarSenha(users.getPassword()));
			Users resposta = repository.save(users);
			if (resposta == null) {
				throw new IllegalArgumentException("Gravacao invalida");
			}
			Map<String,Object> mapa = new HashMap<String,Object>();
	       mapa.put("token", serviceToken.getGenerateTokenWithName(resposta.getUsername()));
	       mapa.put("usuario", resposta);
			return mapa;
		} catch (Exception ex) {
			throw new Exception("error:" + ex.getMessage()); // serviço eu lanço error
		}
	}
	
	public Object login(Users  users) throws Exception {
		try {
			//busca pelo email no banco 
             Users respUsers =repository.findByEmail(users.getEmail());
             //verificar a criptografia  
  boolean resp =  service.checkSenha(users.getPassword(), respUsers.getPassword());
            if (resp==false) {
            	throw new IllegalArgumentException("Senha invalida");
            }
            // alterar o campo status : logado
         this.updateServiceLogado(respUsers.getId(), respUsers);
         // retornar o token
    Map<String,Object> mapa = new HashMap<String,Object>();
	mapa.put("token", serviceToken.getGenerateTokenWithName(respUsers.getUsername()));
	  mapa.put("status","logado");
           return mapa;
		} catch (Exception ex) {
			throw new Exception("error:" + ex.getMessage()); 
		}
	}

	public List<Users> findAll() {
		return repository.findAll();
	}
	
	public Users updateServiceLogado(String id,Users user)throws Exception{
	return repository.findById(id).map(mapusers ->{
		 		                    mapusers.setStatus("logado");
				                   return repository.save(mapusers);
	               }).orElseThrow(()->new Exception("Error na Alteracao"));
		
	}
	
	public Users updateServiceLogout(String id,Users user)throws Exception{
		return repository.findById(id).map(mapusers ->{
			 		                    mapusers.setStatus("ativo");
					                   return repository.save(mapusers);
		               }).orElseThrow(()->new Exception("Error na Alteracao"));
			
	 }
	
	public Users updateServiceBloked(String id,Users user)throws Exception{
		return repository.findById(id).map(mapusers ->{
			 		                    mapusers.setStatus("bloqueado");
					                   return repository.save(mapusers);
		               }).orElseThrow(()->new Exception("Error na Alteracao"));
			
	 }
	
}
