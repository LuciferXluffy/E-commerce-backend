package com.lucifer.shoes.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserRequest {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

}
