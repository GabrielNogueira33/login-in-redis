# login-in-redis
implementando redis em uma simulação de login

Simule isso com um POST em um cliente de API (Insomnia, Bruno, Postman, etc) no endpoint

Mas primeiramente inicie um containter com docker de redis em sua máquina e implemente a porta padrão do redis 6379

# Requisitos minimos 
Java 21
Docker instalado
Porta 6379 e 8080 disponíveis

```
sudo docker run -d --name redis-local -p 6379:6379 redis
```

Após isto, você pode começar a testar:

Caso de êxito

```
POST http://localhost:8080/login/usuario?senha=123
```

Caso de falha 

```
POST http://localhost:8080/login/usuario?senha=senhaErrada
```

Após falhar 5 vezes, o usuário será bloqueado por 60 segundos por conta do cache do redis.
