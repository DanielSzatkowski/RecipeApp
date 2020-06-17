package pl.umk.mat.danielsz.recipeapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "user")
@Data @NoArgsConstructor
public class User extends BaseEntity{

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "mail")
    private String mail;

    @Lob
    @Column(name = "description")
    private String description;

    @Lob
    @Column(name = "profile-picture")
    private String profilePicture;
}
