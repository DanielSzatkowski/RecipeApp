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

    @Override
    public int hashCode(){
        return 20;
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

        Role other = (Role) obj;
        return (id != null && id.equals(other.getId()));
    }
}
