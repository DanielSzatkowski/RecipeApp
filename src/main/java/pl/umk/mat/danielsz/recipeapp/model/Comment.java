package pl.umk.mat.danielsz.recipeapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "comments")
public class Comment {

    @NotBlank
    @Column(name = "content")
    private String content;

    @NotBlank
    @Column(name = "rating")
    @Min(value = 1, message = "Value too low") @Max(value = 5, message = "Value too high")
    private short rating;

    @NotBlank
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date creationDate;
}
