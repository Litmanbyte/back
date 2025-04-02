package com.example.back.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.text.MessageFormat;

import com.example.back.security.CustomJwt;
import com.nimbusds.jose.proc.SecurityContext;

@RestController
@CrossOrigin(
    origins = "http://localhost:4200",
    allowedHeaders = "*",
    methods = { RequestMethod.GET }
)
public class MenuController {

    @GetMapping("/menu")
    @PreAuthorize("hasAuthority('ROLE_default-roles-gerenciamento-laudos')")
    public InnerMenuController hello(){
        var jwt = (CustomJwt)SecurityContextHolder.getContext().getAuthentication();
        var message = String.format(
                "Olá bem vindo ao menu %s %s", jwt.getFirstname(), jwt.getLastname());
        return new InnerMenuController(message);
    }
    
    record InnerMenuController(String message) {
    }
}
