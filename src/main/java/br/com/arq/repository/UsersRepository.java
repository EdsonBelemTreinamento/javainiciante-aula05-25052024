package br.com.arq.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import br.com.arq.entity.Users;

public interface UsersRepository extends MongoRepository<Users,String> {

	 public Users findByEmail(String email);
}
