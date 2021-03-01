package com.leverx.pets.provider;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@Data
@RequestScope
public class AuthProvider {

    private String authHeader;

}
