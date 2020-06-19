package pl.umk.mat.danielsz.recipeapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "user") //TODO: nieporzaek z tabelami w h2 trzeba zmienic user -> users [obecne sa dwie tabele tabela users pusta]
@Data @NoArgsConstructor
@Setter @Getter
public class User extends BaseEntity{

    @Column(name = "login", unique = true)
    private String login;

    @JsonIgnore
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

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "users_roles",
        joinColumns = { @JoinColumn(name = "user_fk") },
        inverseJoinColumns = { @JoinColumn(name = "role_fk") })
    private Set<Role> roles = new HashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "user")
    private List<Recipe> recipes = new ArrayList<>();

    @JsonBackReference
    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();
}
