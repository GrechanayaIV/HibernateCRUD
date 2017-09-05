package entity;

import dao.IdentifiedTable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "skills")
public class Skills implements IdentifiedTable<Integer> {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @ManyToMany(mappedBy = "skills")
    private Set<Developers> developers = new HashSet<>();

    public Skills() { }

    public Skills(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Developers> getDevelopers() {
        return developers;
    }

    public void setDevelopers(Set<Developers> developers) {
        this.developers = developers;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return  true;
        if(obj == null|| getClass()!=obj.getClass())return false;
        Skills skills = (Skills)obj;
        return Objects.equals(id, skills.id);
    }

    @Override
    public int hashCode() {
        int prime =31;
        int result = 1;
        result = prime*result + (int)(id^(id>>>32));
        result = prime* result + ((title == null)? 0: title.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Skills:" +
                "\nid = " + this.id +
                ",\ntitle = " + this.title;
    }
}

