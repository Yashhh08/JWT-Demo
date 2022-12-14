package com.masai.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest {

    private String username;
    private String password;

}
