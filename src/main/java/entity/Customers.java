package entity;

import dao.IdentifiedTable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "customers")
public class Customers implements IdentifiedTable<Integer> {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "title")
    private String title;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "customer_projects",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    private Set<Projects> projects = new HashSet<>();

    public Customers() {

    }

    public Customers(Integer id) {
        this.id = id;
    }


    public Customers(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Set<Projects> getProjects() {
        return projects;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setProjects(Set<Projects> projects) {
        this.projects = projects;
    }

    public void addProjects(Projects project){
        this.projects.add(project);
        project.getCustomers().add(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Developers)) return false;
        return (id != null) && id.equals(((Customers) obj).id);
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + prime * id;
        return result;
    }

    @Override
    public String toString() {
        return "Customers:" +
                "\nid = " + this.id +
                ",\ntitle = " + this.title;
    }
}
