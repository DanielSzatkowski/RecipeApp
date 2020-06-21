package pl.umk.mat.danielsz.recipeapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
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

    public Set<GrantedAuthority> getGrantedAuthoritiesRoles(){
        Set<GrantedAuthority> authSet = new HashSet<>();

        for(Role role: this.getRoles()){
            authSet.add(new SimpleGrantedAuthority(role.getName()));
        }

        return authSet;
    }

    @Override
    public int hashCode(){
        return 10;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }

        if(obj == null){
            return false;
        }

        if(this.getClass() != obj.getClass()){
            return false;
        }

        User other = (User) obj;
        return (id != null && id.equals(other.getId()));
    }
}
