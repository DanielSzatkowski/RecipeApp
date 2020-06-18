package pl.umk.mat.danielsz.recipeapp.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @Column(name = "profile_picture")
    private String profilePicture;

    @ManyToMany
    @JoinTable(name = "users_roles",
        joinColumns = { @JoinColumn(name = "user_fk") },
        inverseJoinColumns = { @JoinColumn(name = "role_fk") })
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private List<Recipe> recipes = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();
}
