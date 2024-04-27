package br.com.arq.service;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.stereotype.Service;

@Service
public class CriptografiaService {

	public String criptografarSenha(String senha) {
		BasicPasswordEncryptor encryptor = new BasicPasswordEncryptor();
		String resposta = encryptor.encryptPassword(senha);
		return resposta;
	}

	public Boolean checkSenha(String senhaAntiga, String senhaCriptografada) {
		BasicPasswordEncryptor encryptor = new BasicPasswordEncryptor();
		Boolean resposta = encryptor.checkPassword(senhaAntiga, senhaCriptografada);
		return resposta;
	}

}
