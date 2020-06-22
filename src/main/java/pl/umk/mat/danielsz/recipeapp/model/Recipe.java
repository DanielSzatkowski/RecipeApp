package pl.umk.mat.danielsz.recipeapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @Column(name = "picture")
    private String picture;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date creationDate;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_fk")
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.creationDate = new Date();
    }

    @Override
    public int hashCode(){
        return 30;
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

        Recipe other = (Recipe) obj;
        return (id != null && id.equals(other.getId()));
    }
}

