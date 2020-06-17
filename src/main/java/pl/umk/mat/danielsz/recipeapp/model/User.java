package pl.umk.mat.danielsz.recipeapp.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "user")
@Data @NoArgsConstructor
@Setter @Getter
public class User extends BaseEntity{

    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "mail", unique = true)
    private String mail;

    @Lob
    @Column(name = "description")
    private String description;

    @Lob
    @Column(name = "profile-picture")
    private String profilePicture;
}
