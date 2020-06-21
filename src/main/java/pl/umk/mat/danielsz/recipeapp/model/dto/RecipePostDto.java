package pl.umk.mat.danielsz.recipeapp.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
@NoArgsConstructor
public class RecipePostDto {

    @NotBlank
    private String name;

    @NotBlank
    private String description;
}
