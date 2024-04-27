package br.com.arq.dto;

import br.com.arq.entity.Users;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UsersDTO {
	
	    private String id;
	    
	    @Pattern(regexp="[A-Za-z ]{2,250}",message="Nome Fora do Padrao ...")
		private String username;
	   
	    @Email(message="Email fora do Padrao ...")
		private String email;
	   
	    @Size(min=6,max=40,message="Senha no minimo de 6 carateres")
		private String password;
		
	    private String status;

		
        

		public UsersDTO(String id,
				@Pattern(regexp = "[A-Za-z ]{2,250}", message = "Nome Fora do Padrao ...") String username,
				@Email(message = "Email fora do Padrao ...") String email,
				@Size(min = 6, max = 40, message = "Senha no minimo de carateres") String password, String status) {
			super();
			this.id = id;
			this.username = username;
			this.email = email;
			this.password = password;
			this.status = status;
		}
		
		
		public UsersDTO() {
			 
		}
	 //alteração
		public Users toEntityWithId() {
			Users user = new Users();
			user.setId(this.id);
			user.setUsername(this.username);
			user.setEmail(this.email);
			user.setPassword(this.password);
			user.setStatus(this.status);
			return user;
		}
       //criação do usuario  
		public Users toEntity() {
			Users user = new Users();
			user.setUsername(this.username);
			user.setEmail(this.email);
			user.setPassword(this.password);
			user.setStatus(this.status);
			return user;
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
