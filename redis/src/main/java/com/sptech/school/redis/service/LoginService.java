package com.sptech.school.redis.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class LoginService {
    private final StringRedisTemplate redis;

    public LoginService(StringRedisTemplate redis){
        this.redis = redis;
    }

    public String tentarLogin(String usuario, String senha){
        String key = "tentativas" + usuario;

        // Obter o numero atual de tentativa
        String tentativas = redis.opsForValue().get(key);
        int qtdTentativas = tentativas != null ? Integer.parseInt(tentativas) : 0;

        // Se já está bloqueado
        if(qtdTentativas >= 5){
            return "Usuário Bloqueado, tente novamente mais tarde";
        }
        //Simula tentativa correta
        String senhaCorreta = "123";

        if(!senha.equals(senhaCorreta)){
            // Incrementar a tentativa e definir a expiração
            redis.opsForValue().set(
                    key,
                    String.valueOf(qtdTentativas + 1),
                    60,
                    TimeUnit.SECONDS
            );
            return "Senha incorreta";
        }

        // Limpa a quantidade de tentativas
        redis.delete(key);
        return "Login realizado com sucesso!";
    }
}
