package pl.umk.mat.danielsz.recipeapp.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter @Getter
@NoArgsConstructor
public class CommentCreateDto {

    @NotBlank
    private String content;

    @NotNull
    @Min(value = 1, message = "Value too low") @Max(value = 5, message = "Value too high")
    private int rating;
}
