package com.sptech.school.redis.controller;

import com.sptech.school.redis.service.LoginService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/login")
public class LoginController {
    private final LoginService loginService;

            public LoginController(LoginService loginService){
                this.loginService = loginService;
            }

            @PostMapping("{usuario}")
            public String login(
                    @PathVariable String usuario,
                    @RequestParam String senha
            ){
                return loginService.tentarLogin(usuario,senha);
            }
}
