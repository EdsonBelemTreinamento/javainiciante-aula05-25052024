package br.com.arq.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("users")
public class Users {

	@Id
    private String id;
	private String username;
	@Indexed(unique=true)
	private String email;
	private String password;
	private String status;

	
	public Users(String id, String username, String email, String password, String status) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.status = status;
	}

	public Users() {
		 
	}
 

	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", email=" + email +  ", status=" + status + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
   
	
	
	
	
}
