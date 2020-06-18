package pl.umk.mat.danielsz.recipeapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "recipes")
@NoArgsConstructor
@Setter @Getter
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_fk")
    private User user = new User();

    @OneToMany(mappedBy = "recipe")
    private List<Comment> comments = new ArrayList<>();
}

