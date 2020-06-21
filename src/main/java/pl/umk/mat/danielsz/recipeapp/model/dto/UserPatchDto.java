package pl.umk.mat.danielsz.recipeapp.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter @Setter
@NoArgsConstructor
public class UserPatchDto {

    private String description;

    @Email
    private String mail;
}
