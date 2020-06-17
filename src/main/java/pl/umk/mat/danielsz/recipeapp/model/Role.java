package pl.umk.mat.danielsz.recipeapp.model;

import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "roles")
@NoArgsConstructor
@Setter @Getter
public class Role extends BaseEntity {

    @NotBlank
    @Column(name = "name", unique = true)
    private String name;
}
