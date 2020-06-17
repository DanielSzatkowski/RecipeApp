package pl.umk.mat.danielsz.recipeapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "recipes")
public class Recipe extends BaseEntity {

    @NotBlank
    @Column(name = "name")
    private String name;

    @Lob
    @NotBlank
    @Column(name = "decription")
    private String description;

    @NotBlank
    @Column(name = "picture")
    private String picture;

    @NotBlank
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date creationDate;
}

