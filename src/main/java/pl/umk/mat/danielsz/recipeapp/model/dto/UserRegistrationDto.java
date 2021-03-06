package pl.umk.mat.danielsz.recipeapp.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;

@NoArgsConstructor
@Getter @Setter
public class UserRegistrationDto {

    private String login;

    private String password;

    @Email
    private String mail;

}
