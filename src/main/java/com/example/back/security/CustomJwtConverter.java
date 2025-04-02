package com.example.back.security;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.jwt.Jwt;

public class CustomJwtConverter implements Converter<Jwt,CustomJwt>{

    @Override
    public CustomJwt convert(Jwt source){
        List<GrantedAuthority> granted = extract(source);
        var customJwt = new CustomJwt(source, granted);
        customJwt.setFirstname(source.getClaimAsString("given_name"));
        customJwt.setLastname(source.getClaimAsString("family_name"));
        return customJwt;
    }

    private List<GrantedAuthority> extract(Jwt jwt){
        var result = new ArrayList<GrantedAuthority>();
        var access = jwt.getClaimAsMap("realm_access");
        if (access != null && access.get("roles") != null){
            var roles = access.get("roles");
            if (roles instanceof List l){
                l.forEach(role -> {
                    result.add(new SimpleGrantedAuthority("ROLE_"+role));
                });
            }
        }
        return result;
    } 
}
